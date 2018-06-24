/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado.mercadoria;

import database.core.CoreDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.supermercado.mercadoria.ItemProduto;
import modelo.supermercado.Compra;
import modelo.supermercado.mercadoria.Produto;

/**
 *
 * @author joel-
 */
public abstract class ItemProdutoDAO extends CoreDAO{
    
    // O ideal aki seria ter como parametro uma Instancia de uma compra já com seu id
    // Mas para agilizar (só olhar a inserção da compra que vcs vao entender) vai ficar assim msm
    public static int create(int idCompra, ItemProduto item) throws ClassNotFoundException, SQLException {

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string básica de sql;
        String sql = "INSERT INTO compra" +
                "(preco_compra, quant, fk_produto, fk_hist_compra)" +
                " VALUES (?, ?, ?, ?)";

        PreparedStatement st = conexao.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);

        // Defina os valores que ocuparão as '?' na ordem acima;
        st.setDouble(1, item.getPrecoCompra());
        st.setInt(2, item.getQuantidade());
        st.setInt(3, item.getProduto().getId());
        st.setInt(4, idCompra);

        // Execute o INSERT e receba o ID do item cadastrado no BD;
        st.executeUpdate();
        int id = getIdAtCreate(st);

        st.close();
        conexao.close();

        return id;
    }
    
    //Jennifer
    public static List<ItemProduto> readItensByCompra(Compra compra)throws SQLException, ClassNotFoundException{
        //Faça uso da função ProdutoDAO.readProdutoById(int id)
        //Servira para pegar o produto do item em questao
        
        List<ItemProduto> itens = new ArrayList<>();

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();
 
        // Forme a string sql;
        String sql = "SELECT * from compra "
                + "WHERE fk_hist_compra = ?"; //REVER SE ESTA CERTO
        PreparedStatement st = conexao.prepareStatement (sql);
        st.setInt(1, compra.getId());
        
        ResultSet rs = st.executeQuery();
 
        while (rs.next()) {
  
            int id = rs.getInt("id");
            double precoCompra = rs.getDouble("preco_compra");
            int quantidade = rs.getInt("quant");
            Produto prod ;
            prod = ProdutoDAO.readProdutos(rs);
                        
            itens.add(new ItemProduto(id,precoCompra,quantidade,prod));
        }

        st.close();
        conexao.close();

        return itens;
    }
}
