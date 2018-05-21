package database;

import modelo.supermercado.Funcionario;
import modelo.supermercado.Supermercado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by 20162bsi0511 on 21/05/2018.
 */
public abstract class FuncionarioDAO extends  DBCommand {

    public static int create(Funcionario funcionario, Supermercado supermercado) throws SQLException, ClassNotFoundException {
        int id = PessoaFisicaDAO.create(funcionario); // insere primeiro os dados da pessoa

        Connection conn = getConnection();

        String sql = "INSERT INTO funcionario (setor,cargo,fk_pessoa_fisica,fk_supermercado) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, funcionario.getSetor());
        st.setString(2, funcionario.getCargo());
        st.setInt(3, id);
        st.setInt(4, supermercado.getId());


        try {
            st.executeUpdate();
        } catch (PSQLException ex) {
            // se ocorrer algum erro durante a inseção do restante dos dados
            // apaga o que ja foi inserido e lança a exceção
            PessoaFisicaDAO.delete(id);
            throw ex;
        }

        st.close();
        conn.close();

        return id;
    }
}
