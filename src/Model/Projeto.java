/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.PopupMenu;

/**
 *
 * @author Desenvolvedor
 */
public class Projeto {
    private int id;
    private String nome;
    private Tarefa tarefa;

    public Projeto() {
    }



    public Projeto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Projeto(int id, String nome, Tarefa tarefa) {
        this.id = id;
        this.nome = nome;
        this.tarefa = tarefa;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    
    

}
