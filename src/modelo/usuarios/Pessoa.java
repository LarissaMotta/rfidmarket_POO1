package modelo.usuarios;

import util.Util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public abstract class Pessoa {
    private int id; //não pode ser <= 0
    
    // nenhum dos atributos pode ser null
    private String nome;
    private Endereco endereco;

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Pessoa(int id, String nome, Endereco endereco) throws IllegalArgumentException{
        Util.verificaID(id);
        
        this.id = id;
        setNome(nome);
        setEndereco(endereco);
    }

    

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Pessoa(String nome, Endereco endereco) throws IllegalArgumentException{

        setNome(nome);
        setEndereco(endereco);
    }
   
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public final void setNome(String nome) {
        Util.verificaStringNullVazia(nome, "Nome");
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public final void setEndereco(Endereco endereco) throws IllegalArgumentException{
        Util.verificaIsObjNull(endereco, "Endereço");
        
        this.endereco = endereco;
    }
    
}
