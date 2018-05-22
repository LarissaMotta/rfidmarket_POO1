package database;

import modelo.supermercado.mercadoria.Fornecedor;
import java.sql.SQLException;

public abstract class FornecedorDAO extends DBCommand{

    /**
     * Insere um fornecedor na base de dados;
     * @param fornecedor a ser gravado na base de dados;
     * @return Inteiro que representa o ID do fornecedor inserido no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {

        // Retorne o id da pessoa jurídica que corresponde ao fornecedor;
        return PessoaJuridicaDAO.create(fornecedor);
    }
}