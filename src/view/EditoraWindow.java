package view;

import controller.EditoraController;
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

public class EditoraWindow {

	private static final int windowWidth = 700;
	private static final int windowHeight = 500;
	
	private EditoraController ec = new EditoraController();
	
	public EditoraWindow(Stage stage) {
		VBox boxGeral = new VBox();
		
		// Elementos topo
		HBox boxTopo = new HBox();
		Button btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(event -> voltarAction(stage));
		// Campo e label para busca
		Label lblBuscar = new Label("Buscar por nome:\t");
		lblBuscar.setPadding(new Insets(3, 5, 0, 0));
		ec.addBuscaItem();
		
		// Ajustes na HBox topo
		Region reg = new Region();
		HBox.setHgrow(reg, Priority.ALWAYS);
		boxTopo.setPadding(new Insets(10, 10, 10, 10));
		boxTopo.getChildren().addAll(btnVoltar, reg, lblBuscar, ec.getTxtBusca());
		
		
		// Elementos bottom 1 - Labels e campos
		GridPane panelCampos = new GridPane();
		panelCampos.setPadding(new Insets(10, 10, 10, 10));
		String[] nomesLabels = new String[] { "Nome da editora:\t", "Logradouro:\t\t", "Numero:\t\t\t", "Complemento:\t\t", "CEP:\t\t\t\t", "Telefone:\t\t\t" };
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
		stage.setTitle("Livraria - CRUD Editoras");
		stage.show();
	}

	public static int getWindowWidth() {
		return EditoraWindow.windowWidth;
	}
	
	public static int getWindowHeight() {
		return EditoraWindow.windowHeight;
	}
	
	private void voltarAction(Stage stage) {
		_MainWindow mw = new _MainWindow(stage);
	}
}
