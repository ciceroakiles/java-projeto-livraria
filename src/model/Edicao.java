package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "edicoes")
public class Edicao {

	@Id
	@Column(name = "isbn")
	@NotNull
	private String isbn;
	
	@Column(name = "preco")
	@NotNull
	private double preco;
	
	@Column(name = "anoedicao")
	@NotNull
	private int anoedicao;
	
	@Column(name = "numpaginas")
	@NotNull
	private int numpaginas;
	
	@Column(name = "qtdeestoque")
	@NotNull
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
