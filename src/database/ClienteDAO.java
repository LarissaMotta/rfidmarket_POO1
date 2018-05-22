package database;

import modelo.cliente.Cartao;
import modelo.cliente.Cliente;

import java.sql.*;

/**
 * Created by 20162bsi0040 on 21/05/2018.
 */
public abstract class ClienteDAO extends DBCommand{

    /**
     * Insere um cliente na base de dados;
     * @param cliente Cliente a ser gravado na base de dados;
     * @return Inteiro que representa o ID do cliente inserido no BD;
     */
    public static int create(Cliente cliente)
            throws ClassNotFoundException, SQLException {
        return PessoaFisicaDAO.create(cliente);
    }

    /*TODO triste. Decidir se recebe um cliente/cartão ou o clienteID/cartaoID*/
    public static int addCartao(Cliente cliente, Cartao cartao)
            throws SQLException, ClassNotFoundException {

        if (cartao.getId() <= 0 || cliente.getId() <= 0)
            throw new IllegalArgumentException("ID inválido para cliente ou cartão");

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string básica de sql;
        String sql = "INSERT INTO utiliza (fk_cartao, fk_pessoa_fisica) VALUES (?, ?)";
        PreparedStatement ps;
        ps = conexao.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);

        // Defina os valores que ocuparão as '?' na ordem acima;
        ps.setLong(1, cartao.getNumero());
        ps.setInt(2, cliente.getId());

        // Execute o INSERT e receba o ID do cartão cadastrado no BD;
        ps.executeUpdate();
        int id = getIdAtCreate(ps);

        ps.close();
        conexao.close();

        return id;
    }
}
