package modelo;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public abstract class Pessoa {
    private final int id;
    private final String nome;
    private Endereco endereco;

    public Pessoa(int id, String nome, Endereco endereco) throws IllegalArgumentException{
        if (id <= 0 || nome == null || endereco == null) 
            throw new IllegalArgumentException("Algum argumento está errado!");
        
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Contato> getContato() {
        return ;
    }

    public void setEndereco(Endereco endereco) throws IllegalArgumentException{
        if (endereco == null)
            throw new IllegalArgumentException("Endereço nulo!");
        else
            this.endereco = endereco;
    }
    
}
