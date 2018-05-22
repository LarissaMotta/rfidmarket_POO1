package database;

import modelo.cliente.Cliente;
import java.sql.SQLException;

/**
 * Created by 20162bsi0040 on 21/05/2018.
 */
public abstract class ClienteDAO extends DBCommand{

    /**
     * Insere um cliente na base de dados;
     * @param cliente Cliente a ser gravado na base de dados;
     * @return Inteiro que representa o ID do cliente inserido no BD;
     */
    public static int create(Cliente cliente) throws ClassNotFoundException, SQLException {
        return PessoaFisicaDAO.create(cliente);
    }
}
