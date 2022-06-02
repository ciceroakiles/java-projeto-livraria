package model;

public class Edicao {

	private String isbn;
	private double preco;
	private int anoedicao;
	private int numpaginas;
	private int qtdeestoque;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getAnoedicao() {
		return anoedicao;
	}
	public void setAnoedicao(int anoedicao) {
		this.anoedicao = anoedicao;
	}
	public int getNumpaginas() {
		return numpaginas;
	}
	public void setNumpaginas(int numpaginas) {
		this.numpaginas = numpaginas;
	}
	public int getQtdeestoque() {
		return qtdeestoque;
	}
	public void setQtdeestoque(int qtdeestoque) {
		this.qtdeestoque = qtdeestoque;
	}
	
	@Override
	public String toString() {
		return this.isbn +
			", " + this.preco +
			", " + this.anoedicao +
			", " + this.numpaginas +
			", " + this.qtdeestoque;
	}
}
