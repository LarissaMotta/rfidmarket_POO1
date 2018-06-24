package database.usuarios;

import database.core.CoreDAO;
import database.filter.Clause;
import database.filter.Filter;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import modelo.pagamento.Cartao;
import modelo.usuarios.Cliente;
import modelo.usuarios.Endereco;
import modelo.usuarios.PessoaFisica.Genero;
import modelo.supermercado.Supermercado;
import util.Util;

//Classe criada p/ abstrair a manipulação de objetos cliente no Banco de Dados;
public abstract class ClienteDAO extends CoreDAO {

    /**
     * Insere um cliente na base de dados;
     *
     * @param cliente Cliente a ser gravado na base de dados;
     * @return Inteiro que representa o ID do cliente inserido no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(Cliente cliente) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return PessoaFisicaDAO.create(cliente);
    }

    /**
     * Cria uma relação no BD entre cartão e cliente, ambos já devem estar
     * inseridos na base;
     *
     * @param cliente cliente usuário do cartão;
     * @param cartao cartão utilizado pelo cliente;
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void addCartao(Cliente cliente, Cartao cartao)
            throws SQLException, ClassNotFoundException {

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string básica de sql;
        String sql = "INSERT INTO utiliza (fk_cartao, fk_cliente) VALUES (?, ?)";
        PreparedStatement ps = conexao.prepareStatement(sql);

        // Defina os valores que ocuparão as '?' na ordem acima;
        ps.setInt(1, cartao.getId());
        ps.setInt(2, cliente.getId());

        // Execute o INSERT e receba o ID do cartão cadastrado no BD;
        ps.executeUpdate();

        ps.close();
        conexao.close();
    }

    private static Cliente readCliente(PreparedStatement ps)
            throws SQLException {

        ResultSet rs = ps.executeQuery();
        rs.next();

        return readCliente(rs);
    }

    /**
     * Com base em um ResultSet, cria e retorna um novo cliente com os dados da
     * linha do RS.
     *
     * @param rs resultSet com os dados do cliente;
     * @return Cliente gerado com base nos dados do RS;
     * @throws SQLException
     */
    private static Cliente readCliente(ResultSet rs)
            throws SQLException {

        String cpf = rs.getString("cpf");
        Date dtNasc = new Date(rs.getDate("data_nasc").getTime());
        char gen = rs.getString("genero").charAt(0);
        String login = rs.getString("login");
        String senha = rs.getString("senha");
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        Endereco end = PessoaDAO.getEndereco(rs);

        Genero genero;
        if (gen == 'M') {
            genero = Genero.M;
        } else {
            genero = Genero.F;
        }

        return new Cliente(cpf, dtNasc, genero, login, senha, id, nome, end);
    }

    /**
     * Retorna um conjunto de clientes do supermercado recebido como parâmetro
     * fazendo os devidos filtros podendo ser: total - onde todos os parametros
     * de filtro são passados, parcial - onde alguns parametros de filtro são
     * passados e outros são null, sem filtros - onde todos os parametros de
     * filtro são null.
     *
     * @param superm supermercado que contém os clientes buscados;
     * @param nome usado para filtro de clientes que comecam com esse nome
     * @param cpf usado para filtro do cliente que tenha este cpf
     * @param genero
     * @return lista de clientes compradores no supermercado recebido;
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     */
    public static List<Cliente> readClientesBySupermercado(Supermercado superm, String nome, String cpf, Genero genero)
            throws SQLException, ClassNotFoundException, IllegalArgumentException {

        if (cpf != null && !Util.isCpfValido(cpf)) {
            throw new IllegalArgumentException("CPF inválido!");
        }

        // Crie e inicialize a lista, e abra uma conexão com o BD;
        List<Cliente> clientes = new ArrayList<>();

        Connection conexao = getConnection();

        Filter filter = new Filter();

        Clause clause = new Clause("p.nome", nome + "%", Clause.ILIKE);
        filter.addClause(clause);

        clause = new Clause("pf.cpf", cpf, Clause.IGUAL);
        filter.addClause(clause);

        if (genero != null) {
            clause = new Clause("pf.genero", genero.toChar(), Clause.IGUAL);
            filter.addClause(clause);
        }

        // Forme a string sql;
        String sql = "SELECT p.id, p.nome, p.numero, p.rua, p.cep, p.bairro,"
                + "p.estado, p.cidade, pf.data_nasc, pf.genero, pf.login, pf.senha,"
                + "pf.cpf FROM hist_compra as hc "
                + "INNER JOIN fisica as pf ON hc.fk_cliente = pf.fk_pessoa "
                + "INNER JOIN pessoa as p ON pf.fk_pessoa = p.id "
                + "WHERE hc.fk_supermercado = ? " + filter.getFilter()
                + " GROUP BY (p.id, p.nome, p.numero, p.rua, p.cep, p.bairro,"
                + "p.estado, p.cidade, pf.data_nasc, pf.genero, pf.login, pf.senha, pf.cpf) "
                + "ORDER BY p.nome";

        // Substitua a '?' pelo valor da coluna;
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, superm.getId());

        ResultSet rs = ps.executeQuery();

        // Enquanto houver linhas, gere um cliente c/ a linha e add. na lista;
        while (rs.next()) {
            clientes.add(readCliente(rs));
        }

        ps.close();
        conexao.close();

        return clientes;
    }

    public static Map<Cliente,Double> readClientesMaisConsumistas(Supermercado supermercado, Date dataMin, Date dataMax, Integer maxResult) 
            throws ClassNotFoundException, SQLException, IllegalArgumentException {
        if (!Util.isIntervalValid(dataMin, dataMax)) {
            throw new IllegalArgumentException("Intervalo de data inválido!");
        }
        
        Filter filter = new Filter();
        
        Clause clause = new Clause("DATE(hc.timestamp)", dataMin, Clause.MAIOR_IGUAL);
        filter.addClause(clause);
        
        clause = new Clause("DATE(hc.timestamp)", dataMax, Clause.MENOR_IGUAL);
        filter.addClause(clause);
        
        String sql = "SELECT SUM(c.preco_compra) as valor_comprado, p.id, p.nome, p.numero, p.rua, p.cep, p.bairro, "
                + "p.estado, p.cidade, pf.data_nasc, pf.genero, pf.login, pf.senha,pf.cpf "
                + "FROM hist_compra as hc "
                + "INNER JOIN fisica as pf ON hc.fk_cliente = pf.fk_pessoa "
                + "INNER JOIN pessoa as p ON pf.fk_pessoa = p.id "
                + "INNER JOIN compra as c ON c.fk_hist_compra = hc.id "
                + "WHERE hc.fk_supermercado = ? " + filter.getFilter()
                + " GROUP BY (p.id, p.nome, p.numero, p.rua, p.cep, p.bairro, "
                + "p.estado, p.cidade, pf.data_nasc, pf.genero, pf.login, pf.senha, pf.cpf) "
                + "ORDER BY SUM(c.preco_compra) DESC, p.nome";
        
        if (maxResult != null){
            if (maxResult <= 0) throw new IllegalArgumentException("Máx. Resultados não pode ser menor que 1");
            else sql += " LIMIT " + maxResult;
        }
        
        return readClienteRelatorio(supermercado, sql, "valor_comprado");
    }
    
    public static Map<Cliente,Double> readClientesMediaConsumo(Supermercado supermercado, Date dataMin, Date dataMax, Integer maxResult)
            throws ClassNotFoundException, SQLException, IllegalArgumentException {
        
        if (!Util.isIntervalValid(dataMin, dataMax)) {
            throw new IllegalArgumentException("Intervalo de data inválido!");
        }
        
        Filter filter = new Filter();
        
        Clause clause = new Clause("DATE(hc.timestamp)", dataMin, Clause.MAIOR_IGUAL);
        filter.addClause(clause);
        
        clause = new Clause("DATE(hc.timestamp)", dataMax, Clause.MENOR_IGUAL);
        filter.addClause(clause);
        
        String sql = "SELECT AVG(c.preco_compra) as media_compra, p.id, p.nome, p.numero, p.rua, p.cep, p.bairro, "
                + "p.estado, p.cidade, pf.data_nasc, pf.genero, pf.login, pf.senha,pf.cpf "
                + "FROM hist_compra as hc "
                + "INNER JOIN fisica as pf ON hc.fk_cliente = pf.fk_pessoa "
                + "INNER JOIN pessoa as p ON pf.fk_pessoa = p.id "
                + "INNER JOIN compra as c ON c.fk_hist_compra = hc.id "
                + "WHERE hc.fk_supermercado = ? " + filter.getFilter()
                + " GROUP BY (p.id, p.nome, p.numero, p.rua, p.cep, p.bairro, "
                + "p.estado, p.cidade, pf.data_nasc, pf.genero, pf.login, pf.senha, pf.cpf) "
                + "ORDER BY AVG(c.preco_compra) DESC, p.nome";
        
        if (maxResult != null){
            if (maxResult <= 0) throw new IllegalArgumentException("Máx. Resultados não pode ser menor que 1");
            else sql += " LIMIT " + maxResult;
        }
        
        return readClienteRelatorio(supermercado, sql, "media_compra");
    }
    
    private static Map<Cliente,Double> readClienteRelatorio(Supermercado supermercado, String sql, String colValor) throws ClassNotFoundException, SQLException{
        Map<Cliente,Double> map = new LinkedHashMap<>();

        Connection conexao = getConnection();
        
        // Substitua a '?' pelo valor da coluna;
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, supermercado.getId());

        ResultSet rs = ps.executeQuery();

        // Enquanto houver linhas, gere um cliente c/ a linha e add. na lista;
        while (rs.next()) {
            Cliente cliente = readCliente(rs);
            double valor = rs.getDouble(colValor);
            map.put(cliente, valor);
        }

        ps.close();
        conexao.close();

        return map;
    }
}
