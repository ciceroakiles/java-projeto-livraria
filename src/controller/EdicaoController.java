package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.Edicao;
import model.Livro;
import service.EdicaoService;
import service.LivroService;
import view.EdicaoWindow;

public class EdicaoController {

	private TableView<Edicao> tabela = new TableView<Edicao>();
	private Edicao edicao;
	private String idSelect = "";
	
	// CAMPOS, PARTE 1
	private TextField[] txtCampos = new TextField[5];
	
	// Para uso de getChildren().remove()
	private HBox boxCamposBotoes = new HBox();
	private VBox boxBtnIniciais = addBotoesIniciais();
	private VBox boxBtnSalvarCancelar = addBotoesSalvarCancelar();
	
	// Para uso de setVisible()
	private Button btnSalvar;
	private Button btnSalvarAlt;
	
	public EdicaoController() {
		tabela.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		tabela.setPrefSize(EdicaoWindow.getWindowWidth(), EdicaoWindow.getWindowHeight()/2);
		
		// CAMPOS, PARTE 2 - COLUNAS DOS ATRIBUTOS
		String[] nomesColunas = new String[] { "ISBN", "Preco", "Ano", "Paginas", "Qtde." };
		String[] nomesAtributos = new String[] { "isbn", "preco", "anoedicao", "numpaginas", "qtdeestoque" };
		for (int i = 0; i < nomesColunas.length; i++) {
			TableColumn<Edicao, String> coluna = new TableColumn<Edicao, String>(nomesColunas[i]);
			coluna.setCellValueFactory(new PropertyValueFactory<Edicao, String>(nomesAtributos[i]));
			tabela.getColumns().add(coluna);
		}
		
		// Observable e selecao de linhas
		ObservableList<?> observList = tabela.getSelectionModel().getSelectedCells();
        addSelecaoLinha(observList);
        
        // Exibir os registros
        atualizarTabela(EdicaoService.listarEdicoes());
	}
	
	public TableView getTable() {
        return tabela;
    }
	
	public TextField[] getTxtCampos() {
		return txtCampos;
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
	
	private void atualizarTabela(ArrayList<Edicao> edicoes) {
		tabela.setItems(FXCollections.observableArrayList(edicoes));
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
                    edicao = (Edicao) tabelaPos.getTableView().getItems().get(tabelaPos.getRow());
                    idSelect = edicao.getIsbn();
                    //System.out.println("ID selecionado: " + idSelect);
                    atualizarCampos();
            	}
            }
        });
	}
	
	private void atualizarCampos() {
		if (idSelect.equals("")) {
			// Nao ha elemento selecionado
			preencherCampos("", "", "", "", "");
			boxBtnIniciais.getChildren().get(1).setDisable(true);
			boxBtnIniciais.getChildren().get(2).setDisable(true);
		} else {
			// Elemento selecionado
			preencherCampos(
	        	edicao.getIsbn(),
	        	(edicao.getPreco() + ""),
	        	(edicao.getAnoedicao() + ""),
	        	(edicao.getNumpaginas() + ""),
	        	(edicao.getQtdeestoque() + "")
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
		idSelect = "";
		atualizarCampos();
		atualizarTabela(EdicaoService.listarEdicoes());
		prepararTabelaAndCampos();
		btnSalvarAlt.setVisible(false);
	}
	
	private void alterarAction() {
		if (!idSelect.equals("")) {
			prepararTabelaAndCampos();
			btnSalvar.setVisible(false);
		}
	}
	
	private void excluirAction() {
		if (!idSelect.equals("")) {
			EdicaoService.deletarEdicao(idSelect);
			idSelect = "";
			atualizarCampos();
			atualizarTabela(EdicaoService.listarEdicoes());
		}
	}
	
	private void salvarAction() {
		edicao = new Edicao();
		prepararObjeto();
		EdicaoService.criarEdicao(edicao);
		concluirAcoes();
	}
	
	private void salvarAltAction() {
		if (!idSelect.equals("")) edicao = EdicaoService.lerEdicao(idSelect);
		prepararObjeto();
		EdicaoService.atualizarEdicao(edicao);
		concluirAcoes();
	}
	
	private void cancelarAction() {
		concluirAcoes();
	}
	
	private void prepararTabelaAndCampos() {
		tabela.setDisable(true);
		mudarEstadoCampos(Color.WHITE, true);
		boxCamposBotoes.getChildren().remove(1);
		boxCamposBotoes.getChildren().add(boxBtnSalvarCancelar);
		txtCampos[0].requestFocus();
	}
	
	// CAMPOS, PARTE 3 - PROCESSO ANTES DE SALVAR
	private void prepararObjeto() {
		edicao.setIsbn(txtCampos[0].getText());
		edicao.setPreco(Double.parseDouble(txtCampos[1].getText()));
		edicao.setAnoedicao(Integer.parseInt(txtCampos[2].getText()));
		edicao.setNumpaginas(Integer.parseInt(txtCampos[3].getText()));
		edicao.setQtdeestoque(Integer.parseInt(txtCampos[4].getText()));
	}
	
	private void concluirAcoes() {
		// Remover selecao
		idSelect = "";
		atualizarCampos();
		
		// Recuperar botoes laterais
		boxCamposBotoes.getChildren().remove(1);
		boxCamposBotoes.getChildren().add(boxBtnIniciais);
		
		// Retornar ao estado original
		mudarEstadoCampos(Color.LIGHTGRAY, false);
		atualizarTabela(EdicaoService.listarEdicoes());
		tabela.setDisable(false);
		btnSalvar.setVisible(true);
		btnSalvarAlt.setVisible(true);
	}
}
