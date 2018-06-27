/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado;

import controlTest.ResetTable;
import database.pagamento.CartaoDAO;
import database.supermercado.mercadoria.ProdutoDAO;
import database.usuarios.ClienteDAO;
import database.usuarios.PessoaJuridicaDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.pagamento.Cartao;
import modelo.supermercado.Compra;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.ItemProduto;
import modelo.supermercado.mercadoria.Produto;
import modelo.usuarios.Cliente;
import modelo.usuarios.Endereco;
import modelo.usuarios.PessoaFisica;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author laly_
 */
public class CompraDAOTest {
    private Cliente cliente;
    private Cartao cartao;
    private Supermercado supermercado;
    
    public CompraDAOTest() {
    }
    
    //create(Compra compra, Cliente cliente, Cartao cartao, Supermercado supermercado)
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        ResetTable.cleanAllTables();
        System.out.println("create");
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        supermercado = new Supermercado(1,-52.2471,-2.5297,"serra 03","44.122.623/0001-02", "EPA", endereco);
        
        int idSupermercado = SupermercadoDAO.create(supermercado);
        supermercado = new Supermercado(idSupermercado,supermercado.getLatitude(),supermercado.getLongitude(),supermercado.getUnidade(),supermercado.getCnpj(), supermercado.getNome(), endereco);
        
        System.out.println("idSupermercado = "+idSupermercado);
        
        cliente = new Cliente("216.856.707-76", new Date(), PessoaFisica.Genero.M, "joel@hotmail.com", "testedesenha", "Joel", endereco);
        int idCliente = ClienteDAO.create(cliente);
        
        cliente = new Cliente(cliente.getCpf(), cliente.getDataNasc(), cliente.getGenero(), cliente.getLogin(), cliente.getSenha(),idCliente, cliente.getNome(), endereco);
        System.out.println("idCliente = "+idCliente);
        
        cartao = new Cartao("MasterCard", new  java.util.Date(2019, 8, 1), "5482657412589634", "Maria", Cartao.Tipo.CREDITO);
        int idCartao = CartaoDAO.create(cartao);
        
        cartao = new Cartao(idCartao, cartao.getBandeira(), cartao.getDataValid(), cartao.getNumero(), cartao.getTitular(), cartao.getTipo());
        System.out.println("idCartao = "+idCartao);
        
        ClienteDAO.addCartao(cliente, cartao);
        
        Produto produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        int idProd = ProdutoDAO.create(produto, supermercado);
        
        System.out.println("idProduto = "+idProd);
        produto = new Produto(idProd, produto.getCodigo(), produto.getCusto(), produto.getDescricao(), produto.getMarca(), produto.getNome(), produto.getPrecoVenda(), produto.getQtdPrateleira(), produto.getQtdEstoque(),produto.getTipo());
        
        
        ItemProduto itemProduto = new ItemProduto(35.00,02,produto);
        
        List<ItemProduto> itens = new ArrayList<>();
        itens.add(itemProduto);
        
        Compra compra = new Compra(new Date(2018,06,20),itens);
        int idCompra = CompraDAO.create(compra,cliente,cartao,supermercado);
        
        System.out.println("idCompra = "+idCompra);
    }
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }

    /**
     * Test of readHistoricoComprasByCartao method, of class CompraDAO.
     */
    @Test
    public void testReadHistoricoComprasByCartao() throws Exception {
        System.out.println("readHistoricoComprasByCartao");
        
        List<Compra> result = CompraDAO.readHistoricoComprasByCartao(cliente, cartao);
        assertEquals(1, result.size());
        System.out.println(result);
    }

    /**
     * Test of readHistoricoComprasBySupermercado method, of class CompraDAO.
     */
    @Test
    public void testReadHistoricoComprasBySupermercado() throws Exception {
        System.out.println("readHistoricoComprasBySupermercado");
        
        List<Compra> result = CompraDAO.readHistoricoComprasBySupermercado(supermercado);
        assertEquals(1, result.size());
        System.out.println(result);
        
    }

    /**
     * Test of readHistoricoComprasByCliente method, of class CompraDAO.
     */
    @Test
    public void testReadHistoricoComprasByCliente_Cliente() throws Exception {
        System.out.println("readHistoricoComprasByCliente");
        
        List<Compra> result = CompraDAO.readHistoricoComprasByCliente(cliente);
        assertEquals(1, result.size());
        System.out.println(result);
    }

    /**
     * Test of readHistoricoComprasByCliente method, of class CompraDAO.
     */
    @Test
    public void testReadHistoricoComprasByCliente_Cliente_Supermercado() throws Exception {
        System.out.println("readHistoricoComprasByCliente");
        
        List<Compra> result = CompraDAO.readHistoricoComprasByCliente(cliente,supermercado);
        assertEquals(1, result.size());
        System.out.println(result);
    }
}
