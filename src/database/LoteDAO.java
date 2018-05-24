package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Fornecedor;
import modelo.supermercado.mercadoria.Lote;
import modelo.supermercado.mercadoria.Produto;

public abstract class LoteDAO extends DBCommand {

    /**
     * Insere um lote de um produto na base de dados;
     * @param lote lote a ser escrito na base de dados;
     * @param forn fornecedor do lote de produtos;
     * @param prod produto que corresponde as unidades do lote;
     * @param superm supermercado proprietário do lote de produtos;
     * @return Inteiro que representa o ID do lote inserido no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(Lote lote, Fornecedor forn, Produto prod, Supermercado superm)throws ClassNotFoundException, SQLException {

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string básica de sql;
        String sql = "INSERT INTO lote" +
                "(data_compra, numero, fabricacao, quantidade, validade," +
                "fk_fornecedor, fk_produto, fk_supermercado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement st = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // Defina os valores que ocuparão as '?' na ordem acima;
        st.setDate(1, new Date(lote.getDataCompra().getTime()));
        st.setString(2, lote.getIdentificador());
        st.setDate(3, new Date(lote.getDataFabricacao().getTime()));
        st.setInt(4, lote.getNumUnidades());
        st.setDate(5, new Date(lote.getDataValidade().getTime()));
        st.setInt(6, forn.getId());
        st.setInt(7, prod.getId());
        st.setInt(8, superm.getId());

        // Execute o INSERT e receba o ID do cartão cadastrado no BD;
        st.executeUpdate();
        int id = getIdAtCreate(st);

        st.close();
        conexao.close();

        return id;
    }
    
    //Jubileu
    public static List<Lote> readLotesBySupermercado(Supermercado supermercado){
        
    }
    
    //Jubileu
    public static List<Lote> readLotesByProduto(Produto produto){
        
    }
    
    //Jubileu
    public static List<Lote> readLotesByFornecedor(Fornecedor fornecedor){
        
    }
    
    //Jubileu
    private static List<Lote> readLotes(PreparedStatement st){
        //Faça uso da função ProdutoDAO.readProdutoById(int id)
        //Servira para pegar o produto do Lote em questao
    }
}
