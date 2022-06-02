package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Edicao;
import dao.iface.IEdicaoDao;
import util.ConnectionTest;

public class EdicaoDaoImpl implements IEdicaoDao {

	@Override
	public void criar(Edicao obj) {
		try {
			String query = "insert into edicoes(isbn, preco, anoedicao, numpaginas, qtdeestoque) values (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setString(1, obj.getIsbn());
			pstmt.setDouble(2, obj.getPreco());
			pstmt.setInt(3, obj.getAnoedicao());
			pstmt.setInt(4, obj.getNumpaginas());
			pstmt.setInt(5, obj.getQtdeestoque());
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Edicao ler(String txt) {
		Edicao edicao = new Edicao();
		try {
			String query = "select * from edicoes where isbn = ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setObject(1, txt);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				// (isbn, preco, anoedicao, numpaginas, qtdeestoque)
				edicao.setPreco(result.getDouble(2));
				edicao.setAnoedicao(result.getInt(3));
				edicao.setNumpaginas(result.getInt(4));
				edicao.setQtdeestoque(result.getInt(5));
				edicao.setIsbn(txt);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return edicao;
	}

	@Override
	public ArrayList<Edicao> listar() {
		ArrayList<Edicao> edicoes = new ArrayList<Edicao>();
		try {
			String query = "select isbn from edicoes order by isbn";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			String txt;
			while (result.next()) {
				txt = result.getString(1);
				edicoes.add(ler(txt));
				//System.out.println(ler(txt));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return edicoes;
	}

	@Override
	public void atualizar(Edicao obj) {
		try {
			// (isbn, preco, anoedicao, numpaginas, qtdeestoque)
			String query = "update edicoes set preco = ?, anoedicao = ?, numpaginas = ?, qtdeestoque = ? where isbn = ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setDouble(1, obj.getPreco());
			pstmt.setInt(2, obj.getAnoedicao());
			pstmt.setInt(3, obj.getNumpaginas());
			pstmt.setInt(4, obj.getQtdeestoque());
			pstmt.setString(5, obj.getIsbn());
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deletar(String txt) {
		try {
			String query = "delete from edicoes where isbn = ?";
			PreparedStatement pstmt = ConnectionTest.getConnection().prepareStatement(query);
			pstmt.setString(1, txt);
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
