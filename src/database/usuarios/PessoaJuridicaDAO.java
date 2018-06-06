package database.usuarios;

import database.core.CoreDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.usuarios.PessoaJuridica;
import org.postgresql.util.PSQLException;

/**
 *
 * @author joel-
 */
public abstract class PessoaJuridicaDAO extends CoreDAO{

    /**
     * Insere uma usuarios jurídica na base de dados;
     * @param pessoaJuridica PJ a ser gravada na base de dados;
     * @return Inteiro que representa o ID da PJ inserida no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(PessoaJuridica pessoaJuridica) throws ClassNotFoundException, SQLException {

        int id = PessoaDAO.create(pessoaJuridica); // insere primeiro os dados da usuarios
        
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
     * Remove uma usuarios da base de dados;
     * @param id id da usuarios a ser removida da base;
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void delete(int id) throws SQLException, ClassNotFoundException {

        Connection conn = getConnection();
        String sql = "DELETE FROM juridica WHERE fk_pessoa = ?";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        st.executeUpdate();
        st.close();
        conn.close();

        PessoaDAO.delete(id);
    }
}