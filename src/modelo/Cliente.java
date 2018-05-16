/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import java.util.List;

public class Cliente extends PessoaFisica {

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Cliente(String cpf, Date dataNasc, char genero, String login, String rg, String senha, int id, String nome, Endereco endereco) throws IllegalArgumentException{
        super(cpf, dataNasc, genero, login, rg, senha, id, nome, endereco);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Cliente(String cpf, Date dataNasc, char genero, String login, String rg, String senha, String nome, Endereco endereco) throws IllegalArgumentException{
        super(cpf, dataNasc, genero, login, rg, senha, nome, endereco);
    }

    // Retorna a lista com todas as compras feitas do cliente
    public List<Compra> getHistoricoCompra() {
        //TODO criar função na classe CompraDAO para carregar no BD
    }

    // Retorna a lista com as compras feitas do cliente com um determinado cartao
    public List<Compra> getHistoricoCompraByCartao(Cartao cartao) {
        //TODO criar função na classe CompraDAO para carregar no BD
    }

    // Retorna todos os cartoes do cliente
    public List<Cartao> getCartoes() {
        //TODO criar função na classe CartaoDAO para carregar no BD
    }
}
