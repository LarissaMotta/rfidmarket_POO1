/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

public class Lote {
    // nenhum dos atributos pode ser null
    private final int id;               //não pode ser alterado depois de instanciado e nem <= 0
    private final String codigoProd;
    private final Date dataCompra;
    private final Date dataFabricacao;
    private final Date dataValidade;
    private final int numUnidades;      //não pode ser <= 0
    private final String registro;
    private final Produto produto;      // deve ser carregado e não passado para construtor

    public Lote(int id, String codigoProd, Date dataCompra, Date dataFabricacao, Date dataValidade, int numUnidades, String registro) throws IllegalArgumentException{
        if (id <= 0 || codigoProd == null || dataCompra == null || dataFabricacao == null || dataValidade == null || numUnidades <= 0 || registro == null)
            throw new IllegalArgumentException("Algum argumento inválido!");
        
        this.id = id;
        this.codigoProd = codigoProd;
        this.dataCompra = dataCompra;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.numUnidades = numUnidades;
        this.registro = registro;
        this.produto = ;    // Fazer carregamento do produto aki
    }
    
    public Lote(String codigoProd, Date dataCompra, Date dataFabricacao, Date dataValidade, int numUnidades, String registro) throws IllegalArgumentException{
        if (codigoProd == null || dataCompra == null || dataFabricacao == null || dataValidade == null || numUnidades <= 0 || registro == null)
            throw new IllegalArgumentException("Algum argumento inválido!");
        
        this.codigoProd = codigoProd;
        this.dataCompra = dataCompra;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.numUnidades = numUnidades;
        this.registro = registro;
        this.produto = ;    // Fazer carregamento do produto aki 
    }
    
    public Supermercado getSupermercado(){
        //TODO criar função na classe SupermercadoDAO para carregar no BD
    }
    
    public int getId() {
        return id;
    }

    public String getCodigoProd() {
        return codigoProd;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public int getNumUnidades() {
        return numUnidades;
    }

    public String getRegistro() {
        return registro;
    }

    public Produto getProduto() {
        return produto;
    }
}
