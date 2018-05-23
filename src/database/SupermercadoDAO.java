package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import modelo.cliente.Compra;
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
public abstract class SupermercadoDAO extends DBCommand {

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

    public static Supermercado readSupermercadoByFuncionario(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT * from utiliza WHERE fk_supermercado = ?"; // Tem que fazer os joins para consertar

        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, funcionario.getId());

        Supermercado supermercado = readSupermercado(st);
        
        st.close();
        conexao.close();

        return supermercado;
    }
    
    //Joel
    public static Supermercado readSupermercadoByCompra(Compra compra) throws SQLException, ClassNotFoundException {
        
    }
    
    //Joel
    public static List<Supermercado> readSupermercadoByFornecedor(Fornecedor fornecedor) throws SQLException, ClassNotFoundException {
        
    }
    
    //Joel
    public static Supermercado readSupermercadoByLote(Lote lote) throws SQLException, ClassNotFoundException {
        
    }
    
    //Joel
    public static Supermercado readSupermercadoByProduto(Produto produto) throws SQLException, ClassNotFoundException {
        
    }
    
    //Esse metodo deve ser usada em todos os outros readSupermecados... para instaciar o supermercado
    //
    private static Supermercado readSupermercado(PreparedStatement st) throws SQLException{
        ResultSet rs = st.executeQuery();
        
        rs.next();
        
        //Tem que implementar o restante da função para pegar os dados da tabela
        //E retornar um Supermercado
        int id = rs.getInt("id");
        /*
        restante do codigo
        */
        
        return new Supermercado(id, latitude, longitude, unidade, cnpj, nome, endereco);
    }
}
