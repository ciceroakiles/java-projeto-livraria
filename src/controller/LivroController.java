package controller;

import java.util.ArrayList;
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
import model.Livro;
import service.LivroService;
import view.LivroWindow;

public class LivroController {

	private TableView<Livro> tabela = new TableView<Livro>();
	private Livro livro;
	private long idSelect = -1;
	
	// CAMPOS, PARTE 1 - txtTitulo, txtIdioma, txtGenero, txtAno
	private TextField[] txtCampos = new TextField[4];
	private TextField txtBusca = new TextField();
	
	// Para uso de getChildren().remove()
	private HBox boxCamposBotoes = new HBox();
	private VBox boxBtnIniciais = addBotoesIniciais();
	private VBox boxBtnSalvarCancelar = addBotoesSalvarCancelar();
	
	// Para uso de setVisible()
	private Button btnSalvar;
	private Button btnSalvarAlt;
	
	public LivroController() {
		tabela.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		tabela.setPrefSize(LivroWindow.getWindowWidth(), LivroWindow.getWindowHeight()/2);
		
		// CAMPOS, PARTE 2 - COLUNAS DOS ATRIBUTOS
		String[] nomesColunas = new String[] { "ID", "Titulo", "Idioma", "Genero", "Ano" };
		String[] nomesAtributos = new String[] { "idLivro", "titulolivro", "idioma", "genero", "anolivro" };
		for (int i = 0; i < nomesColunas.length; i++) {
			TableColumn<Livro, String> coluna = new TableColumn<Livro, String>(nomesColunas[i]);
			coluna.setCellValueFactory(new PropertyValueFactory<Livro, String>(nomesAtributos[i]));
			tabela.getColumns().add(coluna);
		}
		
		// Observable e selecao de linhas
		ObservableList<?> observList = tabela.getSelectionModel().getSelectedCells();
        addSelecaoLinha(observList);
        
        // Exibir os registros
        atualizarTabela(LivroService.listarLivros());
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
					atualizarTabela(LivroService.listarLivros());
					idSelect = -1;
					atualizarCampos();
				} else {
					atualizarTabela(LivroService.buscarLivros(txtBusca.getText()));
				}
			}
		});
	}
	
	private void atualizarTabela(ArrayList<Livro> livros) {
		tabela.setItems(FXCollections.observableArrayList(livros));
	}
	
	private void addSelecaoLinha(ObservableList<?> obsList) {
		obsList.addListener(new ListChangeListener<Object>() {
            @Override
			public void onChanged(Change<?> c) {
            	if (!obsList.isEmpty()) {
            		TablePosition<?, ?> tabelaPos = (TablePosition<?, ?>) obsList.get(0);
                    //String valorCelula = (String) tabelaPos.getTableColumn().getCellData(tabelaPos.getRow());
                    //System.out.println(valorCelula);
            		//System.out.println("Linha: " + tabelaPos.getRow());
                    livro = (Livro) tabelaPos.getTableView().getItems().get(tabelaPos.getRow());
                    idSelect = livro.getIdLivro().longValue();
                    //System.out.println("ID selecionado: " + idSelect);
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
	        	livro.getTitulolivro(),
	        	livro.getIdioma(),
	        	livro.getGenero(),
	        	livro.getAnolivro() + ""
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
		atualizarTabela(LivroService.listarLivros());
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
			LivroService.deletarLivro(idSelect);
			idSelect = -1;
			atualizarCampos();
			atualizarTabela(LivroService.listarLivros());
		}
	}
	
	private void salvarAction() {
		livro = new Livro();
		prepararObjeto();
		LivroService.criarLivro(livro);
		concluirAcoes();
	}
	
	private void salvarAltAction() {
		if (idSelect >= 1) livro = LivroService.lerLivro(idSelect);
		prepararObjeto();
		LivroService.atualizarLivro(livro);
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
		livro.setTitulolivro(txtCampos[0].getText());
		livro.setIdioma(txtCampos[1].getText());
		livro.setGenero(txtCampos[2].getText());
		livro.setAnolivro(Integer.parseInt(txtCampos[3].getText()));
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
		atualizarTabela(LivroService.listarLivros());
		tabela.setDisable(false);
		txtBusca.setDisable(false);
		btnSalvar.setVisible(true);
		btnSalvarAlt.setVisible(true);
	}
}