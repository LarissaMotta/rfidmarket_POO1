package database.usuarios;

import database.core.CoreDAO;
import database.filter.Clause;
import database.filter.Filter;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.pagamento.Cartao;
import modelo.usuarios.Cliente;
import modelo.usuarios.Endereco;
import modelo.usuarios.PessoaFisica.Genero;
import modelo.supermercado.Supermercado;
import util.Util;

//Classe criada p/ abstrair a manipulação de objetos cliente no Banco de Dados;
public abstract class ClienteDAO extends CoreDAO{

    /**
     * Insere um cliente na base de dados;
     * @param cliente Cliente a ser gravado na base de dados;
     * @return Inteiro que representa o ID do cliente inserido no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(Cliente cliente) throws ClassNotFoundException, SQLException {
        return PessoaFisicaDAO.create(cliente);
    }

    /**
     * Cria uma relação no BD entre cartão e cliente, ambos já devem estar
     * inseridos na base;
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
        PreparedStatement ps = conexao.prepareStatement (sql);

        // Defina os valores que ocuparão as '?' na ordem acima;
        ps.setInt(1, cartao.getId());
        ps.setInt(2, cliente.getId());

        // Execute o INSERT e receba o ID do cartão cadastrado no BD;
        ps.executeUpdate();

        ps.close();
        conexao.close();
    }

    private static Cliente readCliente(PreparedStatement ps)
            throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {

        ResultSet rs = ps.executeQuery();
        rs.next();

        return readCliente(rs);
    }

    /**
     * Com base em um ResultSet, cria e retorna um novo cliente com os dados
     * da linha do RS.
     * @param rs resultSet com os dados do cliente;
     * @return Cliente gerado com base nos dados do RS;
     * @throws SQLException
     */
    private static Cliente readCliente(ResultSet rs)
            throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {

        String cpf = rs.getString("cpf");
        Date dtNasc = new Date(rs.getDate("data_nasc").getTime());
        char gen = rs.getString("genero").charAt(0);
        String login = rs.getString("login");
        String senha = rs.getString("senha");
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        Endereco end = PessoaDAO.getEndereco(rs);
        
        Genero genero = null;
        if (gen == 'M') genero = Genero.M;
        else genero = Genero.F;

        return new Cliente(cpf, dtNasc, genero, login, senha, id, nome, end);
    }

    /**
     * Retorna um conjunto de clientes do supermercado recebido como parâmetro
     * fazendo os devidos filtros podendo ser: 
     * total - onde todos os parametros de filtro são passados,
     * parcial - onde alguns parametros de filtro são passados e outros são null,
     * sem filtros - onde todos os parametros de filtro são null.
     * @param superm supermercado que contém os clientes buscados;
     * @param nome usado para filtro de clientes que comecam com esse nome
     * @param cpf usado para filtro do cliente que tenha este cpf 
     * @return lista de clientes compradores no supermercado recebido;
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     */
    public static List<Cliente> readClientesBySupermercado(Supermercado superm, String nome, String cpf)
            throws SQLException, ClassNotFoundException, IllegalArgumentException, UnsupportedEncodingException, NoSuchAlgorithmException {

        // Crie e inicialize a lista, e abra uma conexão com o BD;
        List<Cliente> clientes = new ArrayList<>();

        Connection conexao = getConnection();

        if (cpf != null && !Util.isCpfValido(cpf)) throw new IllegalArgumentException("CPF inválido!");
        
        Filter filter = new Filter();
        
        Clause clause = new Clause("p.nome",nome+"%", Clause.ILIKE);
        filter.addClause(clause);
        
        clause = new Clause("pf.cpf",cpf, Clause.IGUAL);
        filter.addClause(clause);
        
        // Forme a string sql;
        String sql = "SELECT p.id, p.nome, p.numero, p.rua, p.cep, p.bairro," +
                "p.estado, p.cidade, pf.data_nasc, pf.genero, pf.login, pf.senha," +
                "pf.cpf FROM hist_compra as hc " +
                "INNER JOIN fisica as pf ON hc.fk_cliente = pf.fk_pessoa " +
                "INNER JOIN usuarios as p ON pf.fk_pessoa = p.id "
                + "WHERE hc.fk_supermercado = ? " + filter.getFilter();
        
        // Substitua a '?' pelo valor da coluna;
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, superm.getId());

        ResultSet rs = ps.executeQuery();

        // Enquanto houver linhas, gere um cliente c/ a linha e add. na lista;
        while (rs.next()) clientes.add(readCliente(rs));

        ps.close();
        conexao.close();

        return clientes;
    }
}
