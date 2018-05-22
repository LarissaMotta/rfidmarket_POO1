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
import modelo.cliente.Cartao;
import modelo.cliente.Cliente;
import modelo.cliente.Compra;
import modelo.supermercado.Supermercado;

/**
 *
 * @author joel-
 */
public abstract class CompraDAO extends DBCommand{
    
    public static int create(Compra compra, Cliente cliente, Cartao cartao, Supermercado supermercado) throws ClassNotFoundException, SQLException {

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string básica de sql;
        String sql = "INSERT INTO hist_compra" +
                "(timestamp, fk_cartao, fk_pessoa_fisica, fk_supermercado)" +
                " VALUES (CURRENT_TIMESTAMP, ?, ?, ?)";

        PreparedStatement st = conexao.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);

        // Defina os valores que ocuparão as '?' na ordem acima;
        st.setInt(1, cartao.getId());
        st.setInt(2, cliente.getId());
        st.setInt(3, supermercado.getId());

        // Execute o INSERT e receba o ID da Compra cadastrada no BD;
        st.executeUpdate();
        int id = getIdAtCreate(st);

        st.close();
        conexao.close();
        
        //Salva os itens no BD. Temos que pensar o q fazer caso dê alguma exceção na inserção de qualquer item
        //Eu acredito que seria bom armazenar os ids retornador em um vetor (hehehe)
        //e caso de alguma exceção, apaga todos os itens inseridos e depois apaga a compra em si 
        for (ItemProduto item : compra.getItens()){
            int idItem = ItemProdutoDAO.create(id, item);
        }

        return id;
    }
}
