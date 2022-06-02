package controller;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.Autor;
import service.AutorService;
import view.AutorWindow;

public class AutorController {

	private TableView<Autor> tabela = new TableView<Autor>();
	private Autor autor;
	private long idSelect = -1;
	
	// CAMPOS, PARTE 1
	private TextField[] txtCampos = new TextField[4];
	private TextField txtBusca = new TextField();
	
	// Para uso de getChildren().remove()
	private HBox boxCamposBotoes = new HBox();
	private VBox boxBtnIniciais = addBotoesIniciais();
	private VBox boxBtnSalvarCancelar = addBotoesSalvarCancelar();
	
	// Para uso de setVisible()
	private Button btnSalvar;
	private Button btnSalvarAlt;
	
	private StringProperty sp = new SimpleStringProperty("");
	
	public AutorController() {
		tabela.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		tabela.setPrefSize(AutorWindow.getWindowWidth(), AutorWindow.getWindowHeight()/2);
		
		// CAMPOS, PARTE 2 - COLUNAS DOS ATRIBUTOS
		String[] nomesColunas = new String[] { "ID", "Nome", "Pseudonimo", "Pais", "Ano de nascimento" };
		String[] nomesAtributos = new String[] { "idAutor", "nomeautor", "pseud", "paisnasc", "anonasc" };
		for (int i = 0; i < nomesColunas.length; i++) {
			TableColumn<Autor, String> coluna = new TableColumn<Autor, String>(nomesColunas[i]);
			coluna.setCellValueFactory(new PropertyValueFactory<Autor, String>(nomesAtributos[i]));
			tabela.getColumns().add(coluna);
		}
		
		// Observable e selecao de linhas
		ObservableList<?> observList = tabela.getSelectionModel().getSelectedCells();
        addSelecaoLinha(observList);
        
        // Exibir os registros
        atualizarTabela(AutorService.listarAutores());
	}
	
	public TableView getTable() {
        return tabela;
    }
	
	public TextField[] getTxtCampos() {
		return txtCampos;
	}

	public TextField getTxtBusca() {
		return txtBusca;
	}

	public HBox getBoxCamposBotoes() {
		return boxCamposBotoes;
	}

	public VBox getBoxBtnIniciais() {
		return boxBtnIniciais;
	}

	public VBox getBoxBtnSalvarCancelar() {
		return boxBtnSalvarCancelar;
	}

	public Button getBtnSalvar() {
		return btnSalvar;
	}

	public Button getBtnSalvarAlt() {
		return btnSalvarAlt;
	}
	
	public void addBuscaItem() {
		txtBusca.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (txtBusca.getText() == null || txtBusca.getText().equals("")) {
					atualizarTabela(AutorService.listarAutores());
					idSelect = -1;
					atualizarCampos();
				} else {
					atualizarTabela(AutorService.buscarAutores(txtBusca.getText()));
				}
			}
		});
	}

	private void atualizarTabela(ArrayList<Autor> autores) {
		tabela.setItems(FXCollections.observableArrayList(autores));
	}

	private void addSelecaoLinha(ObservableList<?> obsList) {
		obsList.addListener(new ListChangeListener<Object>() {
            @Override
			public void onChanged(Change<?> c) {
            	if (!obsList.isEmpty()) {
            		TablePosition<?, ?> tabelaPos = (TablePosition<?, ?>) obsList.get(0);
                    autor = (Autor) tabelaPos.getTableView().getItems().get(tabelaPos.getRow());
                    idSelect = autor.getIdAutor().longValue();
                    atualizarCampos();
            	}
            }
        });
	}
	
	private void atualizarCampos() {
		if (idSelect == -1) {
			// Nao ha elemento selecionado
			preencherCampos("", "", "", "");
			boxBtnIniciais.getChildren().get(1).setDisable(true);
			boxBtnIniciais.getChildren().get(2).setDisable(true);
			txtBusca.clear();
		} else {
			// Elemento selecionado
			preencherCampos(
	        	autor.getNomeautor(),
	        	String.valueOf(autor.isPseud()),
	        	autor.getPaisnasc(),
	        	autor.getAnonasc() + ""
	        );
			boxBtnIniciais.getChildren().get(1).setDisable(false);
            boxBtnIniciais.getChildren().get(2).setDisable(false);
		}
	}
	
	private void preencherCampos(String... campos) {
		for (int i = 0; i < txtCampos.length; i++) {
			txtCampos[i].setText(campos[i]);
		}
	}

	private VBox addBotoesIniciais() {
		Button btnNovo = new Button("Criar");
		btnNovo.setOnAction(event -> novoAction());
		Button btnAlterar = new Button("Alterar");
		btnAlterar.setOnAction(event -> alterarAction());
		Button btnExcluir = new Button("Excluir");
		btnExcluir.setOnAction(event -> excluirAction());
		VBox boxBtns = new VBox();
		boxBtns.setPadding(new Insets(10, 10, 10, 10));
		boxBtns.setSpacing(10);
		boxBtns.getChildren().addAll(btnNovo, btnAlterar, btnExcluir);
		return boxBtns;
	}
	
	private VBox addBotoesSalvarCancelar() {
		// Botoes para salvar (novo e alterar)
		btnSalvar = new Button("Salvar");
		btnSalvar.setOnAction(event -> salvarAction());
		btnSalvarAlt = new Button("Salvar alteracoes");
		btnSalvarAlt.setOnAction(event -> salvarAltAction());
		
		// Botao cancelar
		Button btnCancelar = new Button("Cancelar");
		btnCancelar.setOnAction(event -> cancelarAction());
		
		// Grupo de botoes na vertical
		VBox boxBtns = new VBox();
		boxBtns.setPadding(new Insets(10, 10, 10, 10));
		boxBtns.setSpacing(10);
		boxBtns.getChildren().addAll(btnSalvar, btnSalvarAlt, btnCancelar);
		return boxBtns;
	}
	
	public void mudarEstadoCampos(Color c, boolean editavel) {
		Paint p = Paint.valueOf(Integer.toHexString(c.hashCode()));
		for (int i = 0; i < txtCampos.length; i++) {
			txtCampos[i].setStyle("-fx-control-inner-background: #" + p.toString().substring(2));
			txtCampos[i].setEditable(editavel);
		}
	}
	
	private void novoAction() {
		idSelect = -1;
		atualizarCampos();
		atualizarTabela(AutorService.listarAutores());
		prepararTabelaAndCampos();
		btnSalvarAlt.setVisible(false);
	}
	
	private void alterarAction() {
		if (idSelect >= 1) {
			prepararTabelaAndCampos();
			btnSalvar.setVisible(false);
		}
	}
	
	private void excluirAction() {
		if (idSelect >= 1) {
			AutorService.deletarAutor(idSelect);
			idSelect = -1;
			atualizarCampos();
			atualizarTabela(AutorService.listarAutores());
		}
	}
	
	private void salvarAction() {
		autor = new Autor();
		prepararObjeto();
		AutorService.criarAutor(autor);
		concluirAcoes();
	}
	
	private void salvarAltAction() {
		if (idSelect >= 1) autor = AutorService.lerAutor(idSelect);
		prepararObjeto();
		AutorService.atualizarAutor(autor);
		concluirAcoes();
	}
	
	private void cancelarAction() {
		concluirAcoes();
	}
	
	private void prepararTabelaAndCampos() {
		tabela.setDisable(true);
		txtBusca.setDisable(true);
		mudarEstadoCampos(Color.WHITE, true);
		boxCamposBotoes.getChildren().remove(1);
		boxCamposBotoes.getChildren().add(boxBtnSalvarCancelar);
		txtCampos[0].requestFocus();
	}
	
	// CAMPOS, PARTE 3 - PROCESSO ANTES DE SALVAR
	private void prepararObjeto() {
		autor.setNomeautor(txtCampos[0].getText());
		autor.setPseud(Boolean.valueOf(txtCampos[1].getText()));
		autor.setPaisnasc(txtCampos[2].getText());
		autor.setAnonasc(Integer.parseInt(txtCampos[3].getText()));
	}
	
	private void concluirAcoes() {
		// Remover selecao
		idSelect = -1;
		atualizarCampos();
		
		// Recuperar botoes laterais
		boxCamposBotoes.getChildren().remove(1);
		boxCamposBotoes.getChildren().add(boxBtnIniciais);
		
		// Retornar ao estado original
		mudarEstadoCampos(Color.LIGHTGRAY, false);
		atualizarTabela(AutorService.listarAutores());
		tabela.setDisable(false);
		txtBusca.setDisable(false);
		btnSalvar.setVisible(true);
		btnSalvarAlt.setVisible(true);
	}
}
