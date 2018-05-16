/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import java.util.List;

public class Cliente extends PessoaFisica{
    
    public Cliente(long cpf, Date dataNasc, char genero, String login, long rg, String senha, int id, String nome, Endereco endereco) {
        super(cpf, dataNasc, genero, login, rg, senha, id, nome, endereco);
    }
    
    public List<Compra> getHistoricosCompra(){
        return ;
    }
}
