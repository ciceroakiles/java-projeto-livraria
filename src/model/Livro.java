package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "livros")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_livro")
	@NotNull
	private int idLivro;
	
	@Column(name = "titulolivro", length = 100)
	@NotNull
	private String titulolivro;
	
	@Column(name = "idioma", length = 50)
	@NotNull
	private String idioma;
	
	@Column(name = "genero", length = 50)
	private String genero;
	
	@Column(name = "anolivro")
	private int anolivro;
	
	public int getIdLivro() {
		return idLivro;
	}
	public void setIdlivro(int idLivro) {
		this.idLivro = idLivro;
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
