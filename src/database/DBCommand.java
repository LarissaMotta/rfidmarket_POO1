package database;

import java.sql.*;

//Classe para agilizar criação de tabelas, seleção e inserção de valores;
public abstract class DBCommand {

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        //ALTERAR de acordo com sua base de dados, usuário e senha no postgresql;
        String nome_base_dados = "EasyMarket";
        String nome_user_postgre = "postgres";
        String senha = "Pianobsiwill1";

        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/" + nome_base_dados;
        Connection conexao;

        Class.forName(driver);
        conexao = DriverManager.getConnection(url, nome_user_postgre, senha);

        return conexao;
    }

    
    //não criaremos tabela dentro da aplicacao
    /*
    public static void createTable(String comando)
            throws SQLException, ClassNotFoundException {

        Connection conexao = null;
        conexao = getConnection();

        try {
            Statement st = null;
            st = conexao.createStatement();
            st.execute(comando);
        } finally {
            if (conexao != null) {
                conexao.close();
            }
        }
    }*/

    // cada classe tem do modelo tem que ter sua proria classe DAO
    // por isso esse funcao não faz sentido
    /*
    public static void insert(String comando)
            throws SQLException, ClassNotFoundException {

        Connection conexao = null;
        conexao = getConnection();

        try {
            Statement st = null;
            st = conexao.createStatement();
            st.executeUpdate(comando);
        } finally {
            if (conexao != null) {
                conexao.close();
            }
        }
    }
    */

    // essa funcão gera problemas no futuro, pois tem q se fechar o statement
    // e isso fecha o resultSet tambem, logo outras funções nao conseguiram usa-lo
    /*
    public static ResultSet select(String comando)
            throws SQLException, ClassNotFoundException {

        ResultSet rs;
        Connection conexao = null;
        conexao = getConnection();

        try {
            Statement st = conexao.createStatement();
            rs = st.executeQuery(comando);
        } finally {
            if (conexao != null) {
                conexao.close();
            }
        }

        return rs;
    }*/
}
