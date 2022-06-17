package dao.iface;

import java.util.ArrayList;

import model.Editora;

public interface IEditoraDao {

	public void criar(Editora obj);

	public Editora ler(long id);

	public void atualizar(Editora obj);

	public void deletar(long id);

	public ArrayList<Editora> listar();

	public ArrayList<Editora> buscar(String txt);
}
