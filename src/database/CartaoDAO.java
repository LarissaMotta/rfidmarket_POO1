package database;

import modelo.cliente.Cartao;
import modelo.cliente.Cliente;
import java.sql.*;

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
}
