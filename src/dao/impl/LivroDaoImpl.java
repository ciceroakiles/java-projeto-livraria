package dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import dao.iface.ILivroDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Livro;
import util.HibernateUtil;

public class LivroDaoImpl implements ILivroDao {

	private SessionFactory sf;
	
	public LivroDaoImpl() {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void criar(Livro obj) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(obj);
		transaction.commit();
	}

	@Override
	public Livro ler(long id) {
		EntityManager entityManager = sf.createEntityManager();
		Livro l = entityManager.find(Livro.class, id);
		return l;
	}

	@Override
	public void atualizar(Livro obj) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(obj);
		transaction.commit();
	}

	@Override
	public void deletar(long id) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(ler(id));
		transaction.commit();
	}

	@Override
	public ArrayList<Livro> listar() {
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(montarQuery(null));
		return gerarLista(query);
	}

	@Override
	public ArrayList<Livro> buscar(String txt) {
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(montarQuery(txt));
		return gerarLista(query);
	}

	private String montarQuery(String txt) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT id_livro, titulolivro, idioma, genero, anolivro FROM livros ");
		if (txt != null) {
			buffer.append("WHERE titulolivro LIKE '%");
			buffer.append(txt);
			buffer.append("%' ");
		}
		buffer.append("ORDER BY id_livro");
		return buffer.toString();
	}

	private ArrayList<Livro> gerarLista(Query query) {
		ArrayList<Livro> livros = new ArrayList<Livro>();
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Livro l = new Livro();
			l.setIdlivro(Integer.valueOf(obj[0].toString()));
			l.setTitulolivro(obj[1].toString());
			l.setIdioma(obj[2].toString());
			l.setGenero(obj[3].toString());
			l.setAnolivro(Integer.valueOf(obj[4].toString()));
			livros.add(l);
		}
		return livros;
	}
}
