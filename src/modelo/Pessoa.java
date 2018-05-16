package modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 20162bsi0147
 */
public abstract class Pessoa {
    private final int id;
    private final String nome;
    private Endereco endereco;

    public Pessoa(int id, String nome, Endereco endereco) {
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

    public Contato getContato() {
        return ;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
