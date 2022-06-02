package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	private static Connection db_connection = createConnection();
	private static final String url =  "jdbc:postgresql://localhost:5433/livraria"; //"jdbc:mysql://localhost:3306/livraria";
	private static final String user = "usuariobd"; //"postgres"; //"root";
	private static final String pass = "faculdadeAtrapalhaMeuTrabalho"; //"usuariobd"; //"alunofatec";
	
	private static Connection createConnection() {
		Connection ct = null;
		try {
			ct = DriverManager.getConnection(url, user, pass);
			if (ct != null) {
				//System.out.println("[Sucesso] Conexao com o banco de dados OK");
			} else {
				System.out.println("[Falha] Erro na conexao com o banco de dados");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return ct;
	}
	
	public static Connection getConnection() {
		return db_connection;
	}
}
