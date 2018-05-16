/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author joel-
 */
public class ItemProduto {
    private int id;                 // não pode ser <= 0
    private double precoCompra;     // não pode ser <= 0
    private int quantidade;         // não pode ser <= 0
    private final Produto produto;  // deve ser carregado e não passado para construtor

    public ItemProduto(int id, double precoCompra, int quantidade) throws IllegalArgumentException{
        if (id <= 0 || precoCompra <= 0 || quantidade <= 0)
            throw new IllegalArgumentException("Algum argumento inválido!");
        
        this.id = id;
        this.precoCompra = precoCompra;
        this.quantidade = quantidade;
        this.produto = ;    // Fazer carregamento do produto aki 
    }

    public ItemProduto(double precoCompra, int quantidade) throws IllegalArgumentException{
        if (precoCompra <= 0 || quantidade <= 0)
            throw new IllegalArgumentException("Algum argumento inválido!");
        
        this.precoCompra = precoCompra;
        this.quantidade = quantidade;
        this.produto = ;    // Fazer carregamento do produto aki
    }

    public int getId() {
        return id;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
