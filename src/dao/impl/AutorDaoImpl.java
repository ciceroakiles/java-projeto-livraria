package dao.impl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
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
		// TODO: Listar autores
		return null;
	}

	@Override
	public ArrayList<Autor> buscar(String txt) {
		// TODO: Buscar autores por nome
		return null;
	}
}
