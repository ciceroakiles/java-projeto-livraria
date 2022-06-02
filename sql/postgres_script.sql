-- DROPs (secundarias)
drop table if exists livroedicaoeditora;
drop table if exists livroautor;

-- DROPs (principais)
drop table if exists edicoes;
drop table if exists editoras;
drop table if exists autores;
drop table if exists livros;

-- TABLEs (principais)
create table if not exists livros (
  id_livro bigserial primary key,
  titulolivro varchar(100) not null,
  idioma varchar(50) not null,
  genero varchar(50),
  anolivro integer
);

create table if not exists autores (
  id_autor bigserial primary key,
  nomeautor varchar(100) not null,
  pseud boolean not null,
  paisnasc varchar(50),
  anonasc integer
);

create table if not exists editoras (
  id_editora bigserial primary key,
  nomeeditora varchar(100) not null,
  logradendereco varchar(255) not null,
  numendereco varchar(10),
  complendereco varchar(50),
  cependereco varchar(8) not null,
  telefone varchar(20) not null
);

create table if not exists edicoes (
  isbn varchar(13) primary key,
  preco numeric(5,2) not null constraint positivo check (preco > 0),
  anoedicao int not null,
  numpaginas integer not null,
  qtdeestoque integer not null
);

-- TABLEs (secundarias)
create table if not exists livroautor (
  id_livro bigint references livros(id_livro) on delete cascade,
  id_autor bigint references autores(id_autor) on delete cascade,
  primary key (id_livro, id_autor)
);

create table if not exists livroedicaoeditora (
  id_livro bigint references livros(id_livro) on delete cascade,
  isbn varchar(13) references edicoes(isbn) on delete cascade,
  id_editora bigint references editoras(id_editora) on delete cascade,
  primary key (id_livro, isbn, id_editora)
);

-- INSERTs (principais)
insert into livros(titulolivro, idioma, genero, anolivro) values ('Título não informado', 'Idioma desconhecido', 'Gênero não informado', null);
insert into livros(titulolivro, idioma, genero, anolivro) values ('A Injustiça do Monopólio Postal', 'Português', null, 2010);
insert into livros(titulolivro, idioma, genero, anolivro) values ('Brilhos: Seleção de Textos para Reflexão', 'Português', null, 2004);
insert into livros(titulolivro, idioma, genero, anolivro) values ('A República', 'Grego', 'Humanidades e ciências sociais', null);
insert into livros(titulolivro, idioma, genero, anolivro) values ('A Flecha de Ouro', 'Inglês', 'Romance', 1919);

insert into autores(nomeautor, pseud, paisnasc, anonasc) values ('Autor desconhecido', false, 'País desconhecido', null);
insert into autores(nomeautor, pseud, paisnasc, anonasc) values ('Platão', false, 'Grécia', null);
insert into autores(nomeautor, pseud, paisnasc, anonasc) values ('Joseph Conrad', true, 'Ucrânia', 1857);
insert into autores(nomeautor, pseud, paisnasc, anonasc) values ('José de Alencar', false, 'Brasil', 1829);
insert into autores(nomeautor, pseud, paisnasc, anonasc) values ('Dante Alighieri', false, 'Itália', 1265);

insert into editoras(nomeeditora, logradendereco, numendereco, complendereco, cependereco, telefone)
  values ('L&PM', 'Rua Comendador Coruja', '314', 'Loja 9 - Floresta', '90220180', '(51)32255777');
insert into editoras(nomeeditora, logradendereco, numendereco, complendereco, cependereco, telefone)
  values ('Editora Pensamento', 'Rua Dr. Mário Vicente', '374', null, '04270000', '(63)31410000');

insert into edicoes(isbn, preco, anoedicao, numpaginas, qtdeestoque) values ('9788525410733', 15.50, 2008, 304, 12);
insert into edicoes(isbn, preco, anoedicao, numpaginas, qtdeestoque) values ('8533616252', 20.00, 2002, 202, 10);

-- INSERTs (secundarias)
/*
insert into livroautor(id_livro, id_autor) values (3, 2);
insert into livroautor(id_livro, id_autor) values (4, 3);

insert into livroedicaoeditora(id_livro, isbn, id_editora) values (4, '9788525410733', 1);
*/

-- SELECTs (principais)
select * from livros order by id_livro;
--select * from autores;
--select * from editoras;
--select * from edicoes;

-- SELECTs (secundarias)
--select * from livroautor;
/*
select (titulolivro, nomeautor) from livroautor l
  inner join livros on (livros.id_livro = l.id_livro)
  inner join autores on (autores.id_autor = l.id_autor);
*/
--select * from livroedicaoeditora;
/*
select (titulolivro, nomeeditora, preco) from livroedicaoeditora l
  inner join livros on (livros.id_livro = l.id_livro)
  inner join editoras on (editoras.id_editora = l.id_editora)
  inner join edicoes on (edicoes.isbn = l.isbn);
*/
