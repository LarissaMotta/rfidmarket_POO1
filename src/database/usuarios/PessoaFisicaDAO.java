package database.usuarios;

import database.core.CoreDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.usuarios.PessoaFisica;
import org.postgresql.util.PSQLException;
import util.Util;

/**
 *
 * @author joel-
 */
public abstract class PessoaFisicaDAO extends CoreDAO {

    /**
     * Insere uma usuarios física na base de dados;
     * @param pessoaFisica PF a ser gravada na base de dados;
     * @return Inteiro que representa o ID da PF inserida no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(PessoaFisica pessoaFisica) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {

        int id = PessoaDAO.create(pessoaFisica); // insere primeiro os dados da usuarios
        
        Connection conn = getConnection();

        String sql = "INSERT INTO fisica (cpf,data_nasc,genero,login,senha,fk_pessoa) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, pessoaFisica.getCpf());
        st.setDate(2, new Date(pessoaFisica.getDataNasc().getTime()));
        st.setString(3, String.valueOf(pessoaFisica.getGenero().toChar()));
        st.setString(4, pessoaFisica.getLogin());
        st.setString(5, Util.criptografar(pessoaFisica.getSenha()));
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
    
    public static void update(PessoaFisica pf) throws SQLException, ClassNotFoundException{
        Connection conn = getConnection();
        String sql = "UPDATE fisica "
                + "SET cpf = ?, "
                + "SET data_nasc = ?, "
                + "SET genero = ?, "
                + "SET senha = ?, "
                + "WHERE fk_pessoa = ?";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, pf.getCpf());
        st.setDate(2, new Date(pf.getDataNasc().getTime()));
        st.setString(3, String.valueOf(pf.getGenero().toChar()));
        st.setString(4, pf.getSenha());
        st.setInt(5, pf.getId());

        st.executeUpdate();
        st.close();
        conn.close();
        
        PessoaDAO.update(pf);
    }

    /**
     * Remove uma usuarios da base de dados;
     * @param id id da usuarios a ser removida da base;
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void delete(int id) throws SQLException, ClassNotFoundException {

        Connection conn = getConnection();
        String sql = "DELETE FROM fisica WHERE fk_pessoa = ?";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        st.executeUpdate();
        st.close();
        conn.close();

        PessoaDAO.delete(id);
    }
}
