package view;

import org.hibernate.SessionFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import util.HibernateUtil;

public class Main extends Application {

	private SessionFactory preload = HibernateUtil.getSessionFactory();
	
	@Override
	public void start(Stage stage) {
		_MainWindow mw = new _MainWindow(stage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
