package database;

import java.sql.*;

//Classe para agilizar criação de tabelas, seleção e inserção de valores;
public abstract class DBCommand {

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        //ALTERAR de acordo com sua base de dados, usuário e senha no postgresql;
        String nome_base_dados = "EasyMarket";
        String nome_user_postgre = "postgres";
        String senha = "";

        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/" + nome_base_dados;
        Connection conexao;

        Class.forName(driver);
        conexao = DriverManager.getConnection(url, nome_user_postgre, senha);

        return conexao;
    }

    public static int getIdAtCreate(PreparedStatement st) throws SQLException {
        ResultSet rs = st.getGeneratedKeys();
        rs.next();
        return rs.getInt("id");
    }
}
