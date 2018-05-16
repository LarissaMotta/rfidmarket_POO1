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
public abstract class PessoaFisica extends Pessoa{
    private long cpf;
    private Date dataNasc;
    private char genero;
    private String login;
    private long rg;
    private String senha;

    public PessoaFisica(long cpf, Date dataNasc, char genero, String login, long rg, String senha, int id, String nome, Endereco endereco) {
        super(id, nome, endereco);
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.genero = genero;
        this.login = login;
        this.rg = rg;
        this.senha = senha;
    }

    public long getCpf() {
        return cpf;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public char getGenero() {
        return genero;
    }

    public String getLogin() {
        return login;
    }

    public long getRg() {
        return rg;
    }

    public String getSenha() {
        return senha;
    }
   
    
}
