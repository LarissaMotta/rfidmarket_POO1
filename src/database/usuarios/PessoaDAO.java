package database.usuarios;

import database.core.CoreDAO;
import static database.core.CoreDAO.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.usuarios.Endereco;
import modelo.usuarios.Endereco.Estado;
import modelo.usuarios.Pessoa;

/**
 *
 * @author joel-
 */
public abstract class PessoaDAO extends CoreDAO{

    /**
     * Insere uma usuarios na base de dados;
     * @param pessoa usuarios a ser gravada na base de dados;
     * @return Inteiro que representa o ID da usuarios inserida no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(Pessoa pessoa) throws ClassNotFoundException, SQLException {

        Connection conn = getConnection();
        String sql = "INSERT INTO pessoa (nome,numero,rua,cep,bairro,cidade,estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Endereco endereco = pessoa.getEndereco();

        PreparedStatement st = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        st.setString(1, pessoa.getNome());
        st.setInt(2, endereco.getNumero());
        st.setString(3, endereco.getRuaAvenida());
        st.setString(4, endereco.getCep());
        st.setString(5, endereco.getBairro());
        st.setString(6, endereco.getCidade());
        st.setString(7, endereco.getEstado().toString());

        st.executeUpdate();
        int id = getIdAtCreate(st);
        st.close();
        conn.close();

        return id;
    }
    
    public static void update (Pessoa p) throws ClassNotFoundException, SQLException{
        Connection conn = getConnection();
        String sql = "UPDATE pessoa "
                + "SET nome = ?, "
                + "SET numero = ?, "
                + "SET rua = ?, "
                + "SET cep = ?, "
                + "SET bairro = ?, "
                + "SET cidade = ?, "
                + "SET estado = ?, "
                + "WHERE id = ?";
        
        Endereco endereco = p.getEndereco();
        
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, p.getNome());
        st.setInt(2, endereco.getNumero());
        st.setString(3, endereco.getRuaAvenida());
        st.setString(4, endereco.getCep());
        st.setString(5, endereco.getBairro());
        st.setString(6, endereco.getCidade());
        st.setString(7, endereco.getEstado().toString());
        st.setInt(8, p.getId());
        
        st.executeUpdate();
        st.close();
        conn.close();
    }
    
    // tem que perguntar ao professor se pode deixar como argumento o id ou
    // se tem que ser obrigatoriamente um objeto do tipo usuarios, pois pode infligir,
    // algumas regras de POO e do modelo DAO
    public static void delete(int id) throws ClassNotFoundException, SQLException{
        Connection conn = getConnection();
        String sql = "DELETE FROM pessoa WHERE id = ?";
        
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        st.executeUpdate();
        
        st.close();
        conn.close();
    }
    
    public static Endereco getEndereco(ResultSet rs) throws SQLException{
        int numero = rs.getInt("numero");
        String ruaAvenida = rs.getString("rua");
        String cep = rs.getString("cep");
        String bairro = rs.getString("bairro");
        String cidade = rs.getString("cidade");
        String estado = rs.getString("estado");
        
        Estado state = null;
        for (Estado e : Estado.values()){
            if (e.toString().equals(estado)){
                state = e;
                break;
            }
        }
        
        return new Endereco(bairro, cep, cidade, state, numero, ruaAvenida);
    }
}
