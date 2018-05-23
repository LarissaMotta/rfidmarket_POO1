package database;

import static database.DBCommand.getConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.supermercado.mercadoria.Fornecedor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.pessoa.Endereco;
import modelo.supermercado.Funcionario;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Lote;

public abstract class FornecedorDAO extends DBCommand{

    /**
     * Insere um fornecedor na base de dados;
     * @param fornecedor a ser gravado na base de dados;
     * @return Inteiro que representa o ID do fornecedor inserido no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {

        // Retorne o id da pessoa jurídica que corresponde ao fornecedor;
        return PessoaJuridicaDAO.create(fornecedor);
    }
    
    //Larissa
    public static List<Fornecedor> readFornecedoresBySupermercado(Supermercado supermercado)throws ClassNotFoundException, SQLException{
        List<Fornecedor> fornecedores = new ArrayList<>();
      
        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT * from juridico " 
                //pessoa com juridica
               /* + "INNER JOIN pessoa on pessoa.id = juridica.id"
                + "INNER JOIN juridica on juridica.id = supermercado.id"
                + " WHERE fk_fornecedor = ?"*/; 

        PreparedStatement st = conexao.prepareStatement (sql);
        st.setInt(1, supermercado.getId());
        
        ResultSet rs = st.executeQuery();
        while (rs.next()) {

            String cnpj = rs.getString("cnpj");
            String nome = rs.getString("nome");
            int id = rs.getInt("id");
            String bairro = rs.getString("bairro");
            String cep = rs.getString("cep");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            int numero = rs.getInt("numero");
            String ruaAvenida = rs.getString("rua");
            Endereco endereco = new Endereco(bairro,cep,cidade,estado,numero, ruaAvenida);

            fornecedores.add(new Fornecedor(cnpj,id,nome,endereco));
        }

        st.close();
        conexao.close();

        return fornecedores;
    }
    
    //Larissa
    public static Fornecedor readFornecedorByLote(Lote lote) throws ClassNotFoundException, SQLException{
        Fornecedor fornecedor = null;
        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();
        // Forme a string sql;
        String sql = "SELECT * from juridico "
                //pessoa com juridica
               /* + "INNER JOIN pessoa on pessoa.id = juridica.id"
                + "INNER JOIN juridica on juridica.id = supermercado.id"
                + "INNER JOIN supermercado supermercado.id = lote.id"
                + " WHERE fk_fornecedor = ?"*/; 
        PreparedStatement st = conexao.prepareStatement (sql);
        st.setInt(1, lote.getId());
 /*       ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String cnpj = rs.getString("cnpj");
            String nome = rs.getString("nome");
            int id = rs.getInt("id");
            String bairro = rs.getString("bairro");
            String cep = rs.getString("cep");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            int numero = rs.getInt("numero");
            String ruaAvenida = rs.getString("rua");
            Endereco endereco = new Endereco(bairro,cep,cidade,estado,numero, ruaAvenida);*/
            fornecedor = readFornecedor(st);
       // }
        st.close();
        conexao.close();
        return fornecedor;
    }
    
    //Larissa
    private static Fornecedor readFornecedor(PreparedStatement st) throws SQLException{
        Fornecedor fornecedor = null;
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String cnpj = rs.getString("cnpj");
            String nome = rs.getString("nome");
            int id = rs.getInt("id");
            String bairro = rs.getString("bairro");
            String cep = rs.getString("cep");
            String cidade = rs.getString("cidade");
            String estado = rs.getString("estado");
            int numero = rs.getInt("numero");
            String ruaAvenida = rs.getString("rua");
            Endereco endereco = new Endereco(bairro,cep,cidade,estado,numero, ruaAvenida);
            fornecedor = new Fornecedor(cnpj,id,nome,endereco);
        }
        return fornecedor;
        
    }
}