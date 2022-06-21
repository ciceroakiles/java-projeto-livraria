package dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT isbn, preco, anoedicao, numpaginas, qtdeestoque FROM edicoes ");
		buffer.append("ORDER BY isbn");
		EntityManager entityManager = sf.createEntityManager();
		Query query = (Query) entityManager.createNativeQuery(buffer.toString());
		ArrayList<Edicao> edicoes = new ArrayList<Edicao>();
		List<Object[]> lista = query.getResultList();
		for (Object[] obj : lista) {
			Edicao ec = new Edicao();
			ec.setIsbn(obj[0].toString());
			ec.setPreco(Double.valueOf(obj[1].toString()));
			ec.setAnoedicao(Integer.valueOf(obj[2].toString()));
			ec.setNumpaginas(Integer.valueOf(obj[3].toString()));
			ec.setQtdeestoque(Integer.valueOf(obj[4].toString()));
			edicoes.add(ec);
		}
		return edicoes;
	}
}
