/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

public class Funcionario extends PessoaFisica{
    private String cargo;
    private String setor;

    public Funcionario(String cargo, String setor, String cpf, Date dataNasc, char genero, String login, String rg, String senha, int id, String nome, Endereco endereco) throws IllegalArgumentException{
        super(cpf, dataNasc, genero, login, rg, senha, id, nome, endereco);
        setCargo(cargo);
        setSetor(setor);
    }

    public final void setCargo(String cargo) throws IllegalArgumentException{
        if (cargo == null)
            throw new IllegalArgumentException("Cargo nulo!");
        else
            this.cargo = cargo;
    }

    public final void setSetor(String setor) throws IllegalArgumentException{
        if (setor == null)
            throw new IllegalArgumentException("Setor nulo!");
        else
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
