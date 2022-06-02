package model;

import java.math.BigInteger;

public class Editora {

	private BigInteger idEditora;
	private String nomeeditora;
	private String logradendereco;
	private String numendereco;
	private String complendereco;
	private String cependereco;
	private String telefone;
	
	public BigInteger getIdEditora() {
		return idEditora;
	}
	public void setIdEditora(long idEditora) {
		this.idEditora = BigInteger.valueOf(idEditora);
	}
	public String getNomeeditora() {
		return nomeeditora;
	}
	public void setNomeeditora(String nomeeditora) {
		this.nomeeditora = nomeeditora;
	}
	public String getLogradendereco() {
		return logradendereco;
	}
	public void setLogradendereco(String logradendereco) {
		this.logradendereco = logradendereco;
	}
	public String getNumendereco() {
		return numendereco;
	}
	public void setNumendereco(String numendereco) {
		this.numendereco = numendereco;
	}
	public String getComplendereco() {
		return complendereco;
	}
	public void setComplendereco(String complendereco) {
		this.complendereco = complendereco;
	}
	public String getCependereco() {
		return cependereco;
	}
	public void setCependereco(String cependereco) {
		this.cependereco = cependereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return this.nomeeditora +
			", " + this.logradendereco +
			", " + this.numendereco +
			", " + this.complendereco +
			", " + this.cependereco +
			", " + this.telefone;
	}
}
