package dao.iface;

import java.util.ArrayList;

import model.Autor;

public interface IAutorDao {

	public void criar(Autor obj);

	public Autor ler(long id);

	public ArrayList<Autor> listar();

	public ArrayList<Autor> buscar(String txt);

	public void atualizar(Autor obj);

	public void deletar(long id);
}
