/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import java.util.List;

public class HistoricoCompra {
    private int id;
    private Date data;
    
    public HistoricoCompra(int id, Date data) {
        this.id = id;
        this.data = data;
    }

    public List<Produto> getProdutos(){
        
    }
    
    public Supermecado getSupermecado(){
        
    }
}
