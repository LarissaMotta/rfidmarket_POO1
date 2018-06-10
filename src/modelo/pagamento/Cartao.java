/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pagamento;

import util.Util;

import java.util.Date;

/**
 *
 * @author joel-
 */
public class Cartao {

    // nenhum desse valores pode ser null
    private int id;             //não pode ser <= 0 
    private final String bandeira;
    private Date dataValid;
    private String numero;
    private String titular;
    private Tipo tipo;          //talves tenha que tirar, ou talves tenha que add no Diagrama

    public enum Tipo {
        CREDITO('C'), DEBITO('D');

        private final char type;

        Tipo(char type) {
            this.type = type;
        }

        public char toChar() {
            return type;
        }
    }

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Cartao(int id, String bandeira, Date dataValid, String numero, String titular, Tipo tipo) throws IllegalArgumentException {
        Util.verificaID(id);
        Util.verificaStringNullVazia(bandeira, "Bandeira");
        
        this.id = id;
        this.bandeira = bandeira;
        setDataValid(dataValid);
        setNumero(numero);
        setTipo(tipo);
        setTitular(titular);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Cartao(String bandeira, Date dataValid, String numero, String titular, Tipo tipo) throws IllegalArgumentException {
        Util.verificaStringNullVazia(bandeira, "Bandeira");
        
        this.bandeira = bandeira;
        setDataValid(dataValid);
        setNumero(numero);
        setTipo(tipo);
        setTitular(titular);
    }

    public int getId() {
        return id;
    }

    public String getBandeira() {
        return bandeira;
    }

    public final Date getDataValid() {
        return dataValid;
    }

    public final void setDataValid(Date dataValid) {
        Util.verificaIsObjNull(dataValid, "Data de Validade");
        this.dataValid = dataValid;
    }

    public String getNumero() {
        return numero;
    }

    public final void setNumero(String numero) throws IllegalArgumentException {
        if (numero == null || numero.length() != 16)
            throw new IllegalArgumentException("Número do cartão inválido!");
        
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public final void setTitular(String titular) throws IllegalArgumentException {
        Util.verificaStringNullVazia(titular, "Titular");
        this.titular = titular;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public final void setTipo(Tipo tipo) throws IllegalArgumentException {
        Util.verificaIsObjNull(tipo, "Tipo");
        this.tipo = tipo;
    }
}
