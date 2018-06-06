package database;

import database.core.CoreDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.usuarios.Contato;
import modelo.usuarios.Pessoa;

/**
 * Created by 20162bsi0511 on 21/05/2018.
 */
public abstract class ContatoDAO extends CoreDAO {

    /**
     * Insere um contato na base de dados;
     * @param contato contato a ser gravado na base de dados;
     * @param pessoa usuarios relacionada ao contato recebido;
     * @return Inteiro que representa o ID do contato inserido no BD;
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static int create(Contato contato, Pessoa pessoa) throws SQLException, ClassNotFoundException {

        Connection conn = getConnection();
        String sql = "INSERT INTO contato (descricao,tipo,fk_pessoa) "
                + "VALUES (?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, contato.getDescricao());
        st.setString(2, contato.getTipo().toString());
        st.setInt(3, pessoa.getId());

        st.executeUpdate();
        int id = getIdAtCreate(st);
        st.close();
        conn.close();

        return id;
    }
    
    public static List<Contato> readContatosByPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException{
        List<Contato> lstContatos = new ArrayList<>();

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT * from contato WHERE fk_pessoa = ?";
 
        PreparedStatement st = conexao.prepareStatement (sql);
        st.setInt(1, pessoa.getId());
        
        ResultSet rs = st.executeQuery();

        // Enquanto houver algum contato resultado da busca;
        while (rs.next()) {
            int id = rs.getInt("id");
            String descricao = rs.getString("descricao");
            String tipo = rs.getString("tipo");
            
            Contato.Tipo type = null;
            for (Contato.Tipo t : Contato.Tipo.values()){
                if (t.toString().equals(tipo)){
                    type = t;
                    break;
                }
            }
           
            lstContatos.add(new Contato(id,descricao,type));
        }

        st.close();
        conexao.close();

        return lstContatos;
    }
    

    
}
