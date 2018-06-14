/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuarios;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import util.Util;

public abstract class PessoaFisica extends Pessoa{
    // nenhum atributo pode ser null
    private final String cpf;     // deve ter length igual a 14 e ser válido. Ex: 541.578.176-19
    private final Date dataNasc;  
    private final Genero genero;    // deve ser M para masculino e F para feminino
    private final String login;   // deve seguir o padrao: example@servidor.com
    private String senha;   // deve ter no length >= 6

    public enum Genero {
        M('M'),F('F');

        private final char gen;

        Genero(char gen) {
            this.gen = gen;
        }

        public char toChar() {
            return gen;
        }
    }
    
    // Pode ser usada quando para instanciar a partir de dados do BD
    public PessoaFisica(String cpf, Date dataNasc, Genero genero, String login, String senha, int id, String nome, Endereco endereco) throws IllegalArgumentException{
        super(id, nome, endereco);
        
        if (!Util.isCpfValido(cpf)) throw new IllegalArgumentException("CPF não é válido");
        if (!Util.isLoginValido(login)) throw new IllegalArgumentException("Login inválido");
        Util.verificaIsObjNull(dataNasc, "Data de nascimento");
        
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.genero = genero;
        this.login = login;
        setSenha(senha);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public PessoaFisica(String cpf, Date dataNasc, Genero genero, String login, String senha, String nome, Endereco endereco) throws IllegalArgumentException{
        super(nome, endereco);
        if (!Util.isCpfValido(cpf)) throw new IllegalArgumentException("CPF não é válido");
        if (!Util.isLoginValido(login)) throw new IllegalArgumentException("Login inválido");
        Util.verificaIsObjNull(dataNasc, "Data de nascimento");
        
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.genero = genero;
        this.login = login;
        setSenha(senha);
    }
    
    public final void setSenha(String senha) throws IllegalArgumentException{
        Util.verificaStringNullVazia(senha, "Senha");
        
        if (senha.length() < 6)
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres");
        
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
