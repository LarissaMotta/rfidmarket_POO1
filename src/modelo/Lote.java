/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

public class Lote {
    private final int id;
    private final String codigoProd;
    private final Date dataCompra;
    private final Date dataFabricacao;
    private final Date dataValidade;
    private final int numUnidades;
    private final String registro;

    public Lote(int id, String codigoProd, Date dataCompra, Date dataFabricacao, Date dataValidade, int numUnidades, String registro) throws IllegalArgumentException{
        if (id <= 0 || codigoProd == null || dataCompra == null || dataFabricacao == null || dataValidade == null || numUnidades <= 0 || registro == null)
            throw new IllegalArgumentException("ID inválido: menor que 1!");
        
        this.id = id;
        this.codigoProd = codigoProd;
        this.dataCompra = dataCompra;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.numUnidades = numUnidades;
        this.registro = registro;
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
    
}
