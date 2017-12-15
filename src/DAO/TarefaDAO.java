/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pessoa;
import Model.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Desenvolvedor
 */
public class TarefaDAO {

    private static TarefaDAO instance;

    public static TarefaDAO getInstance() {
        if (instance == null) {
            instance = new TarefaDAO();
        }
        return instance;
    }

    public void inserir(Tarefa tarefa) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            if (tarefa instanceof Tarefa) {
                st.execute("INSERT INTO Tarefa (nome,duracao,valorPercentualAndamento,dataInicio,dataConclusao,id_projeto) "
                        + "VALUES ('" + tarefa.getNome() + "', '"
                        + tarefa.getDuracao() + "','"
                        + tarefa.getValorPercentualAndamento() + "', '"
                        + tarefa.getDataInicio() + "','"
                        + tarefa.getDataConclusao() + "', "
                        + tarefa.getProjeto().getId() + ")");
            }//insert into tarefa ()create table tarefa (id  nome duracao valorPercentualAndamento dataInicio  dataConclusao   id_projeto) values ('  ','','','','','','','','',);

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public void update(Tarefa tarefa) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            st.execute("update Tarefa set nome='"
                    + tarefa.getNome() + "',duracao='"
                    + tarefa.getDuracao() + "',valorPercentualAndamento= '"
                    + tarefa.getValorPercentualAndamento() + "',dataInicio='"
                    + tarefa.getDataInicio() + "',dataConclusao='"
                    + tarefa.getDataConclusao() + "',id_projeto="
                    + tarefa.getProjeto().getId()+" where id="+tarefa.getId());

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

   

   
    
      public Tarefa lerTarefa(String nome) throws ClassNotFoundException, SQLException {
        Tarefa tarefa=null;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;

        try{
        conn = DatabaseLocator.getInstance().getConnection();
        st = conn.prepareStatement("SELECT * FROM tarefa WHERE nome='"+nome+"'");
        rs = st.executeQuery();

      
            while (rs.next()) {
               tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDuracao(rs.getDouble("duracao"));
                tarefa.setValorPercentualAndamento(rs.getDouble("valorPercentualAndamento"));
                tarefa.setDataInicio(rs.getString("dataInicio"));
                tarefa.setDataConclusao(rs.getString("dataConclusao"));
                tarefa.setProjeto(ProjetoDAO.getInstance().lerProjeto(rs.getInt("id_projeto")));
             
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);

        }
        return tarefa;


    }


    private void closeResources(Connection conn, Statement st) {
        try {
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
        }
    }


    
    
    
    public void inserirTarefaDepedente(int tarefa, int tarefaDepedente) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
     
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            System.out.println("=="+tarefa+"==="+tarefaDepedente);
                st.execute("INSERT INTO Depedencia (id_tarefa,id_tarefaPedentes) values("+tarefa+","+tarefaDepedente+")");

    }
    
    
    public void inserirTarefaPessoa(int tarefa, int tarefaDepedente) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        Statement st = null;
     
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            System.out.println("=="+tarefa+"==="+tarefaDepedente);
                st.execute("INSERT INTO pessoas_tarefas (id_tarefa,id_pessoa) values("+tarefa+","+tarefaDepedente+")");

    }
    
    
    public ArrayList<Tarefa> lerTarefasDepedentes(int tarefa_id) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Tarefa> tarefas = new ArrayList();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.prepareStatement("SELECT * " +
"FROM tarefa " +
"INNER JOIN depedencia " +
"ON tarefa.id= depedencia.id_tarefaPedentes Where depedencia.id_tarefa="+tarefa_id);
                    

            rs = st.executeQuery();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDuracao(rs.getDouble("duracao"));
                tarefa.setValorPercentualAndamento(rs.getDouble("valorPercentualAndamento"));
                tarefa.setDataInicio(rs.getString("dataInicio"));
                tarefa.setDataConclusao(rs.getString("dataConclusao"));
                tarefa.setProjeto(ProjetoDAO.getInstance().lerProjeto(rs.getInt("id_projeto")));
                tarefas.add(tarefa);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);

        }
        return tarefas;

    }
    
    
    
    public ArrayList<Pessoa> lerTarefasPessoa(int tarefa_id) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;

         ArrayList<Pessoa> pessoas = new ArrayList();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.prepareStatement("SELECT * " +
"FROM pessoa " +
"INNER JOIN pessoas_tarefas " +
"ON pessoa.id= pessoas_tarefas.id_pessoa Where pessoas_tarefas.id_tarefa="+tarefa_id);
                    

            rs = st.executeQuery();

            while (rs.next()) {
               Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoas.add(pessoa);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);

        }
        return pessoas;

    }
        public boolean excluirTarefaDepedencia(int id) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;
        conn = DatabaseLocator.getInstance().getConnection();
        st = conn.prepareStatement("Delete From depedencia WHERE id_tarefa=" + id);
        st.execute();
        return true;
    }
        
        
        
              public boolean excluirTarefaFeitaDaDEpedencia(int id) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;
        conn = DatabaseLocator.getInstance().getConnection();
        st = conn.prepareStatement("Delete From depedencia WHERE id_tarefaPedentes=" + id);
        st.execute();
        return true;
    }
      public boolean excluirTarefaPessoa(int id) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;
        conn = DatabaseLocator.getInstance().getConnection();
        st = conn.prepareStatement("Delete From pessoas_tarefas WHERE id_tarefa=" + id);
        st.execute();
        return true;
    }
    
       public Tarefa lerTarefa(int id) throws ClassNotFoundException, SQLException {
        Tarefa tarefa=null;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;
        System.out.println("ssdfdfasdfasdfasdfasdf" + id);
        try{
        conn = DatabaseLocator.getInstance().getConnection();
        st = conn.prepareStatement("SELECT * FROM tarefa WHERE id=" + id);
        rs = st.executeQuery();

      
            while (rs.next()) {
               tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDuracao(rs.getDouble("duracao"));
                tarefa.setValorPercentualAndamento(rs.getDouble("valorPercentualAndamento"));
                tarefa.setDataInicio(rs.getString("dataInicio"));
                tarefa.setDataConclusao(rs.getString("dataConclusao"));
                tarefa.setProjeto(ProjetoDAO.getInstance().lerProjeto(rs.getInt("id_projeto")));
             
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);

        }
        return tarefa;


    }
  
 public ArrayList<Tarefa> lerTodosTarefas() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Tarefa> tarefas = new ArrayList();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.prepareStatement("SELECT * FROM Tarefa ");
            rs = st.executeQuery();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDuracao(rs.getDouble("duracao"));
                tarefa.setValorPercentualAndamento(rs.getDouble("valorPercentualAndamento"));
                tarefa.setDataInicio(rs.getString("dataInicio"));
                tarefa.setDataConclusao(rs.getString("dataConclusao"));
                tarefa.setProjeto(ProjetoDAO.getInstance().lerProjeto(rs.getInt("id_projeto")));
                tarefas.add(tarefa);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);

        }
        return tarefas;

    }
 
  public ArrayList<Tarefa> lerTodosTarefasConcluidas() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Tarefa> tarefas = new ArrayList();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.prepareStatement("SELECT * FROM Tarefa  Where dataConclusao!='-'");
            rs = st.executeQuery();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDuracao(rs.getDouble("duracao"));
                tarefa.setValorPercentualAndamento(rs.getDouble("valorPercentualAndamento"));
                tarefa.setDataInicio(rs.getString("dataInicio"));
                tarefa.setDataConclusao(rs.getString("dataConclusao"));
                tarefa.setProjeto(ProjetoDAO.getInstance().lerProjeto(rs.getInt("id_projeto")));
                tarefas.add(tarefa);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);

        }
        return tarefas;

    }
  
   public ArrayList<Tarefa> lerTodosTarefasAFazer() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Tarefa> tarefas = new ArrayList();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.prepareStatement("SELECT * FROM Tarefa  Where dataInicio=='-'");
            rs = st.executeQuery();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDuracao(rs.getDouble("duracao"));
                tarefa.setValorPercentualAndamento(rs.getDouble("valorPercentualAndamento"));
                tarefa.setDataInicio(rs.getString("dataInicio"));
                tarefa.setDataConclusao(rs.getString("dataConclusao"));
                tarefa.setProjeto(ProjetoDAO.getInstance().lerProjeto(rs.getInt("id_projeto")));
                tarefas.add(tarefa);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);

        }
        return tarefas;

    }
   
      public ArrayList<Tarefa> lerTodosTarefasPossivelIniciada() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Tarefa> tarefas = new ArrayList();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.prepareStatement("SELECT * FROM Tarefa  Where dataInicio=='-' ,");
            rs = st.executeQuery();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDuracao(rs.getDouble("duracao"));
                tarefa.setValorPercentualAndamento(rs.getDouble("valorPercentualAndamento"));
                tarefa.setDataInicio(rs.getString("dataInicio"));
                tarefa.setDataConclusao(rs.getString("dataConclusao"));
                tarefa.setProjeto(ProjetoDAO.getInstance().lerProjeto(rs.getInt("id_projeto")));
                
                tarefas.add(tarefa);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);

        }
        return tarefas;

    }

    public Iterable<Tarefa> lerFiltroTarefas(String s) throws ClassNotFoundException, SQLException {
        if(s.equals("Todas as tarefas")){
        return lerTodosTarefas();
          
        }else if(s.equals("Todas as tarefas concluídas")){
        
        return lerTodosTarefasAFazer();
        
         }else if(s.equals("Todas a tarefas fazer")){
       
        return  lerTodosTarefasConcluidas(); 
        
        }else if(s.equals("Todas as tarefas possíveis de serem iniciadas")){
        
        return null;
        
        }
        
   return lerTodosTarefas();
    }
    
}
