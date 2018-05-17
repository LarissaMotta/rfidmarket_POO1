/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
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

    
    // Retorna uma lista com todas as vendas (compras dos clientes) feitas pelo supermercado
    public List<Compra> getHistoricoCompras() {
        //TODO criar função na classe CompraDAO para carregar no BD
    }
    
    // Retorna uma lista com todos os fornecedores deste supermercado
    public List<Fornecedor> getFornecedores() {
        //TODO criar função na classe FornecedorDAO para carregar no BD
    }
    
    // Retorna umaa lista com todos os funcionarios do supermercado
    public List<Funcionario> getFuncionarios() {
        //TODO criar função na classe FuncionarioDAO para carregar no BD
    }
    
    // Retorna uma lista com todos os produtos do supermercado
    public List<Produto> getProdutos() {
        //TODO criar função na classe ProdutoDAO para carregar no BD
    }
    
    // Retorna uma lista com todas os lotes do supermercado
    public List<Lote> getLotes() {
        //TODO criar função na classe LoteDAO para carregar no BD
    }
    
    // Retorna uma lista com todos os clientes do supermercado
    // Ou seja, os clientes que ja fizeram ao menos 1 compra no supermecado
    public List<Cliente> getClientes(){
        //TODO criar função na classe ClienteDAO para carregar no BD
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
