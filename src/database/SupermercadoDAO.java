package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.supermercado.Supermercado;
import org.postgresql.util.PSQLException;

/**
 *
 * @author joel-
 */
public abstract class SupermercadoDAO extends DBCommand{

    /**
     * Insere um supermercado na base de dados;
     * @param supermercado Supermercado a ser inserido na base;
     * @return Inteiro que representa o ID do supermercado inserido no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(Supermercado supermercado) throws ClassNotFoundException, SQLException {

        int id = PessoaJuridicaDAO.create(supermercado);
        Connection conn = getConnection();
        String sql = "INSERT INTO supermercado (longitude,latitude,unidade,fk_pessoa_juridica) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setDouble(1, supermercado.getLongitude());
        st.setDouble(2, supermercado.getLatitude());
        st.setString(3, supermercado.getUnidade());
        st.setInt(4, id);

        try{
            st.executeUpdate();
        }catch (PSQLException ex){
            // se ocorrer algum erro durante a inseção do restante dos dados
            // apaga o que ja foi inserido e lança a exceção
            PessoaJuridicaDAO.delete(id);
        }

        st.close();
        conn.close();

        return id;
    }
}
