package dao.iface;

import java.util.ArrayList;

import model.Edicao;

public interface IEdicaoDao {

	public void criar(Edicao obj);

	public Edicao ler(String txt);

	public ArrayList<Edicao> listar();

	public void atualizar(Edicao obj);

	public void deletar(String txt);
}
