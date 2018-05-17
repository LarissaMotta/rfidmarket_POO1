/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import util.Util;

public class Produto {
    // nenhum atributo pode ser null
    private int id;                 // não pode ser <= 0
    private final String codigo;    // TODO: verificar se é string ou int
    private double custo;
    private String descricao;       
    private final String marca;     // Faz sentido eu mudar a marca do produto depois de instancia-lo? ou seja, final ou não?
    private final String nome;      // E o nome? Faz sentido permitir muda-lo?
    private double precoVenda;      // deve ser > 0
    private int qtdPrateleira;      // deve ser > 0
    private int qtdEstoque;         // deve ser > 0
    private final String tipo;      // E o tipo? Faz sentido permitir muda-lo?

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Produto(int id, String codigo, double custo, String descricao, String marca, String nome, double precoVenda, int qtdPrateleira, int qtdEstoque, String tipo) throws IllegalArgumentException{
        Util.verificaID(id);
        Util.verificaStringNullVazia(marca);
        Util.verificaStringNullVazia(nome);
        Util.verificaStringNullVazia(tipo);
       
        this.id = id;
        this.codigo = codigo;
        setCusto(custo);
        setDescricao(descricao);
        this.marca = marca;
        this.nome = nome;
        setPrecoVenda(precoVenda);
        setQtdPrateleira(qtdPrateleira);
        setQtdEstoque(qtdEstoque);
        this.tipo = tipo;
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Produto(String codigo, double custo, String descricao, String marca, String nome, double precoVenda, int qtdPrateleira, int qtdEstoque, String tipo) throws IllegalArgumentException{
        Util.verificaStringNullVazia(marca);
        Util.verificaStringNullVazia(nome);
        Util.verificaStringNullVazia(tipo);
        
        this.codigo = codigo;
        setCusto(custo);
        setDescricao(descricao);
        this.marca = marca;
        this.nome = nome;
        setPrecoVenda(precoVenda);
        setQtdPrateleira(qtdPrateleira);
        setQtdEstoque(qtdEstoque);
        this.tipo = tipo;
    }
    
    public final void setCusto(double custo) throws IllegalArgumentException{
        if (custo <= 0) 
            throw new IllegalArgumentException("Custo inválido: menor ou igual à 0!");
        else
            this.custo = custo;
    }

    public final void setDescricao(String descricao) throws IllegalArgumentException{
        Util.verificaStringNullVazia(descricao);
        this.descricao = descricao;
    }

    public final void setPrecoVenda(double precoVenda) throws IllegalArgumentException{
        if (precoVenda <= 0) 
            throw new IllegalArgumentException("Preco de venda inválido: menor ou igual à 0!");
        else
            this.precoVenda = precoVenda;
    }

    public final void setQtdPrateleira(int qtdPrateleira) throws IllegalArgumentException{
        if (qtdPrateleira <= 0) 
            throw new IllegalArgumentException("Quantidade na prateleira inválida: menor ou igual à 0!");
        else
            this.qtdPrateleira = qtdPrateleira;
    }

    public final void setQtdEstoque(int qtdEstoque) throws IllegalArgumentException{
        if (qtdEstoque <= 0) 
            throw new IllegalArgumentException("Quantidade em estoque inválida: menor ou igual à 0!");
        else
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

    public String getTipo() {
        return tipo;
    }
    
}
