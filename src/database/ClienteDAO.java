package database;

import modelo.cliente.Cliente;

import java.sql.SQLException;

/**
 * Created by 20162bsi0040 on 21/05/2018.
 */
public abstract class ClienteDAO extends DBCommand{
    public static int create(Cliente cliente) throws ClassNotFoundException, SQLException, PSQLException {
        int id = PessoaFisicaDAO.create(cliente);
        return id;
    }
}
