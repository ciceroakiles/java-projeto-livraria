package view;

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
import controller.LivroController;

public class LivroWindow {

	private static final int windowWidth = 700;
	private static final int windowHeight = 400;
	
	private LivroController lc = new LivroController();
	
	public LivroWindow(Stage stage) {
		VBox boxGeral = new VBox();
		
		// Elementos topo
		HBox boxTopo = new HBox();
		Button btnVoltar = new Button("Voltar");
		btnVoltar.setOnAction(event -> voltarAction(stage));
		// Campo e label para busca
		Label lblBuscar = new Label("Buscar por titulo:\t");
		lblBuscar.setPadding(new Insets(3, 5, 0, 0));
		lc.addBuscaItem();
		
		// Ajustes na HBox topo
		Region reg = new Region();
		HBox.setHgrow(reg, Priority.ALWAYS);
		boxTopo.setPadding(new Insets(10, 10, 10, 10));
		boxTopo.getChildren().addAll(btnVoltar, reg, lblBuscar, lc.getTxtBusca());
		
		
		// Elementos bottom 1 - Labels e campos
		GridPane panelCampos = new GridPane();
		panelCampos.setPadding(new Insets(10, 10, 10, 10));
		String[] nomesLabels = new String[] { "Titulo:\t", "Idioma:\t", "Genero:\t", "Ano:\t\t" };
		for (int i = 0; i < nomesLabels.length; i++) {
			Label lblTemp = new Label(nomesLabels[i]);
			lc.getTxtCampos()[i] = new TextField();
			HBox boxLabelField = new HBox();
			lblTemp.setPadding(new Insets(3, 5, 0, 0));
			boxLabelField.getChildren().addAll(lblTemp, lc.getTxtCampos()[i]);
			boxLabelField.setPadding(new Insets(3, 3, 3, 3));
			panelCampos.add(boxLabelField, 0, i);
		}
		// Desabilitar campos
		lc.mudarEstadoCampos(Color.LIGHTGRAY, false);
		// Elementos bottom 2 - Botoes laterais
		lc.getBoxBtnIniciais().getChildren().get(1).setDisable(true);
		lc.getBoxBtnIniciais().getChildren().get(2).setDisable(true);
		lc.getBoxCamposBotoes().getChildren().addAll(panelCampos, lc.getBoxBtnIniciais());
		lc.getBoxCamposBotoes().setAlignment(Pos.CENTER);
		// Topo. tabela e bottom
		boxGeral.setPadding(new Insets(10, 10, 10, 10));
		boxGeral.getChildren().addAll(boxTopo, lc.getTable(), lc.getBoxCamposBotoes());
		
		
		// Scene e stage
		Scene scn = new Scene(boxGeral, windowWidth, windowHeight);
		stage.setScene(scn);
		stage.setTitle("Livraria - CRUD Livros");
		stage.show();
	}
	
	public static int getWindowWidth() {
		return LivroWindow.windowWidth;
	}
	
	public static int getWindowHeight() {
		return LivroWindow.windowHeight;
	}
	
	private void voltarAction(Stage stage) {
		_MainWindow mw = new _MainWindow(stage);
	}
}
