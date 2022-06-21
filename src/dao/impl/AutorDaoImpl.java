package dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import dao.iface.IAutorDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Autor;
import util.HibernateUtil;

public class AutorDaoImpl implements IAutorDao {

	private SessionFactory sf;
	
	public AutorDaoImpl() {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void criar(Autor obj) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(obj);
		transaction.commit();
	}

	@Override
	public Autor ler(long id) {
		EntityManager entityManager = sf.createEntityManager();
		Autor a = entityManager.find(Autor.class, id);
		return a;
	}

	@Override
	public void atualizar(Autor obj) {
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
	public ArrayList<Autor> listar() {
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(montarQuery(null));
		return gerarLista(query);
	}

	@Override
	public ArrayList<Autor> buscar(String txt) {
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(montarQuery(txt));
		return gerarLista(query);
	}

	private String montarQuery(String txt) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT id_autor, nomeautor, pseud, paisnasc, anonasc FROM autores ");
		if (txt != null) {
			buffer.append("WHERE nomeautor LIKE '%");
			buffer.append(txt);
			buffer.append("%' ");
		}
		buffer.append("ORDER BY id_autor");
		return buffer.toString();
	}

	private ArrayList<Autor> gerarLista(Query query) {
		ArrayList<Autor> autores = new ArrayList<Autor>();
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Autor a = new Autor();
			a.setIdAutor(Integer.valueOf(obj[0].toString()));
			a.setNomeautor(obj[1].toString());
			a.setPseud(Boolean.valueOf(obj[2].toString()));
			a.setPaisnasc(obj[3].toString());
			a.setAnonasc(Integer.valueOf(obj[4].toString()));
			autores.add(a);
		}
		return autores;
	}
}
