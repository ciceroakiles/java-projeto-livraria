package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "autores")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_autor")
	@NotNull
	private int idAutor;
	
	@Column(name = "nomeautor", length = 100)
	@NotNull
	private String nomeautor;
	
	@Column(name = "pseud")
	@NotNull
	private boolean pseud;
	
	@Column(name = "paisnasc", length = 50)
	private String paisnasc;
	
	@Column(name = "anonasc")
	private int anonasc;
	
	public int getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
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
