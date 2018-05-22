package database;

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

    /**
     * Insere uma pessoa jurídica na base de dados;
     * @param pessoaJuridica PJ a ser gravada na base de dados;
     * @return Inteiro que representa o ID da PJ inserida no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(PessoaJuridica pessoaJuridica) throws ClassNotFoundException, SQLException {

        int id = PessoaDAO.create(pessoaJuridica); // insere primeiro os dados da pessoa
        
        Connection conn = getConnection();
        String sql = "INSERT INTO juridica (cnpj, fk_pessoa) "
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

    /**
     * Remove uma pessoa da base de dados;
     * @param id id da pessoa a ser removida da base;
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
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