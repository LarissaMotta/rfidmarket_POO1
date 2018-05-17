/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import util.Util;

public class Funcionario extends PessoaFisica{
    // nenhum dos atributos pode ser null
    private String cargo; 
    private String setor;

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Funcionario(String cargo, String setor, String cpf, Date dataNasc, char genero, String login, String rg, String senha, int id, String nome, Endereco endereco) throws IllegalArgumentException{
        super(cpf, dataNasc, genero, login, rg, senha, id, nome, endereco);
        setCargo(cargo);
        setSetor(setor);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Funcionario(String cargo, String setor, String cpf, Date dataNasc, char genero, String login, String rg, String senha, String nome, Endereco endereco) throws IllegalArgumentException{
        super(cpf, dataNasc, genero, login, rg, senha, nome, endereco);
        setCargo(cargo);
        setSetor(setor);
    }
    
    // Retorna o supermercado em que o funcionario trabalha
    public Supermercado getSupermecado() {
        //TODO criar função na classe SupermercadoDAO para carregar no BD
    }
    
    public final void setCargo(String cargo) throws IllegalArgumentException{
        Util.verificaStringNullVazia(cargo);
        
        this.cargo = cargo;
    }

    public final void setSetor(String setor) throws IllegalArgumentException{
        Util.verificaStringNullVazia(setor);
        
        this.setor = setor;
    }
    
    public String getCargo() {
        return cargo;
    }

    public String getSetor() {
        return setor;
    }
    
}
