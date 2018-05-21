/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.DBCommand.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.pessoa.PessoaJuridica;
import org.postgresql.util.PSQLException;

/**
 *
 * @author joel-
 */
public abstract class PessoaJuridicaDAO extends DBCommand{
    public static int create(PessoaJuridica pessoaJuridica) throws ClassNotFoundException, SQLException, PSQLException {
        int id = PessoaDAO.create(pessoaJuridica); // insere primeiro os dados da pessoa
        
        Connection conn = getConnection();

        String sql = "INSERT INTO juridica (cnpj,fk_pessoa) "
                + "VALUES (?, ?)";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, pessoaJuridica.getCnpj());
        st.setInt(2, id);
        
        try{
            st.executeUpdate();
        }catch (PSQLException ex){
            // se ocorrer algum erro durante a inseção do restante dos dados
            // apaga o que ja foi inserido e lança a exceção
            PessoaDAO.delete(id);
        }

        st.close();
        conn.close();

        return id;
    }
    
    public static void delete(int id) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        String sql = "DELETE FROM juridica WHERE id = ?";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        st.executeUpdate();

        st.close();
        conn.close();

        PessoaDAO.delete(id);
    }
}
