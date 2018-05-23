package database;

import java.sql.PreparedStatement;
import modelo.supermercado.mercadoria.Fornecedor;
import java.sql.SQLException;
import java.util.List;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Lote;

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
    
    //Larissa
    public static List<Fornecedor> readFornecedoresBySupermercado(Supermercado supermercado){
        
    }
    
    //Larissa
    public static Fornecedor readFornecedorByLote(Lote lote){
        
    }
    
    //Larissa
    private static Fornecedor readFornecedor(PreparedStatement st){
        
    }
}