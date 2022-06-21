package dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import dao.iface.IEditoraDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Editora;
import util.HibernateUtil;

public class EditoraDaoImpl implements IEditoraDao {

	private SessionFactory sf;
	
	public EditoraDaoImpl() {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void criar(Editora obj) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(obj);
		transaction.commit();
	}

	@Override
	public Editora ler(long id) {
		EntityManager entityManager = sf.createEntityManager();
		Editora et = entityManager.find(Editora.class, id);
		return et;
	}

	@Override
	public void atualizar(Editora obj) {
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
	public ArrayList<Editora> listar() {
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(montarQuery(null));
		return gerarLista(query);
	}

	@Override
	public ArrayList<Editora> buscar(String txt) {
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(montarQuery(txt));
		return gerarLista(query);
	}

	private String montarQuery(String txt) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT id_editora, nomeeditora, logradendereco, numendereco, complendereco, cependereco, telefone "
				+ " FROM editoras ");
		if (txt != null) {
			buffer.append("WHERE nomeeditora LIKE '%");
			buffer.append(txt);
			buffer.append("%' ");
		}
		buffer.append("ORDER BY id_editora");
		return buffer.toString();
	}

	private ArrayList<Editora> gerarLista(Query query) {
		ArrayList<Editora> editoras = new ArrayList<Editora>();
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Editora et = new Editora();
			et.setIdEditora(Integer.valueOf(obj[0].toString()));
			et.setNomeeditora(obj[1].toString());
			et.setLogradendereco(obj[2].toString());
			et.setNumendereco(obj[3].toString());
			et.setComplendereco(obj[4].toString());
			et.setCependereco(obj[5].toString());
			et.setTelefone(obj[6].toString());
			editoras.add(et);
		}
		return editoras;
	}
}
