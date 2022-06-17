package dao.impl;

import java.util.ArrayList;
import org.hibernate.SessionFactory;
import dao.iface.IEdicaoDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Edicao;
import util.HibernateUtil;

public class EdicaoDaoImpl implements IEdicaoDao {

	private SessionFactory sf;
	
	public EdicaoDaoImpl() {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void criar(Edicao obj) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(obj);
		transaction.commit();
	}

	@Override
	public Edicao ler(String txt) {
		EntityManager entityManager = sf.createEntityManager();
		Edicao ec = entityManager.find(Edicao.class, txt);
		return ec;
	}

	@Override
	public void atualizar(Edicao obj) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(obj);
		transaction.commit();
	}

	@Override
	public void deletar(String txt) {
		EntityManager entityManager = sf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(ler(txt));
		transaction.commit();
	}

	@Override
	public ArrayList<Edicao> listar() {
		// TODO: Listar edicoes
		return null;
	}
}
