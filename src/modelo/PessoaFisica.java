/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PessoaFisica extends Pessoa{
    private String cpf;
    private Date dataNasc;
    private char genero;
    private String login;
    private String rg;
    private String senha;

    public PessoaFisica(String cpf, Date dataNasc, char genero, String login, String rg, String senha, int id, String nome, Endereco endereco) throws IllegalArgumentException{
        super(id, nome, endereco);
        
        setCpf(cpf);
        setDataNasc(dataNasc);
        setGenero(genero);
        setLogin(login);
        setRg(rg);
        setSenha(senha);
    }
    
    public static boolean validaLogin(String login) throws IllegalArgumentException{ // verifica se o loguin pode ser usado
        if (login == null && login.length() <= 0) return false;
        
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public final void setCpf(String cpf) throws IllegalArgumentException{
        if (cpf == null || cpf.length() != 14)
            throw new IllegalArgumentException("CPF inválido");
        else
            this.cpf = cpf;
    }

    public final void setDataNasc(Date dataNasc) throws IllegalArgumentException{
        if (dataNasc == null)
            throw new IllegalArgumentException("Data de nascimento inválido");
        else
            this.dataNasc = dataNasc;
    }

    public final void setGenero(char genero) throws IllegalArgumentException{
        if (genero != 'M' && genero != 'F')
            throw new IllegalArgumentException("Genêro inválido");
        else
            this.genero = genero;
    }

    public final void setLogin(String login) throws IllegalArgumentException{
        if (!validaLogin(login))
            throw new IllegalArgumentException("Login inválido");
        else
            this.login = login;
    }

    public final void setRg(String rg) throws IllegalArgumentException{
        if (rg == null || rg.length() != 7)
            throw new IllegalArgumentException("RG inválido");
        else
            this.rg = rg;
    }

    public final void setSenha(String senha) throws IllegalArgumentException{
        if (senha == null)
            throw new IllegalArgumentException("Senha nula");
        else if (senha.length() < 6)
            throw new IllegalArgumentException("Senha deve ter pelo menos 3 caracteres");
        this.senha = senha;
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

    public String getRg() {
        return rg;
    }

    public String getSenha() {
        return senha;
    }
}
