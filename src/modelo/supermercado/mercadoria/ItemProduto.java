/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.supermercado.mercadoria;

import util.Util;

/**
 *
 * @author joel-
 */
public class ItemProduto {
    private int id;                 // não pode ser <= 0
    private double precoCompra;     // não pode ser <= 0
    private int quantidade;         // não pode ser <= 0
    private final Produto produto;  // deve ser carregado e não passado para construtor

    // Pode ser usada quando para instanciar a partir de dados do BD
    public ItemProduto(int id, double precoCompra, int quantidade, Produto produto) throws IllegalArgumentException{
        Util.verificaID(id);
        Util.verificaIsObjNull(produto, "Produto");
        if (precoCompra <= 0) throw new IllegalArgumentException("Preço de compra menor ou igual a 0!");
        if (quantidade <= 0)  throw new IllegalArgumentException("Quantidade menor ou igual a 0!");
        
        this.id = id;
        this.precoCompra = precoCompra;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public ItemProduto(double precoCompra, int quantidade, Produto produto) throws IllegalArgumentException{
        Util.verificaIsObjNull(produto, "Produto");
        if (precoCompra <= 0) throw new IllegalArgumentException("Preço de compra menor ou igual a 0!");
        if (quantidade <= 0)  throw new IllegalArgumentException("Quantidade menor ou igual a 0!");
        
        this.precoCompra = precoCompra;
        this.quantidade = quantidade;
        this.produto = produto; // deve ser passado por parametro para fazer a ligacao entre as tabelas no BD
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

    public Produto getProduto() {
        return produto;
    }
}
