package DAO;

import Model.Projeto;
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
public class ProjetoDAO {

    private static ProjetoDAO instance;

    public static ProjetoDAO getInstance() {
        if (instance == null) {
            instance = new ProjetoDAO();
        }
        return instance;
    }

    public void inserir(Projeto projeto) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();
            if (projeto instanceof Projeto) {
                st.execute("INSERT INTO Projeto (NOME) "
                        + "VALUES ('" + projeto.getNome() + "')");
            }//  INSERT INTO Projeto (NOME, EMAIL) VALUES ('Andre, 'andre@gmail.com');

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public void updateComTarefa(Projeto projeto) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.createStatement();

            st.execute("update projeto set nome=" + projeto.getNome() + " WHERE id=" + projeto.getId());

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);
        }
    }

    public ArrayList<Projeto> lerTodosProjetos() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;

        ArrayList<Projeto> projetos = new ArrayList();
        try {
            conn = DatabaseLocator.getInstance().getConnection();
            st = conn.prepareStatement("SELECT * FROM Projeto ");
            rs = st.executeQuery();

            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));

                projetos.add(projeto);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);

        }
        return projetos;

    }

    public Projeto lerProjeto(int id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement st = null;
         Projeto projeto = null;
        ResultSet rs;
        System.out.println("ssdfdfasdfasdfasdfasdf" + id);
        try{
        conn = DatabaseLocator.getInstance().getConnection();

        st = conn.prepareStatement("select * from projeto where id=1");
        rs = st.executeQuery();
  
            while (rs.next()) {
               projeto = new Projeto();
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));

              
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            closeResources(conn, st);

        }
        return projeto;

    }

    public boolean excluirProjeto(int id) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs;
        conn = DatabaseLocator.getInstance().getConnection();
        st = conn.prepareStatement("Delete From Projeto WHERE id=" + id);
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
