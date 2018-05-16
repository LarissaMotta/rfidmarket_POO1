/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author 20162bsi0147
 */
public class Endereco {
    private final String bairro;
    private final String cep;
    private final String cidade;
    private final String estado;
    private final int numero;
    private final String ruaAvenida;

    public Endereco(String bairro, String cep, String cidade, String estado, int numero, String ruaAvenida) {
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.ruaAvenida = ruaAvenida;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public int getNumero() {
        return numero;
    }

    public String getRuaAvenida() {
        return ruaAvenida;
    }
    
    
}
