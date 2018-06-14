package objGeneretor;

import modelo.usuarios.Cliente;
import modelo.usuarios.Endereco;
import modelo.usuarios.Funcionario;
import modelo.usuarios.PessoaFisica;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

public class FuncionarioTDAO extends BaseTDAO {

    public static Funcionario readFuncionario()
            throws SQLException, ClassNotFoundException, UnsupportedEncodingException,
            NoSuchAlgorithmException {

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();
        int rand_int = new Random().nextInt(101) + 500;

        if (rand_int == 500) rand_int++;

        // Forme a string sql;
        String sql = "SELECT p.nome, p.numero, p.rua, p.cep, p.bairro,"
                + "p.estado, p.cidade, pf.data_nasc, pf.genero, pf.login, pf.senha,"
                + "cpf, f.fk_pessoa, f.cargo, f.setor FROM funcionario AS f "
                + "INNER JOIN fisica as pf ON f.fk_pessoa = pf.fk_pessoa "
                + "INNER JOIN pessoa as p ON pf.fk_pessoa = p.id "
                + "WHERE f.fk_pessoa = ? ";

        // Substitua a '?' pelo valor da coluna;
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, rand_int);

        ResultSet rs = ps.executeQuery();
        rs.next();

        //Dados pessoais
        String cpf = rs.getString("cpf");
        Date dtNasc = new Date(rs.getDate("data_nasc").getTime());
        char gen = rs.getString("genero").charAt(0);
        String login = rs.getString("login");
        String senha = rs.getString("senha");
        int id = rs.getInt("fk_pessoa");
        String nome = rs.getString("nome");
        PessoaFisica.Genero genero;
        genero = (gen == 'M' || gen == 'm') ? PessoaFisica.Genero.M : PessoaFisica.Genero.F;

        //Endereço
        Endereco endereco = PessoaTDAO.readEndereco(rs);

        //Dados exclusivos do funcionário
        String cargo = rs.getString("cargo");
        String setor= rs.getString("setor");

        ps.close();
        conexao.close();

        return new Funcionario(cargo, setor, cpf, dtNasc, genero, login, senha, id,
                nome, endereco);
    }
}
