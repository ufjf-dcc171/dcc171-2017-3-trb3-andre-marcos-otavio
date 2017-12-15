/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Desenvolvedor
 */
public class Tarefa {
    private int id;
    private String nome;
    private double duracao;
    private double valorPercentualAndamento;
    private String dataInicio;
    private String dataConclusao;
    private Projeto projeto;
    private ArrayList<Tarefa> tarefaFazerAntes;
    private boolean disponivel;

    public Tarefa() {
    }

    public Tarefa(int id, String nome, double duracao, double valorPercentualAndamento, String dataInicio, String dataConclusao, Projeto projeto, ArrayList<Tarefa> tarefaFazerAntes) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.valorPercentualAndamento = valorPercentualAndamento;
        this.dataInicio = dataInicio;
        this.dataConclusao = dataConclusao;
        this.projeto = projeto;
        this.tarefaFazerAntes = tarefaFazerAntes;
    }

    public String getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
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

    public double getDuracao() {
        return duracao;
    }
    
     public boolean getDisponivel() {
        return disponivel;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public double getValorPercentualAndamento() {
        return valorPercentualAndamento;
    }

    public void setValorPercentualAndamento(double valorPercentualAndamento) {
        this.valorPercentualAndamento = valorPercentualAndamento;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataConclusão() {
        return dataConclusao;
    }

    public void setDataConclusão(String dataConclusão) {
        this.dataConclusao = dataConclusão;
    }

    public ArrayList<Tarefa> getTarefaFazerAntes() {
        return tarefaFazerAntes;
    }

    public void setTarefaFazerAntes(ArrayList<Tarefa> tarefaFazerAntes) {
        this.tarefaFazerAntes = tarefaFazerAntes;
    }
   
    




}