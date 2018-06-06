/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.supermercado.mercadoria;

import modelo.usuarios.PessoaJuridica;
import modelo.usuarios.Endereco;

/**
 *
 * @author joel-
 */
public class Fornecedor extends PessoaJuridica{

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Fornecedor(String cnpj, int id, String nome, Endereco endereco) throws IllegalArgumentException {
        super(cnpj, id, nome, endereco);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Fornecedor(String cnpj, String nome, Endereco endereco) throws IllegalArgumentException {
        super(cnpj, nome, endereco);
    }
}
