/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cliente;

import database.ItemProdutoDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import modelo.ItemProduto;
import util.*;

/**
 *
 * @author joel-
 */
public class Compra {
    private int id;
    private final Date dataHora;
    private List<ItemProduto> itens;

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Compra(int id, Date dataHora) throws IllegalArgumentException{
        Util.verificaID(id);
        Util.verificaIsObjNull(dataHora);
        this.id = id;
        this.dataHora = dataHora;
    }
    
    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Compra(Date dataHora, List<ItemProduto> itens) throws IllegalArgumentException{
        Util.verificaIsObjNull(dataHora);
        
        this.dataHora = dataHora;
        setItens(itens);
    }
    
    public int getId() {
        return id;
    }
    
    // retorna os itens e inicializa o atributo itens caso ele seja = null
    public List<ItemProduto> getItens() {
        if (itens == null){ // se true, significa que os itens não foram carregados ainda
            try {
                itens = ItemProdutoDAO.readItensByCompra(this);
            }catch (ClassNotFoundException | SQLException ex){
                ex.printStackTrace();
            }
        }
        
        return itens;
    }

    public final void setItens(List<ItemProduto> itens) {
        Util.verificaIsObjNull(itens);
        
        this.itens = itens;
    }
    
    public Date getDataHora() {
        return dataHora;
    }
    
    public double getValorTotal(){
        if (itens == null) getItens(); // inicializa itens a partir do BD
        
        double valor = 0;
        for (ItemProduto item : itens){
            valor += item.getPrecoCompra();
        }
        
        return valor;
    }
}
