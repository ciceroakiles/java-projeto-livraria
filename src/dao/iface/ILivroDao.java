package dao.iface;

import java.util.ArrayList;

import model.Livro;

public interface ILivroDao {

	public void criar(Livro obj);

	public Livro ler(long id);

	public void atualizar(Livro obj);

	public void deletar(long id);

	public ArrayList<Livro> listar();

	public ArrayList<Livro> buscar(String txt);
}
