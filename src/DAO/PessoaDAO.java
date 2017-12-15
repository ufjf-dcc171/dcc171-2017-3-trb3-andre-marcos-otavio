/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pessoa;
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
public class PessoaDAO {
    
    private static PessoaDAO instance;

        public static PessoaDAO getInstance() {
        if (instance == null) {
            instance = new PessoaDAO();
        }
        return instance;
    }

        
        
        
    public void inserir(Pessoa pessoa) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            if (pessoa instanceof Pessoa) {
                st.execute("INSERT INTO Pessoa (NOME,EMAIL) "
                        + "VALUES ('" + pessoa.getNome() + "', '" + String.valueOf(pessoa.getEmail()) + "')");
            }//  INSERT INTO Pessoa (NOME, EMAIL) VALUES ('Andre, 'andre@gmail.com');


        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }
    
  
    public void update(Pessoa pessoa) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            st.execute("update pessoa set nome="+pessoa.getNome()+" email="+pessoa.getEmail()+" WHERE id="+pessoa.getId());

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }


    public ArrayList<Pessoa> lerTodosPessoas() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Pessoa> pessoas = new ArrayList();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.prepareStatement("SELECT * FROM Pessoa ");
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
    
    
        public Pessoa lerPessoa(String nome) throws ClassNotFoundException, SQLException {
       
            Pessoa pessoa=null;
            Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Pessoa> pessoas = new ArrayList();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.prepareStatement("SELECT * FROM Pessoa where nome='"+nome+"'");
            rs = st.executeQuery();

            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
          
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);

        }
        return pessoa;

    }
    
        public Pessoa lerPessoa(int id) throws ClassNotFoundException, SQLException {
       
            Pessoa pessoa=null;
            Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Pessoa> pessoas = new ArrayList();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.prepareStatement("SELECT * FROM Pessoa where nome="+id);
            rs = st.executeQuery();

            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
          
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);

        }
        return pessoa;

    }
   

    
        public boolean excluirPessoa(int id) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;
        conn = DatabaseLocator.getInstance().getConnection();
        st = conn.prepareStatement("Delete From Pessoa WHERE id=" + id);
        st.execute();
        return true;
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
}
