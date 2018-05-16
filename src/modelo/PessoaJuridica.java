/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author joel-
 */
public class PessoaJuridica extends Pessoa{
    // não pode ser nulo, deve ter length igual a 18 e não pode ser alterado
    private final String cnpj;

    public PessoaJuridica(String cnpj, int id, String nome, Endereco endereco) throws IllegalArgumentException {
        super(id, nome, endereco);
        
        if (isCnpjInvalido(cnpj))
            throw new IllegalArgumentException("CNPJ inválido");
        else
            this.cnpj = cnpj;
    }

    public PessoaJuridica(String cnpj, String nome, Endereco endereco) throws IllegalArgumentException{
        super(nome, endereco);
        
        if (isCnpjInvalido(cnpj))
            throw new IllegalArgumentException("CNPJ inválido");
        else
            this.cnpj = cnpj;
    }
    
    private final boolean isCnpjInvalido(String cnpj){
        return cnpj == null || cnpj.length() != 18;
    }

    public String getCnpj() {
        return cnpj;
    }
}
