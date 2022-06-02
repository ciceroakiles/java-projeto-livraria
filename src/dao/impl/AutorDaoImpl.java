package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Autor;
import dao.iface.IAutorDao;
import util.ConnectionTest;

public class AutorDaoImpl implements IAutorDao {

	@Override
	public void criar(Autor obj) {
		try {
			String query = "insert into autores(nomeautor, pseud, paisnasc, anonasc) values (?, ?, ?, ?)";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setString(1, obj.getNomeautor());
			pstmt.setBoolean(2, obj.isPseud());
			pstmt.setString(3, obj.getPaisnasc());
			pstmt.setInt(4, obj.getAnonasc());
			pstmt.execute();
		} catch (SQLException e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public Autor ler(long id) {
		Autor autor = new Autor();
		try {
			String query = "select * from autores where id_autor = ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setObject(1, id);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				// (nomeautor, pseud, paisnasc, anonasc)
				autor.setNomeautor(result.getString(2));
				autor.setPseud(result.getBoolean(3));
				autor.setPaisnasc(result.getString(4));
				autor.setAnonasc(result.getInt(5));
				autor.setIdAutor(id);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return autor;
	}

	@Override
	public ArrayList<Autor> listar() {
		ArrayList<Autor> autores = new ArrayList<Autor>();
		try {
			String query = "select id_autor from autores order by id_autor";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			long l;
			while (result.next()) {
				l = result.getLong(1);
				autores.add(ler(l));
				//System.out.println(ler(l));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return autores;
	}

	@Override
	public ArrayList<Autor> buscar(String txt) {
		ArrayList<Autor> autores = new ArrayList<Autor>();
		txt = "%" + txt + "%";
		try {
			String query = "select * from autores where nomeautor like ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setString(1, txt);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Autor autorTemp = new Autor();
				// (nomeautor, pseud, paisnasc, anonasc)
				autorTemp.setNomeautor(result.getString(2));
				autorTemp.setPseud(result.getBoolean(3));
				autorTemp.setPaisnasc(result.getString(4));
				autorTemp.setAnonasc(result.getInt(5));
				autorTemp.setIdAutor(result.getLong(1));
				autores.add(autorTemp);
				//System.out.println(autorTemp.getIdautor() + " - " + autorTemp);
			}
			//System.out.println("----");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return autores;
	}

	@Override
	public void atualizar(Autor obj) {
		try {
			// (nomeautor, pseud, paisnasc, anonasc)
			String query = "update autores set nomeautor = ?, pseud = ?, paisnasc = ?, anonasc = ? where id_autor = ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setString(1, obj.getNomeautor());
			pstmt.setBoolean(2, obj.isPseud());
			pstmt.setString(3, obj.getPaisnasc());
			pstmt.setInt(4, obj.getAnonasc());
			pstmt.setObject(5, obj.getIdAutor());
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deletar(long id) {
		try {
			String query = "delete from autores where id_autor = ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setObject(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
