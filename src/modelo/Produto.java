/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class Produto {
    private final int id;
    private final String codigo; //TODO: verificar se é string ou int
    private double custo;
    private String descricao;
    private final String marca;
    private final String nome;
    private double precoVenda;
    private int qtdPrateleira;
    private int qtdEstoque;
    private final String tipo;

    public Produto(int id, String codigo, double custo, String descricao, String marca, String nome, double precoVenda, int qtdPrateleira, int qtdEstoque, String tipo) {
        this.id = id;
        this.codigo = codigo;
        this.custo = custo;
        this.descricao = descricao;
        this.marca = marca;
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.qtdPrateleira = qtdPrateleira;
        this.qtdEstoque = qtdEstoque;
        this.tipo = tipo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public void setQtdPrateleira(int qtdPrateleira) {
        this.qtdPrateleira = qtdPrateleira;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getCusto() {
        return custo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMarca() {
        return marca;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public int getQtdPrateleira() {
        return qtdPrateleira;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public int getQtdVendidas() {
        return qtdVendidas;
    }

    public String getTipo() {
        return tipo;
    }
    
}
