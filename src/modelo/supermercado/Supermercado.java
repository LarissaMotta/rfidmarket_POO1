/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.supermercado;

import modelo.pessoa.PessoaJuridica;
import modelo.pessoa.Endereco;
import util.Util;

/**
 *
 * @author joel-
 */
public class Supermercado extends PessoaJuridica{
    private double latitude;
    private double longitude;
    private String unidade;

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Supermercado(int id, double latitude, double longitude, String unidade, String cnpj, String nome, Endereco endereco) throws IllegalArgumentException {
        super(cnpj, id, nome, endereco);
        Util.verificaStringNullVazia(unidade);
        
        this.latitude = latitude;
        this.longitude = longitude;
        this.unidade = unidade;
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Supermercado(double latitude, double longitude, String unidade, String cnpj, String nome, Endereco endereco) throws IllegalArgumentException {
        super(cnpj, nome, endereco);
        Util.verificaStringNullVazia(unidade);
        
        this.latitude = latitude;
        this.longitude = longitude;
        this.unidade = unidade;
    }
    
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}
