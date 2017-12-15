Alunos: André Magno Ribeiro 
        Marcos 
        Otavio 


O sistema consiste em um gerenciador de projetos e suas tarefas.
     
     
MODELO

O modelo consiste na tabelas: tarefa, projeto, pessoa pessoas_tarefas, depedencia. 
As tabelas pessoas_tarefas e depedencia são tabelas que faz o relacionamento n para n de tarefa para pessoa
e tarefa para tarefa respectivamente. 

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

O sistema poderia ser melhorado em questão de layout e seu codigo poderia se mais otimisado em questão de busca no banco de dados.


