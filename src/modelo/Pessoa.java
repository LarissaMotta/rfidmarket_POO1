package modelo;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public abstract class Pessoa {
    private int id; //não pode ser <= 0
    
    // nenhum dos atributos pode ser null
    private final String nome;
    private Endereco endereco;

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Pessoa(int id, String nome, Endereco endereco) throws IllegalArgumentException{
        if (id <= 0) 
            throw new IllegalArgumentException("ID inválido: menor que 1!");
        if (isNomeInvalido(nome))
            throw new IllegalArgumentException("Nome inválido!");
        
        this.id = id;
        this.nome = nome;
        setEndereco(endereco);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Pessoa(String nome, Endereco endereco) throws IllegalArgumentException{
        if (isNomeInvalido(nome))
            throw new IllegalArgumentException("Nome inválido!");

        this.nome = nome;
        this.endereco = endereco;
    }
    
    private final boolean isNomeInvalido(String nome){
        return nome == null || nome.length() == 0;
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
        //TODO criar função na classe ContatoDAO para carregar no BD
    }

    public final void setEndereco(Endereco endereco) throws IllegalArgumentException{
        if (endereco == null)
            throw new IllegalArgumentException("Endereço nulo!");
        else
            this.endereco = endereco;
    }
    
}
