/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author 20162bsi0147
 */
public class Funcionario extends PessoaFisica{
    private String cargo;
    private int permissoes;
    private String setor;

    public Funcionario(String cargo, int permissoes, String setor, long cpf, Date dataNasc, char genero, String login, long rg, String senha, int id, String nome, Endereco endereco) {
        super(cpf, dataNasc, genero, login, rg, senha, id, nome, endereco);
        this.cargo = cargo;
        this.permissoes = permissoes;
        this.setor = setor;
    }

    public String getCargo() {
        return cargo;
    }

    public int getPermissoes() {
        return permissoes;
    }

    public String getSetor() {
        return setor;
    }
    
}
