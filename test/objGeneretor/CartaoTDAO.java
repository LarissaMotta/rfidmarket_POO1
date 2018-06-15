package objGeneretor;

import modelo.pagamento.Cartao;
import modelo.pagamento.Cartao.Tipo;

import java.sql.*;
import java.util.Random;

public class CartaoTDAO extends BaseTDAO {

    public static Cartao readCartao()
            throws SQLException, ClassNotFoundException {

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();
        int rand_int = new Random().nextInt(1000);

        // Forme a string sql;
        String sql = "SELECT c.id, c.nome_titular, c.validade, c.bandeira, c.numero,"
                + "c.tipo FROM cartao AS c WHERE c.id = ?";

        PreparedStatement ps = conexao.prepareStatement (sql);
        ps.setInt(1, rand_int);
        ResultSet rs = ps.executeQuery();
        rs.next();

        int id = rs.getInt("id");
        String bandeira = rs.getString("bandeira");
        Date dataValid = rs.getDate("validade");
        String numero = rs.getString("numero");
        String titular = rs.getString("nome_titular");
        char tipo_bd = rs.getString("tipo").charAt(0);

        Tipo tipo = (tipo_bd == 'C' || tipo_bd == 'c') ? Tipo.CREDITO : Tipo.DEBITO;

        ps.close();
        conexao.close();

        return new Cartao(id, bandeira, dataValid, numero, titular, tipo);
    }
}
