package modelo.pessoa;

import database.ContatoDAO;
import java.sql.SQLException;
import java.util.List;
import util.Util;

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
        Util.verificaID(id);
        Util.verificaStringNullVazia(nome);
        
        this.id = id;
        this.nome = nome;
        setEndereco(endereco);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Pessoa(String nome, Endereco endereco) throws IllegalArgumentException{
        Util.verificaStringNullVazia(nome);

        this.nome = nome;
        setEndereco(endereco);
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

    public List<Contato> getContato() throws SQLException, ClassNotFoundException {
        return ContatoDAO.readContatosByPessoa(this);
    }

    public final void setEndereco(Endereco endereco) throws IllegalArgumentException{
        Util.verificaIsObjNull(endereco);
        
        this.endereco = endereco;
    }
    
}
