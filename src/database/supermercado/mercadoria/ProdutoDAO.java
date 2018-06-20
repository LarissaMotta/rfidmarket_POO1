package database.supermercado.mercadoria;

import database.core.CoreDAO;
import static database.core.CoreDAO.getConnection;
import database.filter.Clause;
import database.filter.Filter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Produto;

public abstract class ProdutoDAO extends CoreDAO {

    /**
     * Insere um produto na base de dados;
     *
     * @param produto      produto a ser escrito na base de dados;
     * @param supermercado supermercado que dispõe do produto;
     * @return Inteiro que representa o ID do produto inserido no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(Produto produto, Supermercado supermercado) throws ClassNotFoundException, SQLException {

        Connection conn = getConnection();
        String sql = "INSERT INTO produto" +
                "(nome, preco, codigo, descricao, custo, estoque," +
                "tipo, quant_prateleira, marca, fk_supermercado) "
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
    public static List<Produto> readProdutosBySupermercado(Supermercado supermercado, String nome, String marca, String tipo, String cod) throws SQLException, ClassNotFoundException {
        List<Produto> produtos = new ArrayList<>();

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        Filter filter = new Filter();

        Clause clause = new Clause("nome", nome + "%", Clause.ILIKE);
        filter.addClause(clause);

        clause = new Clause("marca", marca + "%", Clause.ILIKE);
        filter.addClause(clause);

        clause = new Clause("tipo", tipo + "%", Clause.ILIKE);
        filter.addClause(clause);

        clause = new Clause("codigo", cod, Clause.IGUAL);
        filter.addClause(clause);

        // Forme a string sql;
        String sql = "SELECT * from produto "
                + "WHERE fk_supermercado = ? " + filter.getFilter(); //REVER SE ESTA CERTO !

        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, supermercado.getId());

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            produtos.add(readProdutos(rs));//id,codigo,custo,descricao,marca,nome,precoVenda,qtdPrateleira,qtdEstoque,tipo));
        }

        st.close();
        conexao.close();

        return produtos;

    }


    private static Produto readProdutos(PreparedStatement st) throws SQLException {
        ResultSet rs = st.executeQuery();
        rs.next();
        return readProdutos(rs);
    }

    //Jennifer
    static Produto readProdutos(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        String codigo = rs.getString("codigo");
        double custo = rs.getDouble("custo");
        String descricao = rs.getString("descricao");
        String marca = rs.getString("marca");
        String nome = rs.getString("nome");
        double precoVenda = rs.getDouble("preco");
        int qtdPrateleira = rs.getInt("quant_prateleira");
        int qtdEstoque = rs.getInt("estoque");
        String tipo = rs.getString("tipo");

        return new Produto(id, codigo, custo, descricao, marca, nome, precoVenda, qtdPrateleira, qtdEstoque, tipo);
    }

    /**
     * Dado um produto já registrado na base, atualiza os dados desse produto com
     * as informações do produto recebido como parâmetro;
     * @param produto Produto a ser atualizado na base de dados;
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void updateProduto(Produto produto)
            throws SQLException, ClassNotFoundException {


        Connection conn = getConnection();
        String sql = "UPDATE produto SET nome = ?, preco = ?, codigo = ?, "
                + "descricao = ?, custo = ?, estoque = ?, tipo = ?, "
                + "quant_prateleira = ?, marca = ? WHERE id = ?;";

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
        st.setInt(10, produto.getId());
        st.executeUpdate();
        st.close();
        conn.close();
    }


    /*
    public static void main(String args[]) {

    // criacao do hashmap
    HashMap<Produto, Integer> map2 = new HashMap<Produto, Integer>();

    Statement st = null;
    ResultSet rs = null;
    Connection con = getConnection();
    Produto prod;

    try{
    st = con.createStatement();
    rs = st.executeQuery("SELECT p.nome "Nome produto", c.quant AS "Unidades vendidas" FROM produto p INNER JOIN compra c ON c.fk_produto = p.id INNER JOIN hist_compra h ON h.id = c.fk_hist_compra  WHERE h.fk_supermercado = ? AND h.timestamp >= ? AND h.timestamp <= ? GROUP BY (p.nome, c.quant) ORDER BY 2 DESC;");

    while(rs.next()){
    //nome,preco,codigo,descricao,custo,id,estoque, tipo, quant_pratelereira, marca,fk_supermercado
    String nome = rs.getString("nome");
    double preco = rs.getDouble("preco");
    String codigo = rs.getString("codigo");
    String descricao = rs.getString("descricao");
    double custo = rs.getDouble("custo");
    int id = rs.getInt("id");
    int estoque = rs.getInt("esroque");
    String tipo = rs.getString("tipo");
    int quant_prateleira = rs.getInt("quant_prateleira");
    String marca = rs.getString("marca");
    int fk_supermercado = rs.getInt("fk_supermercado");
    //int id, String codigo, double custo, String descricao, String marca, String nome, double precoVenda, int qtdPrateleira, int qtdEstoque, String tipo)

    prod = new Produto(id,codigo,custo,descricao,marca,nome,preco,quant_prateleira,estoque,tipo);


    map2.put(prod,id);
    }
    }

    catch(Exception ex){
    ex.printStackTrace();
    }
    }

    public static void main(String args[]) {
    // PRODUTOS PERTO DO VENCIMENTO
    HashMap<Produto, String> map = new HashMap<Produto, String>();

    Statement st = null;
    ResultSet rs = null;
    Connection con = getConnection();
    Produto prod;

    try {
    st = con.createStatement();
    rs = st.executeQuery("SELECT p.nome "Produto", l.validade "Data validade", l.identificador "Ident.Lote", l.fk_fornecedor \"Fornecedor\" FROM produto p INNER JOIN lote l ON l.fk_produto = p.id WHERE p.fk_supermercado = ? AND l.validade >= CURRENT_DATE ORDER BY 2;");
    //nome,preco,codigo,descricao,custo,id,estoque, tipo, quant_pratelereira, marca,fk_supermercado
    String nome = rs.getString("nome");
    double preco = rs.getDouble("preco");
    String codigo = rs.getString("codigo");
    String descricao = rs.getString("descricao");
    double custo = rs.getDouble("custo");
    int id = rs.getInt("id");
    int estoque = rs.getInt("esroque");
    String tipo = rs.getString("tipo");
    int quant_prateleira = rs.getInt("quant_prateleira");
    String marca = rs.getString("marca");
    int fk_supermercado = rs.getInt("fk_supermercado");
    //int id, String codigo, double custo, String descricao, String marca, String nome, double precoVenda, int qtdPrateleira, int qtdEstoque, String tipo)

    prod = new Produto(id, codigo, custo, descricao, marca, nome, preco, quant_prateleira, estoque, tipo);


    map.put(prod, nome);
    } catch (Exception ex) {
    ex.printStackTrace();
    }
    }
    */
}