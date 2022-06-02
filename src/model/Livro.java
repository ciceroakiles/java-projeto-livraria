package model;

import java.math.BigInteger;

public class Livro {

	private BigInteger idLivro;
	private String titulolivro;
	private String idioma;
	private String genero;
	private int anolivro;
	
	public BigInteger getIdLivro() {
		return idLivro;
	}
	public void setIdlivro(long idLivro) {
		this.idLivro = BigInteger.valueOf(idLivro);
	}
	public String getTitulolivro() {
		return titulolivro;
	}
	public void setTitulolivro(String titulolivro) {
		this.titulolivro = titulolivro;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getAnolivro() {
		return anolivro;
	}
	public void setAnolivro(int anolivro) {
		this.anolivro = anolivro;
	}
	
	@Override
	public String toString() {
		return this.titulolivro +
			", " + this.idioma +
			", " + this.genero +
			", " + this.anolivro;
	}
}
