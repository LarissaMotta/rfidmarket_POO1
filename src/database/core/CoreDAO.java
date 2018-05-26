package database.core;

import java.sql.*;

//Classe para agilizar criação de tabelas, seleção e inserção de valores;
public abstract class CoreDAO {

    /**
     *Abre uma conexão com a base dados e a retorna;
     *@return Conexão aberta com a base de dados
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        // ALTERAR de acordo com sua base de dados, usuário e senha no postgresql;

        // Nome da sua base de dados no postgres;
        String nome_base_dados = "EasyMarket";

        // Nome do usuário de sua base;
        String nome_user_postgre = "postgres";

        // Senha para acessar sua base de dados;
        String senha = "";

        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/" + nome_base_dados;
        Connection conexao;

        Class.forName(driver);
        conexao = DriverManager.getConnection(url, nome_user_postgre, senha);

        return conexao;
    }

    /**
     *Dado um PreparedStatement já executado, retorna o ID do item gravado na tabela;
     *@return Inteiro que representa o ID do item inserido em uma tabela;
     *@param st PreparedStatement já executado;
     * @throws java.sql.SQLException
     */
    public static int getIdAtCreate(PreparedStatement st) throws SQLException {
        ResultSet rs = st.getGeneratedKeys();
        rs.next();

        return rs.getInt("id");
    }
}
