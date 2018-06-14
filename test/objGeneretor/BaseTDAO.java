package objGeneretor;

import org.postgresql.util.PSQLException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;

//Classe para agilizar criação de tabelas, seleção e inserção de valores;
public abstract class BaseTDAO {

    private static final String sqls_path = Paths.get(System.getProperty("user.dir"),
            "extra_python_popular_base", "sqls").toString();

    private static final String dir_sql_modelo_fisico = Paths.get(sqls_path,
            "modelo_fisico.sql").toString();

    private static final String dir_sql_insert_cliente = Paths.get(sqls_path,
            "insert_cliente.sql").toString();

    private static final String dir_sql_insert_cartao = Paths.get(sqls_path,
            "insert_cartao.sql").toString();

    private static final String dir_sql_insert_fornecedor = Paths.get(sqls_path,
            "insert_fornecedor.sql").toString();

    private static final String dir_sql_insert_funcionario = Paths.get(sqls_path,
            "insert_funcionario.sql").toString();

    private static final String dir_sql_insert_produto = Paths.get(sqls_path,
            "insert_produto_E_lote.sql").toString();

    private static final String dir_sql_insert_supermercado = Paths.get(sqls_path,
            "insert_supermercado.sql").toString();

    private static final String dir_sql_insert_all = Paths.get(sqls_path,
            "insert_COMPLETO.sql").toString();

    private static String get_sql_from_file(String path_file)
            throws IOException {

        Connection con = null;
        FileReader fr;
        BufferedReader br;

        try {
            fr = new FileReader(path_file);
            br = new BufferedReader(fr);
            StringBuilder sql_builder = new StringBuilder();
            String linha;

            while ((linha = br.readLine()) != null) {
                sql_builder.append(linha).append('\n');
            }

            return sql_builder.toString();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *Abre uma conexão com a base dados e a retorna;
     *@return Conexão aberta com a base de dados
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        // ALTERAR de acordo com sua base de dados, usuário e senha no postgresql;

        // Nome da sua base de dados no postgres;
        String nome_base_dados = "TestBase";

        // Nome do usuário de sua base;
        String nome_user_postgre = "postgres";

        // Senha para acessar sua base de dados;
        String senha = "antonio";

        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/" + nome_base_dados;
        Connection conexao;

        Class.forName(driver);
        conexao = DriverManager.getConnection(url, nome_user_postgre, senha);

        return conexao;
    }

    private static void execute(String sql)
            throws SQLException, ClassNotFoundException {

        Connection con = getConnection();
        Statement st = con.createStatement();
        st.execute(sql);
        st.close();
        con.close();
    }


    //CREATE, DROP e INSERT ALL (Cria, Insere OU Remove TODAS tabelas da base)

    public static void create()
            throws SQLException, ClassNotFoundException, IOException {
        String sql = get_sql_from_file(BaseTDAO.dir_sql_modelo_fisico);
        assert sql != null;
        execute(sql.substring(1, sql.length()));
    }

    public static void drop_all()
            throws SQLException, ClassNotFoundException {

        Connection con = getConnection();
        Statement st = con.createStatement();
        String sql = "DROP TABLE IF EXISTS ";
        DatabaseMetaData md = con.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);

        while (rs.next()) {

            try {
                st.execute(sql + rs.getString(3) + " CASCADE;");
            }

            catch (PSQLException ex) {}
        }

        st.close();
        con.close();
    }

    public static void insert_all()
            throws SQLException, ClassNotFoundException, IOException {
        execute(get_sql_from_file(BaseTDAO.dir_sql_insert_all));
    }


    //INSERTS (Preenche as tabelas que envolvem uma determinada classe, cliente, p.e.)

    public static void insert_clientes()
            throws SQLException, ClassNotFoundException, IOException {
        execute(get_sql_from_file(BaseTDAO.dir_sql_insert_cliente));
    }

    public static void insert_cartoes()
            throws SQLException, ClassNotFoundException, IOException {
        execute(get_sql_from_file(BaseTDAO.dir_sql_insert_cartao));
    }

    public static void insert_fornecedores()
            throws SQLException, ClassNotFoundException, IOException {
        execute(get_sql_from_file(BaseTDAO.dir_sql_insert_fornecedor));
    }

    public static void insert_funcionarios()
            throws SQLException, ClassNotFoundException, IOException {
        execute(get_sql_from_file(BaseTDAO.dir_sql_insert_funcionario));
    }

    public static void insert_produtos()
            throws SQLException, ClassNotFoundException, IOException {
        execute(get_sql_from_file(BaseTDAO.dir_sql_insert_produto));
    }

    public static void insert_supermercados()
            throws SQLException, ClassNotFoundException, IOException {
        execute(get_sql_from_file(BaseTDAO.dir_sql_insert_supermercado));
    }
}
