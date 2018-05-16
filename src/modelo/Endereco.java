/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class Endereco {
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private int numero;
    private String ruaAvenida;

    public Endereco(String bairro, String cep, String cidade, String estado, int numero, String ruaAvenida) throws IllegalArgumentException{
        setBairro(bairro);
        setCep(cep);
        setCidade(cidade);
        setEstado(estado);
        setNumero(numero);
        setRuaAvenida(ruaAvenida);
    }

    public final void setBairro(String bairro) throws IllegalArgumentException{
        if (bairro == null)
            throw new IllegalArgumentException("Bairro nulo!");
        else
            this.bairro = bairro;
    }

    public final void setCep(String cep) throws IllegalArgumentException{
        if (cep == null)
            throw new IllegalArgumentException("CEP nulo!");
        else if (cep.length() < 9)
            throw new IllegalArgumentException("CEP inválido!");
        else
            this.cep = cep;
    }

    public final void setCidade(String cidade) throws IllegalArgumentException{
        if (cidade == null)
            throw new IllegalArgumentException("Cidade nula!");
        else
            this.cidade = cidade;
    }

    public final void setEstado(String estado) throws IllegalArgumentException{
        if (estado == null)
            throw new IllegalArgumentException("Estado nulo!");
        else if (estado.length() != 2)
            throw new IllegalArgumentException("Estado inválido! Somente siglas serão aceitas");
        else
            this.estado = estado;
    }

    public final void setNumero(int numero) throws IllegalArgumentException{
        if (numero < 0) throw new IllegalArgumentException("Número inválido: menor que 0!");
        this.numero = numero;
    }

    public final void setRuaAvenida(String ruaAvenida) throws IllegalArgumentException{
        if (ruaAvenida == null)
            throw new IllegalArgumentException("Rua/Avenida nula!");
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
