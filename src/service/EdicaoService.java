package service;

import java.util.ArrayList;
import model.Edicao;
import dao.impl.EdicaoDaoImpl;

public class EdicaoService {

	private static EdicaoDaoImpl edicaoDao = new EdicaoDaoImpl();

	public static void criarEdicao(Edicao Edicao) {
		edicaoDao.criar(Edicao);
	}

	public static Edicao lerEdicao(String txt) {
		return edicaoDao.ler(txt);
	}

	public static ArrayList<Edicao> listarEdicoes() {
		return edicaoDao.listar();
	}

	public static void atualizarEdicao(Edicao Edicao) {
		edicaoDao.atualizar(Edicao);
	}

	public static void deletarEdicao(String txt) {
		edicaoDao.deletar(txt);
	}
}
