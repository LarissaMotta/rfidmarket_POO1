package database;

import modelo.pessoa.Contato;
import modelo.pessoa.Pessoa;
import modelo.supermercado.Funcionario;
import modelo.supermercado.Supermercado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 20162bsi0511 on 21/05/2018.
 */
public class ContatoDAO extends DBCommand {

    public static int create(Contato contato, Pessoa pessoa) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();

        String sql = "INSERT INTO contato (descricao,tipo,fk_pessoa) "
                + "VALUES (?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, contato.getDescricao());
        st.setString(2, contato.getTipo());
        st.setInt(3, pessoa.getId());

        st.executeUpdate();

        st.close();
        conn.close();

        return id;
    }
}
