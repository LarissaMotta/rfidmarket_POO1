package database;

import modelo.supermercado.mercadoria.Produto;

import java.sql.*;

public class ProdutoDAO extends DBCommand{

    // Insere um produto no BD e retorna seu ID;
    //java n presta uvbbbh
    public static int create(Produto produto)
            throws ClassNotFoundException, SQLException {

        Connection conn = getConnection();
        String sql = "INSERT INTO pessoa" +
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

//       OBS: no BD na tabela produto pede a PK do supermercado;
//       Precisa? Como pegar? Qual relação inicial?
//       st.setInt(7, produto.get);

        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        rs.next();
        int id = rs.getInt("id");

        st.close();
        conn.close();

        return id;
    }

}
