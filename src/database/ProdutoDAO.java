package database;

import static database.DBCommand.getConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.cliente.Cartao;

import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Produto;

public abstract class ProdutoDAO extends DBCommand{

    /**
     * Insere um produto na base de dados;
     * @param produto produto a ser escrito na base de dados;
     * @param supermercado supermercado que dispõe do produto;
     * @return Inteiro que representa o ID do produto inserido no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(Produto produto, Supermercado supermercado) throws ClassNotFoundException, SQLException {

        Connection conn = getConnection();
        String sql = "INSERT INTO produto" +
                "(nome, preco, codigo, descricao, custo, estoque," +
                "tipo, quant_prateleira, marca, fk_supermecado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, produto.getNome());
        st.setDouble(2, produto.getPrecoVenda());
        st.setString(3, produto.getCodigo());
        st.setString(4, produto.getDescricao());
        st.setDouble(5, produto.getCusto());
        st.setInt(6, produto.getQtdEstoque());
        st.setString(7, produto.getTipo());
        st.setInt(8, produto.getQtdPrateleira());
        st.setString(9, produto.getMarca());
        st.setInt(10, supermercado.getId());
        st.executeUpdate();
        int id = getIdAtCreate(st);

        st.close();
        conn.close();

        return id;
    }

    //Jennifer
    public static List<Produto> readProdutosBySupermercado(Supermercado supermercado)throws SQLException, ClassNotFoundException{
        List<Produto> produtos = new ArrayList<>();

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();
 
        // Forme a string sql;
        String sql = "SELECT * from produtos "
               + "INNER JOIN pessoa on pessoa.id = juridica.id"
                + "INNER JOIN juridica on juridica.id = supermercado.id"
                + "INNER JOIN supermercado on supermercado.id = produto.id"
                + "WHERE fk_produto = ?"; //REVER SE ESTA CERTO

        PreparedStatement st = conexao.prepareStatement (sql);
        st.setInt(1, supermercado.getId());
        
        ResultSet rs = st.executeQuery();
 
        while (rs.next()) {

            int id = rs.getInt("id");
            String codigo = rs.getString("codigo");
            double custo = rs.getDouble("custo");
            String descricao = rs.getString("descricao");
            String marca = rs.getString("marca");
            String nome = rs.getString("nome");
            double precoVenda = rs.getDouble("precoVenda");
            int qtdPrateleira = rs.getInt("qtdPrateleira");
            int qtdEstoque = rs.getInt("qtdEstoque");
            String tipo = rs.getString("tipo");

            produtos.add(new Produto(id,codigo,custo,descricao,marca,nome,precoVenda,qtdPrateleira,qtdEstoque,tipo));
        }

        st.close();
        conexao.close();

        return produtos;
        
    }
    
    //Jennifer
    public static Produto readProdutosById(int id){
        
    }
    
    //Jennifer
    private static Produto readProdutos(PreparedStatement st){
        
    }
}
