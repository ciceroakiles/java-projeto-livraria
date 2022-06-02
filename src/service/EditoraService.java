package service;

import java.util.ArrayList;
import model.Editora;
import dao.impl.EditoraDaoImpl;

public class EditoraService {

	private static EditoraDaoImpl editoraDao = new EditoraDaoImpl();

	public static void criarEditora(Editora Editora) {
		editoraDao.criar(Editora);
	}

	public static Editora lerEditora(long idEditora) {
		return editoraDao.ler(idEditora);
	}

	public static ArrayList<Editora> listarEditoras() {
		return editoraDao.listar();
	}

	public static ArrayList<Editora> buscarEditoras(String texto) {
		return editoraDao.buscar(texto);
	}

	public static void atualizarEditora(Editora Editora) {
		editoraDao.atualizar(Editora);
	}

	public static void deletarEditora(long idEditora) {
		editoraDao.deletar(idEditora);
	}
}
