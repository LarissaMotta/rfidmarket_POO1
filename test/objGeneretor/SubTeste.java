package objGeneretor;

import modelo.usuarios.Cliente;
import modelo.usuarios.Funcionario;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SubTeste {

    public static void main(String[] args)
            throws SQLException, IOException, ClassNotFoundException, NoSuchAlgorithmException {
//        BaseTDAO.drop_all();
//        BaseTDAO.create();
//        BaseTDAO.insert_all();
        Funcionario x = FuncionarioTDAO.readFuncionario();
        System.out.printf("%d, %s, %s, %s, %s, %s\n%s, %s, %s, %s, %s",
                x.getId(), x.getCargo(), x.getSetor(), x.getCpf(), x.getDataNasc(),
                x.getGenero(), x.getLogin(), x.getNome(), x.getSenha(),
                x.getEndereco().getCep(), x.getEndereco().getBairro());
    }
}
