/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pessoa;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.Util;

public abstract class PessoaFisica extends Pessoa{
    // nenhum atributo pode ser null
    private String cpf;     // deve ter length igual a 14 e ser válido. Ex: 541.578.176-19
    private Date dataNasc;  
    private char genero;    // deve ser M para masculino e F para feminino
    private String login;   // deve seguir o padrao: example@servidor.com
    private String rg;      // deve ter length igual a 7
    private String senha;   // deve ter no length >= 6

    // Pode ser usada quando para instanciar a partir de dados do BD
    public PessoaFisica(String cpf, Date dataNasc, char genero, String login, String rg, String senha, int id, String nome, Endereco endereco) throws IllegalArgumentException{
        super(id, nome, endereco);
        setCpf(cpf);
        setDataNasc(dataNasc);
        setGenero(genero);
        setLogin(login);
        setRg(rg);
        setSenha(senha);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public PessoaFisica(String cpf, Date dataNasc, char genero, String login, String rg, String senha, String nome, Endereco endereco) throws IllegalArgumentException{
        super(nome, endereco);
        setCpf(cpf);
        setDataNasc(dataNasc);
        setGenero(genero);
        setLogin(login);
        setRg(rg);
        setSenha(senha);
    }
    
    public static boolean isLoginValido(String login) throws IllegalArgumentException{ // verifica se o loguin pode ser usado
        if (login == null && login.length() <= 0) return false;
        
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }

    private static boolean validarCpf(String cpf) {

        String digitos;
        int multip = 10;
        int somaDigitos = 0;
        digitos = cpf.replace(".","").replace("-","");

        //Primeira verificação:
        for (int i = 0; i < 9; i++) {
            somaDigitos += multip * Character.getNumericValue(digitos.charAt(i));
            multip--;
        }

        //A soma final deve ser igual ao primeiro digito verificador;
        if (somaDigitos * 10 % 11 != Character.getNumericValue(digitos.charAt(9)))
            return false;

        //Segunda verificação:
        multip = 11;
        somaDigitos = 0;

        for (int i = 0; i < 10; i++) {
            somaDigitos += multip * Character.getNumericValue(digitos.charAt(i));
            multip--;
        }

        //A soma final agora deve ser igual ao segundo digito verificador;
        return somaDigitos * 10 % 11 == Character.getNumericValue(digitos.charAt(10));
    }

    public final void setCpf(String cpf) throws IllegalArgumentException{

        char charCpf;
        int contDigit = 0;

        if (cpf == null || cpf.length() != 14)
            throw new IllegalArgumentException("CPF com tamanho inválido");

        //Garanta que só haverá dígitos, '.' ou '-';
        for (int i = 0; i < cpf.length(); i++) {
            charCpf = cpf.charAt(i);

            if (Character.isDigit(charCpf)) contDigit++;

            else if (charCpf == '.' || charCpf == '-')
                throw new IllegalArgumentException("CPF com caracter inválido '" + charCpf + "'");
        }

        //CPF deve possuir exatamente 11 dígitos;
        if (contDigit != 11)
            throw new IllegalArgumentException("CPF com menos que 11 dígitos");

        else if (!validarCpf(cpf))
            throw new IllegalArgumentException("CPF não é válido");

        else
            this.cpf = cpf;
    }

    public final void setDataNasc(Date dataNasc) throws IllegalArgumentException{
        if (dataNasc == null)
            throw new IllegalArgumentException("Data de nascimento inválido");
        else
            this.dataNasc = dataNasc;
    }

    //TODO Testar essa função
    public final void setGenero(char genero) throws IllegalArgumentException{

        char genUpper = Character.toUpperCase(genero);

        if (genUpper != 'M' && genUpper != 'F') //char pode ser null?
            throw new IllegalArgumentException("Genêro inválido");
        else
            this.genero = genero;
    }

    public final void setLogin(String login) throws IllegalArgumentException{
        if (!isLoginValido(login))
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
        Util.verificaStringNullVazia(senha);
        
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
