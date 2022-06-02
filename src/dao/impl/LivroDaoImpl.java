package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Livro;
import dao.iface.ILivroDao;
import util.ConnectionTest;

public class LivroDaoImpl implements ILivroDao {

	@Override
	public void criar(Livro obj) {
		try {
			String query = "insert into livros(titulolivro, idioma, genero, anolivro) values (?, ?, ?, ?)";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setString(1, obj.getTitulolivro());
			pstmt.setString(2, obj.getIdioma());
			pstmt.setString(3, obj.getGenero());
			pstmt.setInt(4, obj.getAnolivro());
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Livro ler(long id) {
		Livro livro = new Livro();
		try {
			String query = "select * from livros where id_livro = ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setObject(1, id);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				// (titulolivro, idioma, genero, anolivro)
				livro.setTitulolivro(result.getString(2));
				livro.setIdioma(result.getString(3));
				livro.setGenero(result.getString(4));
				livro.setAnolivro(result.getInt(5));
				livro.setIdlivro(id);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return livro;
	}

	@Override
	public ArrayList<Livro> listar() {
		ArrayList<Livro> livros = new ArrayList<Livro>();
		try {
			String query = "select id_livro from livros order by id_livro";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			long l;
			while (result.next()) {
				l = result.getLong(1);
				livros.add(ler(l));
				//System.out.println(ler(l));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return livros;
	}

	@Override
	public ArrayList<Livro> buscar(String txt) {
		ArrayList<Livro> livros = new ArrayList<Livro>();
		txt = "%" + txt + "%";
		try {
			String query = "select * from livros where titulolivro like ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setString(1, txt);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Livro livroTemp = new Livro();
				// (titulolivro, idioma, genero, anolivro)
				livroTemp.setTitulolivro(result.getString(2));
				livroTemp.setIdioma(result.getString(3));
				livroTemp.setGenero(result.getString(4));
				livroTemp.setAnolivro(result.getInt(5));
				livroTemp.setIdlivro(result.getLong(1));
				livros.add(livroTemp);
				//System.out.println(livroTemp.getIdlivro() + " - " + livroTemp);
			}
			//System.out.println("----");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return livros;
	}

	@Override
	public void atualizar(Livro obj) {
		try {
			// (titulolivro, idioma, genero, anolivro)
			String query = "update livros set titulolivro = ?, idioma = ?, genero = ?, anolivro = ? where id_livro = ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setString(1, obj.getTitulolivro());
			pstmt.setString(2, obj.getIdioma());
			pstmt.setString(3, obj.getGenero());
			pstmt.setInt(4, obj.getAnolivro());
			pstmt.setObject(5, obj.getIdLivro());
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deletar(long id) {
		try {
			String query = "delete from livros where id_livro = ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setObject(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
