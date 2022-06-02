package view;

import controller.AutorController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AutorWindow {

	private static final int windowWidth = 500;
	private static final int windowHeight = 400;
	
	private AutorController ac = new AutorController();
	
	public AutorWindow(Stage stage) {
		VBox boxGeral = new VBox();
		
		// Elementos topo
		HBox boxTopo = new HBox();
		Button btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(event -> voltarAction(stage));
		// Campo e label para busca
		Label lblBuscar = new Label("Buscar por nome:\t");
		lblBuscar.setPadding(new Insets(3, 5, 0, 0));
		ac.addBuscaItem();
		
		// Ajustes na HBox topo
		Region reg = new Region();
		HBox.setHgrow(reg, Priority.ALWAYS);
		boxTopo.setPadding(new Insets(10, 10, 10, 10));
		boxTopo.getChildren().addAll(btnVoltar, reg, lblBuscar, ac.getTxtBusca());
		
		
		// Elementos bottom 1 - Labels e campos
		GridPane panelCampos = new GridPane();
		panelCampos.setPadding(new Insets(10, 10, 10, 10));
		String[] nomesLabels = new String[] { "Nome:\t\t", "Pseud.:\t\t", "Pais:\t\t\t", "Ano de nasc.:\t" };
		for (int i = 0; i < nomesLabels.length; i++) {
			Label lblTemp = new Label(nomesLabels[i]);
			ac.getTxtCampos()[i] = new TextField();
			HBox boxLabelField = new HBox();
			lblTemp.setPadding(new Insets(3, 5, 0, 0));
			boxLabelField.getChildren().addAll(lblTemp, ac.getTxtCampos()[i]);
			boxLabelField.setPadding(new Insets(3, 3, 3, 3));
			panelCampos.add(boxLabelField, 0, i);
		}
		// Desabilitar campos
		ac.mudarEstadoCampos(Color.LIGHTGRAY, false);
		// Elementos bottom 2 - Botoes laterais
		ac.getBoxBtnIniciais().getChildren().get(1).setDisable(true);
		ac.getBoxBtnIniciais().getChildren().get(2).setDisable(true);
		ac.getBoxCamposBotoes().getChildren().addAll(panelCampos, ac.getBoxBtnIniciais());
		ac.getBoxCamposBotoes().setAlignment(Pos.CENTER);
		// Topo. tabela e bottom
		boxGeral.setPadding(new Insets(10, 10, 10, 10));
		boxGeral.getChildren().addAll(boxTopo, ac.getTable(), ac.getBoxCamposBotoes());
		
		
		// Scene e stage
		Scene scn = new Scene(boxGeral, windowWidth, windowHeight);
		stage.setScene(scn);
		stage.setTitle("Livraria - CRUD Autores");
		stage.show();
	}
	
	public static int getWindowWidth() {
		return AutorWindow.windowWidth;
	}
	
	public static int getWindowHeight() {
		return AutorWindow.windowHeight;
	}
	
	private void voltarAction(Stage stage) {
		_MainWindow mw = new _MainWindow(stage);
	}
}
