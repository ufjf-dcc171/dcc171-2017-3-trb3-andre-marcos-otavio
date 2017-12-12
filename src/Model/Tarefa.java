/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Desenvolvedor
 */
public class Tarefa {
    private int id;
    private String nome;
    private double valorPercentualAndamento;
    private String dataInicio;
    private String dataConclusão;
    private Tarefa tarefaAntecessora;
    private Tarefa sucessora;

    public Tarefa() {
    }

    public Tarefa(int id, String nome, double valorPercentualAndamento, String dataInicio, String dataConclusão, Tarefa tarefaAntecessora, Tarefa sucessora) {
        this.id = id;
        this.nome = nome;
        this.valorPercentualAndamento = valorPercentualAndamento;
        this.dataInicio = dataInicio;
        this.dataConclusão = dataConclusão;
        this.tarefaAntecessora = tarefaAntecessora;
        this.sucessora = sucessora;
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
        return dataConclusão;
    }

    public void setDataConclusão(String dataConclusão) {
        this.dataConclusão = dataConclusão;
    }

    public Tarefa getTarefaAntecessora() {
        return tarefaAntecessora;
    }

    public void setTarefaAntecessora(Tarefa tarefaAntecessora) {
        this.tarefaAntecessora = tarefaAntecessora;
    }

    public Tarefa getSucessora() {
        return sucessora;
    }

    public void setSucessora(Tarefa sucessora) {
        this.sucessora = sucessora;
    }
    
    

    
   
}
