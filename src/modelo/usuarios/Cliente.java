/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuarios;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Cliente extends PessoaFisica {

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Cliente(String cpf, Date dataNasc, Genero genero, String login, String senha, int id, String nome, Endereco endereco) throws IllegalArgumentException {
        super(cpf, dataNasc, genero, login, senha, id, nome, endereco);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Cliente(String cpf, Date dataNasc, Genero genero, String login,String senha, String nome, Endereco endereco) throws IllegalArgumentException {
        super(cpf, dataNasc, genero, login, senha, nome, endereco);
    }
}
