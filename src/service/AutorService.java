package service;

import java.util.ArrayList;
import model.Autor;
import dao.impl.AutorDaoImpl;

public class AutorService {

	private static AutorDaoImpl autorDao = new AutorDaoImpl();

	public static void criarAutor(Autor Autor) {
		autorDao.criar(Autor);
	}

	public static Autor lerAutor(long idAutor) {
		return autorDao.ler(idAutor);
	}

	public static ArrayList<Autor> listarAutores() {
		return autorDao.listar();
	}

	public static ArrayList<Autor> buscarAutores(String texto) {
		return autorDao.buscar(texto);
	}

	public static void atualizarAutor(Autor Autor) {
		autorDao.atualizar(Autor);
	}

	public static void deletarAutor(long idAutor) {
		autorDao.deletar(idAutor);
	}
}
