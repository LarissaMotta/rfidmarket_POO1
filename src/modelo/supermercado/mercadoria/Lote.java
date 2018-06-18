/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.supermercado.mercadoria;

import java.util.Date;
import util.Util;

public class Lote {
    private int id;                 //não pode ser alterado depois de instanciado e nem <= 0
    private Date dataCompra;
    private Date dataFabricacao;
    private Date dataValidade;      //Único atributo que pode ser null
    private int numUnidades;        //não pode ser <= 0
    private String identificador;
    private final Produto produto;      

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Lote(int id, Date dataCompra, Date dataFabricacao, Date dataValidade, int numUnidades, String identificador, Produto produto) throws IllegalArgumentException{
        Util.verificaID(id);
        Util.verificaIsObjNull(produto, "Produto");
        this.id = id;
        
        setDataCompra(dataCompra);
        setDataFabricacao(dataFabricacao);
        setDataValidade(dataValidade);
        setIdentificador(identificador);
        setNumUnidades(numUnidades);
        this.produto = produto;
    }
    
    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Lote(Date dataCompra, Date dataFabricacao, Date dataValidade, int numUnidades, String identificador, Produto produto) throws IllegalArgumentException{
        Util.verificaIsObjNull(produto, "Produto");
        
        setDataCompra(dataCompra);
        setDataFabricacao(dataFabricacao);
        setDataValidade(dataValidade);
        setIdentificador(identificador);
        setNumUnidades(numUnidades);
        
        this.produto = produto;
    }

    public final void setDataCompra(Date dataCompra) throws IllegalArgumentException{
        Util.verificaIsObjNull(dataCompra, "Data de compra");
        this.dataCompra = dataCompra;
    }

    public final void setDataFabricacao(Date dataFabricacao) throws IllegalArgumentException{
        Util.verificaIsObjNull(dataFabricacao, "Data de fabricação");
        this.dataFabricacao = dataFabricacao;
    }

    public final void setDataValidade(Date dataValidade){
        this.dataValidade = dataValidade;
    }

    public final void setNumUnidades(int numUnidades) throws IllegalArgumentException{
        if (numUnidades <= 0) throw new IllegalArgumentException("Número de unidade menor ou igual a 0!");
        this.numUnidades = numUnidades;
    }

    public final void setIdentificador(String identificador) throws IllegalArgumentException{
        Util.verificaStringNullVazia(identificador, "Identificador");
        this.identificador = identificador;
    }
    
    public int getId() {
        return id;
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

    public String getIdentificador() {
        return identificador;
    }

    public Produto getProduto() {
        return produto;
    }
    
    
}
