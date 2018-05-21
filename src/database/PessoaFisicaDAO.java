/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.DBCommand.getConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.pessoa.PessoaFisica;
import org.postgresql.util.PSQLException;

/**
 *
 * @author joel-
 */
public abstract class PessoaFisicaDAO extends DBCommand {
    public static int create(PessoaFisica pessoaFisica) throws ClassNotFoundException, SQLException, PSQLException {
        int id = PessoaDAO.create(pessoaFisica); // insere primeiro os dados da pessoa
        
        Connection conn = getConnection();

        String sql = "INSERT INTO fisica (cpf,data_nasc,genero,login,senha,fk_pessoa) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, pessoaFisica.getCpf());
        st.setDate(2, new Date(pessoaFisica.getDataNasc().getTime()));
        st.setString(3, String.valueOf(pessoaFisica.getGenero()));
        st.setString(4, pessoaFisica.getLogin());
        st.setString(5, pessoaFisica.getSenha());
        st.setInt(6, id);
        
        try{
            st.executeUpdate();
        }catch (PSQLException ex){
            // se ocorrer algum erro durante a inseção do restante dos dados
            // apaga o que ja foi inserido e lança a exceção
            PessoaDAO.delete(id);
            throw ex;
        }

        st.close();
        conn.close();

        return id;
    }

    public static void delete(int id) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        String sql = "DELETE FROM fisica WHERE id = ?";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        st.executeUpdate();

        st.close();
        conn.close();

        PessoaDAO.delete(id);
    }
}
