package database;

import modelo.cliente.Cartao;
import modelo.cliente.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaoDAO extends DBCommand {

    /*TODO resolver problema sobre o tipo do cartão
    * Tipo é String ou char? (no banco tá CHAR, aqui tá String)*/
    public static int create(Cartao cartao, Cliente cliente)
            throws ClassNotFoundException, SQLException {

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string básica de sql;
        String sql = "INSERT INTO cartao" +
                "(nome_titular, validade, bandeira, numero, tipo)" +
                " VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps;
        ps = conexao.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);

        // Defina os valores que ocuparão as '?' na ordem acima;
        ps.setString(1, cartao.getTitular());
        ps.setDate(2, new Date(cartao.getDataValid().getTime()));
        ps.setString(3, cartao.getBandeira());
        ps.setLong(4, cartao.getNumero());
        ps.setString(5, cartao.getTipo());

        // Execute o INSERT e receba o ID do cartão cadastrado no BD;
        ps.executeUpdate();
        int id = getIdAtCreate(ps);

        ps.close();
        conexao.close();

        return id;
    }

    /**
     * Retorna um conjunto de cartões relacionados a um cliente;
     * @param cliente cliente usuário do(s) cartão(ões);
     * @return Conjunto de cartões relacionados ao cliente;
     */
    public static List<Cartao> readCartoes(Cliente cliente)
            throws SQLException, ClassNotFoundException {

        List<Cartao> cartoes;

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT * utiliza WHERE fk_pessoa_fisica = " + cliente.getId();

        Statement st = conexao.createStatement();
        ResultSet rs = st.executeQuery(sql);

        // Se a pesquina na base retornou 0 resultados;
        if (rs.getMetaData().getColumnCount() < 1) return null;

        int id;
        String bandeira;
        Date dataValid;
        long numero;
        String titular;
        String tipo;

        cartoes = new ArrayList<>();

        // Enquanto houver algum cartão resultado da busca;
        while (rs.next()) {

            id = rs.getInt("id");
            bandeira = rs.getString("bandeira");
            dataValid = rs.getDate("validade");
            numero = rs.getLong("numero");
            titular = rs.getString("nome_titular");
            tipo = rs.getString("tipo");

            cartoes.add(new Cartao(id, bandeira, dataValid, numero, titular, tipo));
        }

        st.close();
        conexao.close();

        return cartoes;
    }
}
