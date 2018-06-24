package database.supermercado.mercadoria;

import database.core.CoreDAO;
import database.filter.Clause;
import database.filter.Filter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Fornecedor;
import modelo.supermercado.mercadoria.Lote;
import modelo.supermercado.mercadoria.Produto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.Util;

public abstract class LoteDAO extends CoreDAO {

    /**
     * Insere um lote de um produto na base de dados;
     *
     * @param lote lote a ser escrito na base de dados;
     * @param forn fornecedor do lote de produtos;
     * @param prod produto que corresponde as unidades do lote;
     * @param superm supermercado proprietário do lote de produtos;
     * @return Inteiro que representa o ID do lote inserido no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(Lote lote, Fornecedor forn, Produto prod, Supermercado superm) throws ClassNotFoundException, SQLException {

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string básica de sql;
        String sql = "INSERT INTO lote"
                + "(data_compra, identificador, fabricacao, quantidade, validade,"
                + "fk_fornecedor, fk_produto, fk_supermercado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // Defina os valores que ocuparão as '?' na ordem acima;
        ps.setDate(1, new java.sql.Date(lote.getDataCompra().getTime()));
        ps.setString(2, lote.getIdentificador());
        ps.setDate(3, new java.sql.Date(lote.getDataFabricacao().getTime()));
        ps.setInt(4, lote.getNumUnidades());

        if (lote.getDataValidade() == null) {
            ps.setDate(5, null);
        } else {
            ps.setDate(5, new java.sql.Date(lote.getDataValidade().getTime()));
        }

        ps.setInt(6, forn.getId());
        ps.setInt(7, prod.getId());
        ps.setInt(8, superm.getId());

        // Execute o INSERT e receba o ID do cartão cadastrado no BD;
        ps.executeUpdate();
        int id = getIdAtCreate(ps);

        ps.close();
        conexao.close();

        return id;
    }

    private static Lote readLote(PreparedStatement ps)
            throws SQLException, ClassNotFoundException {

        ResultSet rs = ps.executeQuery();
        rs.next();

        return readLote(rs, null);
    }

    /**
     * Com base em um ResultSet, cria e retorna um novo Lote de produtos com os
     * dados da linha do RS.
     *
     * @param rs resultSet com os dados do Lote;
     * @return Lote gerado com base nos dados do RS;
     * @throws SQLException
     */
    private static Lote readLote(ResultSet rs, Produto prod)
            throws SQLException, ClassNotFoundException {

        if (prod == null) {
            prod = ProdutoDAO.readProdutos(rs);
        }

        int id = rs.getInt("id");
        java.util.Date dtComp = new java.util.Date(rs.getDate("data_compra").getTime());
        java.util.Date dtFab = new java.util.Date(rs.getDate("fabricacao").getTime());
        java.util.Date dtVal = null;
        if (rs.getDate("validade") != null) {
            dtVal = new java.util.Date(rs.getDate("validade").getTime());
        }
        int numUnid = rs.getInt("quantidade");
        String ident = rs.getString("identificador");

        return new Lote(id, dtComp, dtFab, dtVal, numUnid, ident, prod);
    }

    //Jubileu
    /**
     * Dado um supermercado, retorna um conjunto de lotes associados ao
     * supermercado;
     *
     * @param superm supermercado detentor dos lotes de produtos;
     * @param ident
     * @param dataFabMin
     * @param dataFabMax
     * @param dataValMin
     * @param dataValMax
     * @param dataCompraMin
     * @param dataCompraMax
     * @return Lista com todos lotes associados ao supermercado;
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<Lote> readLotesBySupermercado(Supermercado superm, String ident, Date dataFabMin,
            Date dataFabMax, Date dataValMin, Date dataValMax, Date dataCompraMin, Date dataCompraMax)
            throws SQLException, ClassNotFoundException {

        // Crie e inicialize a lista, e abra uma conexão com o BD;
        List<Lote> lotes = new ArrayList<>();

        if (!Util.isIntervalValid(dataFabMin, dataFabMax)) {
            throw new IllegalArgumentException("Intervalo de data de fabricação inválido!");
        }

        if (!Util.isIntervalValid(dataValMin, dataValMax)) {
            throw new IllegalArgumentException("Intervalo de data de validade inválido!");
        }

        if (!Util.isIntervalValid(dataCompraMin, dataCompraMax)) {
            throw new IllegalArgumentException("Intervalo de data de compra inválido!");
        }

        Connection conexao = getConnection();

        Filter filter = new Filter();

        Clause clause = new Clause("identificador", ident, Clause.IGUAL);
        filter.addClause(clause);

        clause = new Clause("fabricacao", dataFabMin, Clause.MAIOR_IGUAL);
        filter.addClause(clause);

        clause = new Clause("fabricacao", dataFabMax, Clause.MENOR_IGUAL);
        filter.addClause(clause);

        clause = new Clause("validade", dataValMin, Clause.MAIOR_IGUAL);
        filter.addClause(clause);

        clause = new Clause("validade", dataValMax, Clause.MENOR_IGUAL);
        filter.addClause(clause);

        clause = new Clause("data_compra", dataCompraMin, Clause.MAIOR_IGUAL);
        filter.addClause(clause);

        clause = new Clause("data_compra", dataCompraMax, Clause.MENOR_IGUAL);
        filter.addClause(clause);

        // Forme a string sql;
        String sql = "SELECT lote.id, data_compra, fabricacao, validade, quantidade, identificador, "
                + "produto.id as prodID, nome, preco, codigo, descricao, custo, estoque, tipo, quant_prateleira, marca FROM lote "
                + "INNER JOIN produto ON (lote.fk_produto = produto.id) "
                + "WHERE lote.fk_supermercado = ? " + filter.getFilter()
                + " ORDER BY identificador";

        // Substitua a '?' pelo valor da coluna;
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, superm.getId());

        ResultSet rs = ps.executeQuery();

        // Enquanto houver linhas, gere um lote c/ a linha e add. na lista;
        while (rs.next()) {
            lotes.add(readLote(rs, null));
        }

        ps.close();
        conexao.close();

        return lotes;
    }

    //Jubileu
    /**
     * Dado um produto, retorna um conjunto de lotes do produto;
     *
     * @param produto item que compõem o(s) lote(s);
     * @return Lista com todos lotes compostos pelo produto recebido;
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<Lote> readLotesByProduto(Produto produto)
            throws SQLException, ClassNotFoundException {

        // Crie e inicialize a lista, e abra uma conexão com o BD;
        List<Lote> lotes = new ArrayList<>();

        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT id, data_compra, fabricacao, validade, "
                + "quantidade, identificador FROM lote WHERE fk_produto = ? "
                + "ORDER BY identificador";

        // Substitua a '?' pelo valor da coluna;
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, produto.getId());

        ResultSet rs = ps.executeQuery();

        // Enquanto houver linhas, gere um lote c/ a linha e add. na lista;
        while (rs.next()) {
            lotes.add(readLote(rs, produto));
        }

        ps.close();
        conexao.close();

        return lotes;
    }

    public static List<Lote> readLotesProxVal(Produto produto, int distValidade) throws ClassNotFoundException, SQLException {
        // Crie e inicialize a lista, e abra uma conexão com o BD;
        List<Lote> lotes = new ArrayList<>();
        
        if (distValidade <= 0) throw new IllegalArgumentException("Distância (dias) da validade não pode ser menor que 1");

        Connection conexao = getConnection();
        //TODO Considerar a partir de hoje ou Qualquer dia anterior hoje + distValidade?
        String sql = "SELECT id, data_compra, fabricacao, validade,"
                + "quantidade, identificador FROM lote "
                + "WHERE fk_produto = ? AND "
                + "validade <= CURRENT_DATE + " + distValidade
                + " ORDER BY validade, fabricacao, data_compra, identificador";

        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, produto.getId());

        ResultSet rs = ps.executeQuery();

        // Enquanto houver linhas, gere um lote c/ a linha e add. na lista;
        while (rs.next()) {
            lotes.add(readLote(rs, produto));
        }

        ps.close();
        conexao.close();

        return lotes;
    }

    //Jubileu
    /**
     * Dado um fornecedor, retorna uma lista de lotes oferecidos por essa
     * entidade;
     *
     * @param fornecedor entidade que fornece os lotes de produtos;
     * @param supermercado
     * @return Lista de lotes produzidos pelo fornecedor;
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<Lote> readLotesByFornecedor(Fornecedor fornecedor, Supermercado supermercado)
            throws SQLException, ClassNotFoundException {

        // Crie e inicialize a lista, e abra uma conexão com o BD;
        List<Lote> lotes = new ArrayList<>();

        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT lote.id, data_compra, fabricacao, validade, quantidade, identificador, "
                + "produto.id as prodID, nome, preco, codigo, descricao, custo, estoque, tipo, quant_prateleira, marca FROM lote "
                + "INNER JOIN produto ON (lote.fk_produto = produto.id) "
                + "WHERE lote.fk_fornecedor = ? AND lote.fk_supermercado = ? "
                + "ORDER BY identificador";

        // Substitua a '?' pelo valor da coluna;
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, fornecedor.getId());
        ps.setInt(2, supermercado.getId());

        ResultSet rs = ps.executeQuery();

        // Enquanto houver linhas, gere um lote c/ a linha e add. na lista;
        while (rs.next()) {
            lotes.add(readLote(rs, null));
        }

        ps.close();
        conexao.close();

        return lotes;
    }

    /**
     * Dado um lote já registrado na base, atualiza os dados desse lote com as
     * informações do lote recebido como parâmetro;
     *
     * @param lote lote a ser atualizado na base de dados;
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void update(Lote lote)
            throws SQLException, ClassNotFoundException {

        Connection conn = getConnection();
        String sql = "UPDATE lote SET data_compra = ?, identificador = ?, "
                + "fabricacao = ?, quantidade = ?, validade = ? WHERE id = ?;";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // Defina os valores que ocuparão as '?' na ordem acima;
        ps.setDate(1, new java.sql.Date(lote.getDataCompra().getTime()));
        ps.setString(2, lote.getIdentificador());
        ps.setDate(3, new java.sql.Date(lote.getDataFabricacao().getTime()));
        ps.setInt(4, lote.getNumUnidades());
        ps.setInt(6, lote.getId());

        if (lote.getDataValidade() == null) {
            ps.setDate(5, null);
        } else {
            ps.setDate(5, new java.sql.Date(lote.getDataValidade().getTime()));
        }

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public static void delete(int id) throws SQLException, ClassNotFoundException {

        Connection conn = getConnection();
        String sql = "DELETE FROM lote WHERE id = ?";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        st.executeUpdate();

        st.close();
        conn.close();
    }
}
