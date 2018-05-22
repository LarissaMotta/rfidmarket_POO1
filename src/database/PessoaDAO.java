package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.pessoa.Endereco;
import modelo.pessoa.Pessoa;

/**
 *
 * @author joel-
 */
public abstract class PessoaDAO extends DBCommand{

    /**
     * Insere uma pessoa na base de dados;
     * @param pessoa pessoa a ser gravada na base de dados;
     * @return Inteiro que representa o ID da pessoa inserida no BD;
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
        st.setString(7, endereco.getEstado());

        st.executeUpdate();
        int id = getIdAtCreate(st);
        st.close();
        conn.close();

        return id;
    }
    
    // tem que perguntar ao professor se pode deixar como argumento o id ou
    // se tem que ser obrigatoriamente um objeto do tipo pessoa, pois pode infligir,
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
}
