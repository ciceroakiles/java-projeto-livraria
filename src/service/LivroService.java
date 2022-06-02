package service;

import java.util.ArrayList;
import model.Livro;
import dao.impl.LivroDaoImpl;

public class LivroService {

	private static LivroDaoImpl livroDao = new LivroDaoImpl();

	public static void criarLivro(Livro livro) {
		livroDao.criar(livro);
	}

	public static Livro lerLivro(long idLivro) {
		return livroDao.ler(idLivro);
	}

	public static ArrayList<Livro> listarLivros() {
		return livroDao.listar();
	}

	public static ArrayList<Livro> buscarLivros(String texto) {
		return livroDao.buscar(texto);
	}

	public static void atualizarLivro(Livro livro) {
		livroDao.atualizar(livro);
	}

	public static void deletarLivro(long idLivro) {
		livroDao.deletar(idLivro);
	}
}
