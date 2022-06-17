package dao.impl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
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
		// TODO: Listar editoras
		return null;
	}

	@Override
	public ArrayList<Editora> buscar(String txt) {
		// TODO: Buscar editoras por nome
		return null;
	}
}
