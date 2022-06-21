package view;

import org.hibernate.SessionFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import util.DadosIniciais;
import util.HibernateUtil;

public class Main extends Application {

	private SessionFactory preload = HibernateUtil.getSessionFactory();
	private boolean dadosIniciais = true;
	
	@Override
	public void start(Stage stage) {
		if (dadosIniciais) DadosIniciais.inserirDadosIniciais();
		_MainWindow mw = new _MainWindow(stage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
