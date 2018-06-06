package modelo.usuarios;

import util.Util;

/**
 *
 * @author joel-
 */
public abstract class PessoaJuridica extends Pessoa{

    // não pode ser nulo, deve ter length igual a 18 e não pode ser alterado
    private String cnpj;

    // Pode ser usada quando para instanciar a partir de dados do BD
    public PessoaJuridica(String cnpj, int id, String nome, Endereco endereco) throws IllegalArgumentException {
        super(id, nome, endereco);
        setCnpj(cnpj);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public PessoaJuridica(String cnpj, String nome, Endereco endereco) throws IllegalArgumentException{
        super(nome, endereco);
        setCnpj(cnpj);
    }

    public final void setCnpj(String cnpj) throws IllegalArgumentException{

        if (!Util.isCnpjValido(cnpj))
            throw new IllegalArgumentException("CNPJ não é válido");

        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }
}
