/*TESTE*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import util.Util;
/**
 *
 * @author joel-
 */
public class Cartao {
    // nenhum desse valores pode ser null
    private int id;             //não pode ser <= 0 
    private String bandeira;    
    private String dataValid;
    private long numero;        
    private String titular;     
    private String tipo;        //talves tenha que tirar, ou talves tenha que add no Diagrama

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Cartao(int id, String bandeira, String dataValid, long numero, String titular, String tipo) throws IllegalArgumentException{
        Util.verificaID(id);

        this.id = id;
        setBandeira(bandeira);
        setDataValid(dataValid);
        setNumero(numero);
        setTipo(tipo);
        setTitular(titular);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Cartao(String bandeira, String dataValid, long numero, String titular, String tipo) throws IllegalArgumentException{
        this.bandeira = bandeira;
        this.dataValid = dataValid;
        this.numero = numero;
        this.titular = titular;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getBandeira() {
        return bandeira;
    }

    public final void setBandeira(String bandeira) throws IllegalArgumentException{
        Util.verificaStringNullVazia(bandeira);
        this.bandeira = bandeira;
    }

    public String getDataValid() {
        return dataValid;
    }

    public final void setDataValid(String dataValid) throws IllegalArgumentException{
        Util.verificaStringNullVazia(bandeira);
        this.dataValid = dataValid;
    }

    public long getNumero() {
        return numero;
    }

    public final void setNumero(long numero) throws IllegalArgumentException{
        if (numero <= 0 || String.valueOf(numero).length() <= 16) //TODO tem que melhorar essa validação
            throw new IllegalArgumentException("Número do cartão inválido!");
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public final void setTitular(String titular) throws IllegalArgumentException{
        this.titular = titular;
    }

    public String getTipo() {
        return tipo;
    }

    public final void setTipo(String tipo) throws IllegalArgumentException{
        this.tipo = tipo;
    }
}
