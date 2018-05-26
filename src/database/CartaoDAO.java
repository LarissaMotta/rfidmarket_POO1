package database;

import database.core.CoreDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import modelo.cliente.Cartao;
import modelo.cliente.Cliente;

public abstract class CartaoDAO extends CoreDAO {

    public static int create(Cartao cartao) throws ClassNotFoundException, SQLException {

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string básica de sql;
        String sql = "INSERT INTO cartao" +
                "(nome_titular, validade, bandeira, numero, tipo)" +
                " VALUES (?, ?, ?, ?, ?)";

        PreparedStatement st = conexao.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);

        // Defina os valores que ocuparão as '?' na ordem acima;
        st.setString(1, cartao.getTitular());
        st.setDate(2, new Date(cartao.getDataValid().getTime()));
        st.setString(3, cartao.getBandeira());
        st.setLong(4, cartao.getNumero());
        st.setString(5, String.valueOf(cartao.getTipo()));

        // Execute o INSERT e receba o ID do cartão cadastrado no BD;
        st.executeUpdate();
        int id = getIdAtCreate(st);

        st.close();
        conexao.close();

        return id;
    }

    /**
     * Retorna um conjunto de cartões relacionados a um cliente;
     * @param cliente cliente usuário do(s) cartão(ões);
     * @return Conjunto de cartões relacionados ao cliente;
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    //TODO Consertar
    public static List<Cartao> readCartoesByCliente(Cliente cliente) throws SQLException, ClassNotFoundException {

        List<Cartao> cartoes = new ArrayList<>();

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT c.id, c.nome_titular, c.validade, c.bandeira, c.numero," +
                "c.tipo FROM utiliza AS ut INNER JOIN cartao AS c" +
                "ON ut.fk_cartao = c.id WHERE ut.fk_cliente = ?";

        PreparedStatement st = conexao.prepareStatement (sql);
        st.setInt(1, cliente.getId());
        
        ResultSet rs = st.executeQuery();

        // Enquanto houver algum cartão resultado da busca;
        while (rs.next()) {

            int id = rs.getInt("id");
            String bandeira = rs.getString("bandeira");
            Date dataValid = rs.getDate("validade");
            long numero = rs.getLong("numero");
            String titular = rs.getString("nome_titular");
            char tipo = rs.getString("tipo").charAt(0);

            cartoes.add(new Cartao(id, bandeira, dataValid, numero, titular, tipo));
        }

        st.close();
        conexao.close();

        return cartoes;
    }
}
