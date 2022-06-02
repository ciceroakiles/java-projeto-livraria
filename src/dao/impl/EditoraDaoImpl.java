package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Editora;
import dao.iface.IEditoraDao;
import util.ConnectionTest;

public class EditoraDaoImpl implements IEditoraDao {

	@Override
	public void criar(Editora obj) {
		try {
			String query = "insert into editoras(nomeeditora, logradendereco, numendereco, complendereco, cependereco, telefone) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setString(1, obj.getNomeeditora());
			pstmt.setString(2, obj.getLogradendereco());
			pstmt.setString(3, obj.getNumendereco());
			pstmt.setString(4, obj.getComplendereco());
			pstmt.setString(5, obj.getCependereco());
			pstmt.setString(6, obj.getTelefone());
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Editora ler(long id) {
		Editora editora = new Editora();
		try {
			String query = "select * from editoras where id_editora = ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setObject(1, id);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				// (nomeeditora, logradendereco, numendereco, complendereco, cependereco, telefone)
				editora.setNomeeditora(result.getString(2));
				editora.setLogradendereco(result.getString(3));
				editora.setNumendereco(result.getString(4));
				editora.setComplendereco(result.getString(5));
				editora.setCependereco(result.getString(6));
				editora.setTelefone(result.getString(7));
				editora.setIdEditora(id);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return editora;
	}

	@Override
	public ArrayList<Editora> listar() {
		ArrayList<Editora> editoras = new ArrayList<Editora>();
		try {
			String query = "select id_editora from editoras order by id_editora";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			long l;
			while (result.next()) {
				l = result.getLong(1);
				editoras.add(ler(l));
				//System.out.println(ler(l));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return editoras;
	}

	@Override
	public ArrayList<Editora> buscar(String txt) {
		ArrayList<Editora> editoras = new ArrayList<Editora>();
		txt = "%" + txt + "%";
		try {
			String query = "select * from editoras where nomeeditora like ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setString(1, txt);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Editora editoraTemp = new Editora();
				// (nomeeditora, logradendereco, numendereco, complendereco, cependereco, telefone)
				editoraTemp.setNomeeditora(result.getString(2));
				editoraTemp.setLogradendereco(result.getString(3));
				editoraTemp.setNumendereco(result.getString(4));
				editoraTemp.setComplendereco(result.getString(5));
				editoraTemp.setCependereco(result.getString(6));
				editoraTemp.setTelefone(result.getString(7));
				editoraTemp.setIdEditora(result.getLong(1));
				editoras.add(editoraTemp);
				//System.out.println(editoraTemp.getIdeditora() + " - " + editoraTemp);
			}
			//System.out.println("----");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return editoras;
	}

	@Override
	public void atualizar(Editora obj) {
		try {
			// (nomeeditora, logradendereco, numendereco, complendereco, cependereco, telefone)
			String query = "update editoras set nomeeditora = ?, logradendereco = ?, numendereco = ?, complendereco = ?, cependereco = ?, telefone = ? where id_editora = ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setString(1, obj.getNomeeditora());
			pstmt.setString(2, obj.getLogradendereco());
			pstmt.setString(3, obj.getNumendereco());
			pstmt.setString(4, obj.getComplendereco());
			pstmt.setString(5, obj.getCependereco());
			pstmt.setString(6, obj.getTelefone());
			pstmt.setObject(7, obj.getIdEditora());
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deletar(long id) {
		try {
			String query = "delete from editoras where id_editora = ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setObject(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
