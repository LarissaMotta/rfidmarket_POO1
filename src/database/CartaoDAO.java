package database;

import modelo.cliente.Cartao;
import modelo.cliente.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaoDAO extends DBCommand {

    public static int create(Cartao cartao, Cliente cliente)
            throws ClassNotFoundException, SQLException {

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
     */
    public static List<Cartao> readCartoesByCliente(Cliente cliente)
            throws SQLException, ClassNotFoundException {

        List<Cartao> cartoes = new ArrayList<>();

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT * utiliza WHERE fk_pessoa_fisica = " + cliente.getId();

        Statement st = conexao.createStatement();
        ResultSet rs = st.executeQuery(sql);


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
