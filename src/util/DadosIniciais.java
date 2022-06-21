package util;

import dao.impl.AutorDaoImpl;
import dao.impl.EdicaoDaoImpl;
import dao.impl.EditoraDaoImpl;
import dao.impl.LivroDaoImpl;
import model.Autor;
import model.Edicao;
import model.Editora;
import model.Livro;

public class DadosIniciais {

	public static void inserirDadosIniciais() {
		LivroDaoImpl lDao = new LivroDaoImpl();
		AutorDaoImpl aDao = new AutorDaoImpl();
		EditoraDaoImpl etDao = new EditoraDaoImpl();
		EdicaoDaoImpl ecDao = new EdicaoDaoImpl();
		
		Livro livro1 = new Livro();
		livro1.setTitulolivro("Título não informado");
		livro1.setIdioma("Idioma desconhecido");
		livro1.setGenero("Gênero não informado");
		livro1.setAnolivro(0);
		lDao.criar(livro1);
		
		Livro livro2 = new Livro();
		livro2.setTitulolivro("A Injustiça do Monopólio Postal");
		livro2.setIdioma("Português");
		livro2.setGenero("");
		livro2.setAnolivro(2010);
		lDao.criar(livro2);
		
		Livro livro3 = new Livro();
		livro3.setTitulolivro("Brilhos: Seleção de Textos para Reflexão");
		livro3.setIdioma("Português");
		livro3.setGenero("");
		livro3.setAnolivro(2004);
		lDao.criar(livro3);
		
		Livro livro4 = new Livro();
		livro4.setTitulolivro("A República");
		livro4.setIdioma("Grego");
		livro4.setGenero("Humanidades e ciências sociais");
		livro4.setAnolivro(0);
		lDao.criar(livro4);
		
		Livro livro5 = new Livro();
		livro5.setTitulolivro("A Flecha de Ouro");
		livro5.setIdioma("Inglês");
		livro5.setGenero("Romance");
		livro5.setAnolivro(1919);
		lDao.criar(livro5);
		
		Autor autor1 = new Autor();
		autor1.setNomeautor("Autor desconhecido");
		autor1.setPseud(false);
		autor1.setPaisnasc("País desconhecido");
		autor1.setAnonasc(0);
		aDao.criar(autor1);
		
		Autor autor2 = new Autor();
		autor2.setNomeautor("Platão");
		autor2.setPseud(false);
		autor2.setPaisnasc("Grécia");
		autor2.setAnonasc(0);
		aDao.criar(autor2);
		
		Autor autor3 = new Autor();
		autor3.setNomeautor("Joseph Conrad");
		autor3.setPseud(true);
		autor3.setPaisnasc("Ucrânia");
		autor3.setAnonasc(1857);
		aDao.criar(autor3);
		
		Autor autor4 = new Autor();
		autor4.setNomeautor("José de Alencar");
		autor4.setPseud(false);
		autor4.setPaisnasc("Brasil");
		autor4.setAnonasc(1829);
		aDao.criar(autor4);
		
		Autor autor5 = new Autor();
		autor5.setNomeautor("Dante Alighieri");
		autor5.setPseud(false);
		autor5.setPaisnasc("Itália");
		autor5.setAnonasc(1265);
		aDao.criar(autor5);
		
		Editora editora1 = new Editora();
		editora1.setNomeeditora("L&PM");
		editora1.setLogradendereco("Rua Comendador Coruja");
		editora1.setNumendereco("314");
		editora1.setComplendereco("Loja 9 - Floresta");
		editora1.setCependereco("90220180");
		editora1.setTelefone("(51)32255777");
		etDao.criar(editora1);
		
		Editora editora2 = new Editora();
		editora2.setNomeeditora("Editora Pensamento");
		editora2.setLogradendereco("Rua Dr. Mário Vicente");
		editora2.setNumendereco("374");
		editora2.setComplendereco("");
		editora2.setCependereco("04270000");
		editora2.setTelefone("(63)31410000");
		etDao.criar(editora2);
		
		Edicao edicao1 = new Edicao();
		edicao1.setIsbn("9788525410733");
		edicao1.setPreco(15.50);
		edicao1.setAnoedicao(2008);
		edicao1.setNumpaginas(304);
		edicao1.setQtdeestoque(12);
		ecDao.criar(edicao1);
		
		Edicao edicao2 = new Edicao();
		edicao2.setIsbn("8533616252");
		edicao2.setPreco(20.00);
		edicao2.setAnoedicao(2002);
		edicao2.setNumpaginas(202);
		edicao2.setQtdeestoque(10);
		ecDao.criar(edicao2);
	}
}
