/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import util.Util;

/**
 *
 * @author joel-
 */
public abstract class PessoaJuridica extends Pessoa{
    // não pode ser nulo, deve ter length igual a 18 e não pode ser alterado
    private final String cnpj;

    // Pode ser usada quando para instanciar a partir de dados do BD
    public PessoaJuridica(String cnpj, int id, String nome, Endereco endereco) throws IllegalArgumentException {
        super(id, nome, endereco);
        
        Util.verificaStringNullVazia(cnpj);
        this.cnpj = cnpj;
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public PessoaJuridica(String cnpj, String nome, Endereco endereco) throws IllegalArgumentException{
        super(nome, endereco);
        
        Util.verificaStringNullVazia(cnpj);
        this.cnpj = cnpj;
    }
    
    public String getCnpj() {
        return cnpj;
    }
}
