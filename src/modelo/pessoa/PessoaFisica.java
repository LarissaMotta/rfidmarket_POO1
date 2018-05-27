/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pessoa;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import util.Util;

public abstract class PessoaFisica extends Pessoa{
    // nenhum atributo pode ser null
    private String cpf;     // deve ter length igual a 14 e ser válido. Ex: 541.578.176-19
    private Date dataNasc;  
    private char genero;    // deve ser M para masculino e F para feminino
    private String login;   // deve seguir o padrao: example@servidor.com
    private String senha;   // deve ter no length >= 6

    // Pode ser usada quando para instanciar a partir de dados do BD
    public PessoaFisica(String cpf, Date dataNasc, char genero, String login, String senha, int id, String nome, Endereco endereco) throws IllegalArgumentException, NoSuchAlgorithmException, UnsupportedEncodingException{
        super(id, nome, endereco);
        setCpf(cpf);
        setDataNasc(dataNasc);
        setGenero(genero);
        setLogin(login);
        setSenha(senha);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public PessoaFisica(String cpf, Date dataNasc, char genero, String login, String senha, String nome, Endereco endereco) throws IllegalArgumentException, NoSuchAlgorithmException, UnsupportedEncodingException{
        super(nome, endereco);
        setCpf(cpf);
        setDataNasc(dataNasc);
        setGenero(genero);
        setLogin(login);
        setSenha(senha);
    }
    
    public final void setCpf(String cpf) throws IllegalArgumentException{

        if (!Util.isCpfValido(cpf))
            throw new IllegalArgumentException("CPF não é válido");
        
        this.cpf = cpf;
    }

    public final void setDataNasc(Date dataNasc) throws IllegalArgumentException{
        if (dataNasc == null)
            throw new IllegalArgumentException("Data de nascimento inválido");
        else
            this.dataNasc = dataNasc;
    }

    public final void setGenero(char genero) throws IllegalArgumentException{

        genero = Character.toUpperCase(genero);

        if (genero != 'M' && genero != 'F') //char pode ser null?
            throw new IllegalArgumentException("Genêro inválido");
        else
            this.genero = genero;
    }

    public final void setLogin(String login) throws IllegalArgumentException{
        if (!Util.isLoginValido(login))
            throw new IllegalArgumentException("Login inválido");
        else
            this.login = login;
    }

    public final void setSenha(String senha) throws IllegalArgumentException, NoSuchAlgorithmException, UnsupportedEncodingException{
        Util.verificaStringNullVazia(senha);
        
        if (senha.length() < 6)
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres");
        
        this.senha = Util.criptografar(senha);
    }

    public String getCpf() {
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

    public String getSenha() {
        return senha;
    }
}
