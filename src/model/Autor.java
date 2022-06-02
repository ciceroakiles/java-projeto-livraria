package model;

import java.math.BigInteger;

public class Autor {

	private BigInteger idAutor;
	private String nomeautor;
	private boolean pseud;
	private String paisnasc;
	private int anonasc;
	
	public BigInteger getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(long idAutor) {
		this.idAutor = BigInteger.valueOf(idAutor);
	}
	public String getNomeautor() {
		return nomeautor;
	}
	public void setNomeautor(String nomeautor) {
		this.nomeautor = nomeautor;
	}
	public boolean isPseud() {
		return pseud;
	}
	public void setPseud(boolean pseud) {
		this.pseud = pseud;
	}
	public String getPaisnasc() {
		return paisnasc;
	}
	public void setPaisnasc(String paisnasc) {
		this.paisnasc = paisnasc;
	}
	public int getAnonasc() {
		return anonasc;
	}
	public void setAnonasc(int anonasc) {
		this.anonasc = anonasc;
	}
	
	@Override
	public String toString() {
		return this.nomeautor +
			", " + this.pseud +
			", " + this.paisnasc +
			", " + this.anonasc;
	}
}
