package database;

import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProdutoDAO extends DBCommand{

    /**
     * Insere um produto na base de dados;
     * @param produto produto a ser escrito na base de dados;
     * @param supermercado supermercado que dispõe do produto;
     * @return Inteiro que representa o ID do produto inserido no BD;
     */
    public static int create(Produto produto, Supermercado supermercado)
            throws ClassNotFoundException, SQLException {

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

}
