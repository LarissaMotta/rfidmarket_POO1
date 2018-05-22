/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.ItemProduto;

/**
 *
 * @author joel-
 */
public class ItemProdutoDAO extends DBCommand{
    
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
        st.setInt(3, idCompra);

        // Execute o INSERT e receba o ID do item cadastrado no BD;
        st.executeUpdate();
        int id = getIdAtCreate(st);

        st.close();
        conexao.close();

        return id;
    }
}
