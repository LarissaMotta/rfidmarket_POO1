package modelo.usuarios;

import modelo.usuarios.PessoaFisica;
import modelo.usuarios.Endereco;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import util.Util;

public class Funcionario extends PessoaFisica{
    // nenhum dos atributos pode ser null
    private String cargo; 
    private String setor;

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Funcionario(String cargo, String setor, String cpf, Date dataNasc, Genero genero, String login, String senha, int id, String nome, Endereco endereco) throws IllegalArgumentException, UnsupportedEncodingException, NoSuchAlgorithmException {
        super(cpf, dataNasc, genero, login, senha, id, nome, endereco);
        setCargo(cargo);
        setSetor(setor);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Funcionario(String cargo, String setor, String cpf, Date dataNasc, Genero genero, String login, String senha, String nome, Endereco endereco) throws IllegalArgumentException, UnsupportedEncodingException, NoSuchAlgorithmException {
        super(cpf, dataNasc, genero, login, senha, nome, endereco);
        setCargo(cargo);
        setSetor(setor);
    }
    
    public final void setCargo(String cargo) throws IllegalArgumentException{
        Util.verificaStringNullVazia(cargo);
        
        this.cargo = cargo;
    }

    public final void setSetor(String setor) throws IllegalArgumentException{
        Util.verificaStringNullVazia(setor);
        
        this.setor = setor;
    }
    
    public String getCargo() {
        return cargo;
    }

    public String getSetor() {
        return setor;
    }
    
}
