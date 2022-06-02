package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class _MainWindow {

	private final int windowWidth = 300;
	private final int windowHeight = 200;
	
	public _MainWindow(Stage stage) {
		// Panel
		FlowPane panelBtns = new FlowPane();
		panelBtns.setVgap(40);
		panelBtns.setHgap(40);
		
		// Botoes
		Button btnLivros = new Button("Livros");
		btnLivros.setOnAction(event -> livrosAction(stage));
		Button btnAutores = new Button("Autores");
		btnAutores.setOnAction(event -> autoresAction(stage));
		Button btnEditoras = new Button("Editoras");
		btnEditoras.setOnAction(event -> editorasAction(stage));
		Button btnEdicoes = new Button("Edicoes");
		btnEdicoes.setOnAction(event -> edicoesAction(stage));
		
		// Inclusao
		panelBtns.setPadding(new Insets(50, 50, 50, 50));
		panelBtns.getChildren().addAll(btnLivros, btnAutores, btnEditoras, btnEdicoes);
		
		// Scene
		Scene scn = new Scene(panelBtns, windowWidth, windowHeight);
		stage.setScene(scn);
		stage.setTitle("Livraria - Pagina principal");
		stage.show();
	}

	private void livrosAction(Stage stage) {
		LivroWindow livroWin = new LivroWindow(stage);
	}

	private void autoresAction(Stage stage) {
		AutorWindow autorWin = new AutorWindow(stage);
	}
	
	private void editorasAction(Stage stage) {
		EditoraWindow editoraWin = new EditoraWindow(stage);
	}

	private void edicoesAction(Stage stage) {
		EdicaoWindow edicaoWin = new EdicaoWindow(stage);
	}
}
