/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 20162bsi0147
 */
public class Contato {
    private int id;
    private Map<String, List<String>> contatos;

    public Contato(int id, Map<String, List<String>> contatos) {
        this.id = id;
        this.contatos = contatos;
    }

    public int getId() {
        return id;
    }

    public Map<String, List<String>> getContatos() {
        return contatos;
    }
    
    public void addContato(String tipo, String contato){
        if (contatos.containsKey(tipo)){
            contatos.get(tipo).add(contato);
        }else {
            List<String> lstContatos = new ArrayList<>();
            lstContatos.add(contato);
            contatos.put(tipo, lstContatos);
        }
    }
}
