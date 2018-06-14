/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuarios;

import util.Util;

public class Contato {
    private int id;             //não pode ser <= 0
    private String descricao;   //não pode ser null
    private Tipo tipo;        //não pode ser null

    public enum Tipo {
        EMAIL("Email"),CELULAR("Celular"),TELEFONE("Telefone");

        private final String type;

        Tipo(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }
    
    // Pode ser usada quando para instanciar a partir de dados do BD
    public Contato(int id, String descricao, Tipo tipo) throws IllegalArgumentException{
        Util.verificaID(id);
        
        this.id = id;
        setDescricao(descricao);
        setTipo(tipo);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Contato(String descricao, Tipo tipo) throws IllegalArgumentException{
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
        Util.verificaStringNullVazia(descricao, "Contato");
        
        this.descricao = descricao;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public final void setTipo(Tipo tipo) throws IllegalArgumentException{
        Util.verificaIsObjNull(tipo,"Tipo do contato");
        
        this.tipo = tipo;
    }
     
}
