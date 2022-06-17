package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "editoras")
public class Editora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_editora")
	@NotNull
	private int idEditora;
	
	@Column(name = "nomeeditora", length = 100)
	@NotNull
	private String nomeeditora;
	
	@Column(name = "logradendereco", length = 255)
	@NotNull
	private String logradendereco;
	
	@Column(name = "numendereco")
	private String numendereco;
	
	@Column(name = "complendereco")
	private String complendereco;
	
	@Column(name = "cependereco")
	@NotNull
	private String cependereco;
	
	@Column(name = "telefone", length = 20)
	@NotNull
	private String telefone;
	
	public int getIdEditora() {
		return idEditora;
	}
	public void setIdEditora(int idEditora) {
		this.idEditora = idEditora;
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
