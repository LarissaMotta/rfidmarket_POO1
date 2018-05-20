package database;

import modelo.supermercado.mercadoria.Fornecedor;
import org.postgresql.util.PSQLException;
import java.sql.SQLException;

public class FornecedorDAO {

    public static int create(Fornecedor fornecedor)
            throws ClassNotFoundException, SQLException, PSQLException {

        // Retorne o id da pessoa jurídica que corresponde ao fornecedor;
        return PessoaJuridicaDAO.create(fornecedor);
    }

}