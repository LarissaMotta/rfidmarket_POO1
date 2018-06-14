package objGeneretor;

import modelo.usuarios.Cliente;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SubTeste {

    public static void main(String[] args)
            throws SQLException, IOException, ClassNotFoundException, NoSuchAlgorithmException {
//        BaseTDAO.drop_all();
//        BaseTDAO.create();
//        BaseTDAO.insert_all();
        Cliente c = ClienteTDAO.readCliente();
        System.out.printf("%s, %s, %s, %s, %d, %s", c.getCpf(), c.getNome(),
                c.getGenero(), c.getLogin(), c.getId(), c.getEndereco().getCep());
    }
}
