/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import util.Util;

public class Contato {
    private int id;             //não pode ser <= 0
    private String descricao;   //não pode ser null
    private String tipo;        //não pode ser null

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Contato(int id, String descricao, String tipo) throws IllegalArgumentException{
        Util.verificaID(id);
        
        this.id = id;
        setDescricao(descricao);
        setTipo(tipo);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Contato(String descricao, String tipo) throws IllegalArgumentException{
        setDescricao(descricao);
        setTipo(tipo);
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public final void setDescricao(String descricao) throws IllegalArgumentException{
        Util.verificaStringNullVazia(descricao);
        
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public final void setTipo(String tipo) throws IllegalArgumentException{
        Util.verificaStringNullVazia(tipo);
        
        this.tipo = tipo;
    }
}
