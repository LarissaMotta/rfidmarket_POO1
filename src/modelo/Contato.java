/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class Contato {
    private final int id;
    private String descricao;
    private String tipo;

    public Contato(int id, String descricao, String tipo) throws IllegalArgumentException{
        if (id <= 0) throw new IllegalArgumentException("ID inválido: menor que 1!");
        this.id = id;
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
        if (descricao == null)
            throw new IllegalArgumentException("Descrição nula!");
        else
            this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public final void setTipo(String tipo) throws IllegalArgumentException{
        if (tipo == null)
            throw new IllegalArgumentException("Tipo nulo!");
        else
            this.tipo = tipo;
    }
}
