package database.usuarios;

import database.core.CoreDAO;
import static database.core.CoreDAO.getConnection;
import database.filter.Clause;
import database.filter.Filter;
import modelo.supermercado.Supermercado;
import modelo.usuarios.Endereco;
import modelo.usuarios.Funcionario;
import modelo.usuarios.PessoaFisica.Genero;
import org.postgresql.util.PSQLException;

import javax.security.auth.login.LoginException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.Util;

/**
 * Created by 20162bsi0511 on 21/05/2018.
 */
public abstract class FuncionarioDAO extends CoreDAO {

    /**
     * Insere um funcionário na base de dados;
     *
     * @param funcionario funcionário a ser gravado na base de dados;
     * @param supermercado supermercado em que o funcionário trabalha;
     * @return Inteiro que representa o ID do funcionário inserido no BD;
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static int create(Funcionario funcionario, Supermercado supermercado) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
        int id = PessoaFisicaDAO.create(funcionario); // insere primeiro os dados da usuarios

        Connection conn = getConnection();

        String sql = "INSERT INTO funcionario (setor,cargo,fk_pessoa_fisica,fk_supermercado) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, funcionario.getSetor());
        st.setString(2, funcionario.getCargo());
        st.setInt(3, id);
        st.setInt(4, supermercado.getId());

        try {
            st.executeUpdate();
        } catch (PSQLException ex) {
            // se ocorrer algum erro durante a inseção do restante dos dados
            // apaga o que ja foi inserido e lança a exceção
            PessoaFisicaDAO.delete(id);
            throw ex;
        }

        st.close();
        conn.close();

        return id;
    }

    /**
     * Dado um resultSet com os dados do funcionário, constrói um objeto
     * funcionário já preenchido;
     *
     * @param rs ResultSet com todos dados do funcionário;
     * @return Funcionário criado a partir do result set
     * @throws SQLException
     */
    private static Funcionario readFuncionario(ResultSet rs)
            throws SQLException {
        String cargo = rs.getString("cargo");
        String setor = rs.getString("setor");
        int id = rs.getInt("fk_pessoa_fisica");

        String login = rs.getString("login");
        Date dataNasc = rs.getDate("data_nasc");
        String cpf = rs.getString("cpf");
        String senha = rs.getString("senha");
        String genStr = rs.getString("genero");
        Genero genero = null;

        if ("m".equalsIgnoreCase(genStr)) {
            genero = Genero.M;
        } else if ("f".equalsIgnoreCase(genStr)) {
            genero = Genero.F;
        }

        String nome = rs.getString("nome");
        Endereco endereco = PessoaDAO.getEndereco(rs);

        return new Funcionario(cargo, setor, cpf, dataNasc, genero, login, senha,
                id, nome, endereco);
    }

    //Jennifer
    public static List<Funcionario> readFuncionariosBySupermercado(Supermercado supermercado, String nome, String cpf,
            Genero genero, String setor, String cargo) throws SQLException, ClassNotFoundException, IllegalArgumentException {

        List<Funcionario> funcionarios = new ArrayList<>();

        if (cpf != null && !Util.isCpfValido(cpf)) {
            throw new IllegalArgumentException("CPF inválido!");
        }

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        Filter filter = new Filter();

        Clause clause = new Clause("pessoa.nome", nome + "%", Clause.ILIKE);
        filter.addClause(clause);

        clause = new Clause("fisica.cpf", cpf, Clause.IGUAL);
        filter.addClause(clause);

        if (genero != null) {
            clause = new Clause("fisica.genero", genero.toChar(), Clause.IGUAL);
            filter.addClause(clause);
        }

        clause = new Clause("funcionario.setor", setor + "%", Clause.ILIKE);
        filter.addClause(clause);

        clause = new Clause("funcionario.cargo", cargo + "%", Clause.ILIKE);
        filter.addClause(clause);

        // Forme a string sql;
        String sql = "SELECT * FROM funcionario "
                + "INNER JOIN fisica on fisica.fk_pessoa = funcionario.fk_pessoa_fisica "
                + "INNER JOIN pessoa on pessoa.id = funcionario.fk_pessoa_fisica "
                + "WHERE fk_supermercado = ? " + filter.getFilter()
                + " ORDER BY pessoa.nome";

        PreparedStatement st = conexao.prepareStatement(sql);
        st.setInt(1, supermercado.getId());

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            funcionarios.add(FuncionarioDAO.readFuncionario(rs));
        }

        st.close();
        conexao.close();

        return funcionarios;

    }
    
    public static void update(Funcionario f) throws ClassNotFoundException, SQLException{
        Connection conn = getConnection();
        String sql = "UPDATE funcionario "
                + "SET setor = ?, "
                + "cargo = ? "
                + "WHERE fk_pessoa_fisica = ?";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, f.getSetor());
        st.setString(2, f.getCargo());
        st.setInt(3, f.getId());
        
        st.executeUpdate();
        st.close();
        conn.close();
        
        PessoaFisicaDAO.update(f);
    }

    public static Funcionario SignIn(String login, String senha)
            throws SQLException, ClassNotFoundException, LoginException,
            UnsupportedEncodingException, NoSuchAlgorithmException, IllegalArgumentException {

        if (!Util.isLoginValido(login)) throw new IllegalArgumentException("Email inválido");
        if (senha.length() < 6) throw new IllegalArgumentException("Senha com menos de 6 caracteres");
        
        //Conecte-se ao banco de dados;
        Connection conexao = getConnection();

        //A seleção será filtrada pelo login da pessoa física;
        //Login e senha recebidos devem ser IGUAIS aos de uma PF que seja funcionária;
        Filter filter = new Filter();
        
        String senha_sha512 = Util.criptografar(senha);
        Clause clause = new Clause("pf.senha", senha_sha512, Clause.IGUAL);
        filter.addClause(clause);

        String sql = "SELECT func.cargo, func.setor, func.fk_pessoa_fisica, pf.login,"
                + "pf.data_nasc, pf.cpf, pf.senha, pf.genero, p.nome, p.numero,"
                + "p.rua, p.cep, p.bairro, p.cidade, p.estado "
                + "from funcionario as func INNER JOIN fisica as pf "
                + "ON func.fk_pessoa_fisica = pf.fk_pessoa INNER JOIN pessoa as p "
                + "ON pf.fk_pessoa = p.id WHERE pf.login = ? " + filter.getFilter();

        // Substitua a '?' pelo valor da coluna;
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, login);

        ResultSet rs = ps.executeQuery();

        //Se não encontrou alguém com esse login e senha, lance uma exceção de login;
        if (!rs.next()) {
            throw new LoginException("Falha ao logar, login ou senha inválidos");
        }

        Funcionario funcionario = readFuncionario(rs);

        ps.close();
        conexao.close();

        return funcionario;
    }

    public static void delete(int id) throws SQLException, ClassNotFoundException {

        Connection conn = getConnection();
        String sql = "DELETE FROM funcionario WHERE fk_pessoa_fisica = ?";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);

        st.executeUpdate();
        st.close();
        conn.close();

        PessoaFisicaDAO.delete(id);
    }
}
