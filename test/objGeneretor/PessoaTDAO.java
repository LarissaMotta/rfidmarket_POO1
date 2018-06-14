package objGeneretor;

import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.usuarios.Endereco;
import modelo.usuarios.Endereco.Estado;

public abstract class PessoaTDAO extends BaseTDAO {

    public static Endereco readEndereco(ResultSet rs)
            throws SQLException{

        String cep = rs.getString("cep");
        int numero = rs.getInt("numero");
        String ruaAvenida = rs.getString("rua");
        String bairro = rs.getString("bairro");
        String cidade = rs.getString("cidade");
        String estado_bd = rs.getString("estado");
        Estado estado = null;

        for (Estado e : Estado.values()){

            if (e.toString().equals(estado_bd)){
                estado = e;
                break;
            }
        }

        return new Endereco(bairro, cep, cidade, estado, numero, ruaAvenida);
    }

}

