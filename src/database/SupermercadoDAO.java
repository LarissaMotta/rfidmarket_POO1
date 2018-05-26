package database;

import database.core.CoreDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.cliente.Compra;
import modelo.pessoa.Endereco;
import modelo.supermercado.Funcionario;

import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Fornecedor;
import modelo.supermercado.mercadoria.Lote;
import modelo.supermercado.mercadoria.Produto;
import org.postgresql.util.PSQLException;

/**
 *
 * @author joel-
 */
public abstract class SupermercadoDAO extends CoreDAO {
    
    
    /**
     * Insere um supermercado na base de dados;
     *
     * @param supermercado Supermercado a ser inserido na base;
     * @return Inteiro que representa o ID do supermercado inserido no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(Supermercado supermercado) throws ClassNotFoundException, SQLException {

        int id = PessoaJuridicaDAO.create(supermercado);
        Connection conn = getConnection();
        String sql = "INSERT INTO supermercado (longitude,latitude,unidade,fk_pessoa_juridica) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setDouble(1, supermercado.getLongitude());
        st.setDouble(2, supermercado.getLatitude());
        st.setString(3, supermercado.getUnidade());
        st.setInt(4, id);

        try {
            st.executeUpdate();
        } catch (PSQLException ex) {
            // se ocorrer algum erro durante a inseção do restante dos dados
            // apaga o que ja foi inserido e lança a exceção
            PessoaJuridicaDAO.delete(id);
        }

        st.close();
        conn.close();

        return id;
    }
    
    //Joel
    public static Supermercado readSupermercadoByFuncionario(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT pessoa.id, latitude, longitude, unidade, cnpj, nome, numero, rua, cep, bairro, estado, cidade FROM funcionario "
                + "INNER JOIN supermercado ON funcionario.fk_supermercado = supermercado.fk_pessoa_juridica "
                + "INNER JOIN juridica ON supermercado.fk_pessoa_juridica = juridica.fk_pessoa "
                + "INNER JOIN pessoa ON juridica.fk_pessoa = pessoa.id "
                + "WHERE funcionario.fk_pessoa_fisica = ?";

        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, funcionario.getId());
        
        Supermercado supermercado = readSupermercado(st);
        
        st.close();
        conexao.close();

        return supermercado;
    }
    
    //Joel
    public static Supermercado readSupermercadoByCompra(Compra compra) throws SQLException, ClassNotFoundException {
        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT pessoa.id, latitude, longitude, unidade, cnpj, nome, numero, rua, cep, bairro, estado, cidade FROM hist_compra "
                + "INNER JOIN supermercado ON hist_compra.fk_supermercado = supermercado.fk_pessoa_juridica "
                + "INNER JOIN juridica ON supermercado.fk_pessoa_juridica = juridica.fk_pessoa "
                + "INNER JOIN pessoa ON juridica.fk_pessoa = pessoa.id "
                + "WHERE hist_compra.id = ?";

        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, compra.getId());
        
        Supermercado supermercado = readSupermercado(st);
        
        st.close();
        conexao.close();

        return supermercado;
    }
    
    //Joel
    public static List<Supermercado> readSupermercadoByFornecedor(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT pessoa.id, latitude, longitude, unidade, cnpj, nome, numero, rua, cep, bairro, estado, cidade FROM fornecimento "
                + "INNER JOIN supermercado ON fornecimento.fk_supermercado = supermercado.fk_pessoa_juridica "
                + "INNER JOIN juridica ON supermercado.fk_pessoa_juridica = juridica.fk_pessoa "
                + "INNER JOIN pessoa ON juridica.fk_pessoa = pessoa.id "
                + "WHERE fornecimento.fk_fornecedor = ?";

        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, fornecedor.getId());
        
        ResultSet rs = st.executeQuery();
        
        List<Supermercado> supermercados = new ArrayList<>();
        while (rs.next()){
            supermercados.add(readSupermercado(rs));
        }
        
        st.close();
        conexao.close();

        return supermercados;
    }
    
    //Joel
    public static Supermercado readSupermercadoByLote(Lote lote) throws SQLException, ClassNotFoundException {
        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT pessoa.id, latitude, longitude, unidade, cnpj, nome, pessoa.numero, rua, cep, bairro, estado, cidade FROM lote "
                + "INNER JOIN supermercado ON lote.fk_supermercado = supermercado.fk_pessoa_juridica "
                + "INNER JOIN juridica ON supermercado.fk_pessoa_juridica = juridica.fk_pessoa "
                + "INNER JOIN pessoa ON juridica.fk_pessoa = pessoa.id "
                + "WHERE lote.id = ?";

        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, lote.getId());
        
        Supermercado supermercado = readSupermercado(st);
        
        st.close();
        conexao.close();

        return supermercado;
    }
    
    //Joel
    public static Supermercado readSupermercadoByProduto(Produto produto) throws SQLException, ClassNotFoundException {
        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT pessoa.id, latitude, longitude, unidade, cnpj, pessoa.nome, numero, rua, cep, bairro, estado, cidade FROM produto "
                + "INNER JOIN supermercado ON produto.fk_supermercado = supermercado.fk_pessoa_juridica "
                + "INNER JOIN juridica ON supermercado.fk_pessoa_juridica = juridica.fk_pessoa "
                + "INNER JOIN pessoa ON juridica.fk_pessoa = pessoa.id "
                + "WHERE produto.id = ?";

        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, produto.getId());
        
        Supermercado supermercado = readSupermercado(st);
        
        st.close();
        conexao.close();

        return supermercado;
    }
   
    //Joel
    //Esse metodo deve ser pode ser usado em todos os outros readSupermecados... para instaciar o supermercado
    private static Supermercado readSupermercado(PreparedStatement st) throws SQLException{
        ResultSet rs = st.executeQuery();
        rs.next();
        return readSupermercado(rs);
    }
    
    //Joel
    //Esse metodo deve ser pode ser usado em todos os outros readSupermecados... para instaciar o supermercado
    private static Supermercado readSupermercado(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        double latitude = rs.getDouble("latitude");
        double longitude = rs.getDouble("longitude");
        String unidade = rs.getString("unidade");
        String cnpj = rs.getString("cnpj");
        String nome = rs.getString("nome");
        Endereco endereco = PessoaDAO.getEndereco(rs);
        
        return new Supermercado(id, latitude, longitude, unidade, cnpj, nome, endereco);
    }
}
