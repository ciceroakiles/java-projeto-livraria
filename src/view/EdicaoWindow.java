package view;

import controller.EdicaoController;
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

public class EdicaoWindow {

	private static final int windowWidth = 500;
	private static final int windowHeight = 400;
	
	private EdicaoController ec = new EdicaoController();
	
	public EdicaoWindow(Stage stage) {
		VBox boxGeral = new VBox();
		
		// Elementos topo
		HBox boxTopo = new HBox();
		Button btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(event -> voltarAction(stage));
		
		// Ajustes na HBox topo
		Region reg = new Region();
		HBox.setHgrow(reg, Priority.ALWAYS);
		boxTopo.setPadding(new Insets(10, 10, 10, 10));
		boxTopo.getChildren().addAll(btnVoltar, reg);
		
		
		// Elementos bottom 1 - Labels e campos
		GridPane panelCampos = new GridPane();
		panelCampos.setPadding(new Insets(10, 10, 10, 10));
		String[] nomesLabels = new String[] { "ISBN:\t", "Preco:\t", "Ano:\t\t", "Paginas:\t", "Qtde.:\t" };
		for (int i = 0; i < nomesLabels.length; i++) {
			Label lblTemp = new Label(nomesLabels[i]);
			ec.getTxtCampos()[i] = new TextField();
			HBox boxLabelField = new HBox();
			lblTemp.setPadding(new Insets(3, 5, 0, 0));
			boxLabelField.getChildren().addAll(lblTemp, ec.getTxtCampos()[i]);
			boxLabelField.setPadding(new Insets(3, 3, 3, 3));
			panelCampos.add(boxLabelField, 0, i);
		}
		// Desabilitar campos
		ec.mudarEstadoCampos(Color.LIGHTGRAY, false);
		// Elementos bottom 2 - Botoes laterais
		ec.getBoxBtnIniciais().getChildren().get(1).setDisable(true);
		ec.getBoxBtnIniciais().getChildren().get(2).setDisable(true);
		ec.getBoxCamposBotoes().getChildren().addAll(panelCampos, ec.getBoxBtnIniciais());
		ec.getBoxCamposBotoes().setAlignment(Pos.CENTER);
		// Topo. tabela e bottom
		boxGeral.setPadding(new Insets(10, 10, 10, 10));
		boxGeral.getChildren().addAll(boxTopo, ec.getTable(), ec.getBoxCamposBotoes());
		
		
		// Scene e stage
		Scene scn = new Scene(boxGeral, windowWidth, windowHeight);
		stage.setScene(scn);
		stage.setTitle("Livraria - CRUD Edicoes");
		stage.show();
	}
	
	public static int getWindowWidth() {
		return EdicaoWindow.windowWidth;
	}
	
	public static int getWindowHeight() {
		return EdicaoWindow.windowHeight;
	}
	
	private void voltarAction(Stage stage) {
		_MainWindow mw = new _MainWindow(stage);
	}
}
