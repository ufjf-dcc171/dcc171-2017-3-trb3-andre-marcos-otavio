Alunos: 
André Magno Ribeiro Matricula: 201376060 Curso: Sistema de Informação;  
Marcos Aquino;  
Otavio Augusto Ferreira Rodrigues Matricula: 201276030 Curso: Sistema de Informação;  

RELATÓRIO

Foi desenvolvido um gerenciador de tarefas em Java, para auxiliar uma equipe de desenvolvimento a organizar melhor seus projetos.
Facilitando a visualização das tarefas e prazos. Fazendo também o gerenciamento dos responsáveis e envolvidos em cada tarefa.
        
MODELO

No nosso modelo de banco de dados existem as seguintes tabelas:
	- tarefa;
	- projeto;
	- pessoa;
	- pessoas_tarefas;
	- depedencia 


RELACIONAMENTO DAS TABELAS

Uma tarefa pode ter varias pessoas envolvidas;
Uma pessoa pode ter varias tarefas;
A tabela pessoas_tarefas é responsável por fazer o relacionamento de N pra N entre as tabelas pessoas e tarefa;

Um tarefa pode depender de outra para ser realizada;
A tabela dependencia é responsável por fazer o relacionamento de N pra N entre as tabelas tarefa e tarefa;

Um Projeto pode ter N tarefas;
	- projeto 1,N tarefas

SCRIPTS PARA CRIAÇÃO DO BANCO DE DADOS

create table pessoa (
    id     INTEGER NOT NULL 
                PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1),
    nome varchar(255) NOT NULL,
    email varchar(255) NOT NULL
);

create table projeto (
    id     INTEGER NOT NULL 
                PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1),
    nome varchar(255) NOT NULL
);

create table tarefa (
  id INTEGER NOT NULL 
                PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1),
  nome varchar(255) NOT NULL,
  duracao varchar(50),
	valorPercentualAndamento varchar(50),
	dataInicio varchar(50),
	dataConclusao varchar(50),
	id_projeto integer,
  CONSTRAINT tarefa FOREIGN KEY (id_projeto) REFERENCES projeto (id)
);

create table depedencia (
	id_tarefa integer,
    CONSTRAINT depedencia_1 FOREIGN KEY (id_tarefa) REFERENCES tarefa (id),
	id_tarefaPedentes integer,
    CONSTRAINT depedencia_2 FOREIGN KEY (id_tarefaPedentes) REFERENCES tarefa (id)
);

create table pessoas_tarefas (
	id_tarefa integer,
    CONSTRAINT pessoas_tarefas_1 FOREIGN KEY (id_tarefa) REFERENCES tarefa (id),
	id_pessoa integer,
    CONSTRAINT pessoas_tarefas_2 FOREIGN KEY (id_pessoa) REFERENCES pessoa (id)
);


POSIVEIS MELHORAS
Para realizar melhorias no futuro o sistema desenvolvido poderia ter seu layout melhorado;
As consultas e inserções no banco de dados poderiam ser otimizadas, a fim de melhorar sua performance.

