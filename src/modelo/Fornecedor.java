/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

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
    
    // Retorna uma lista com todos os lotes que foram fornecidos pelo fornecedor
    public List<Lote> getLotes() {
        //TODO criar função na classe LoteDAO para carregar no BD
    }
    
    // Retorna uma lista com todos supermercados que dependem do fornecedor
    public List<Supermercado> getSupermercados() {
        //TODO criar função na classe SupermercadoDAO para carregar no BD
    }
}
