/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado.mercadoria;

import controlTest.ResetTable;
import database.pagamento.CartaoDAO;
import database.supermercado.CompraDAO;
import database.supermercado.SupermercadoDAO;
import database.usuarios.ClienteDAO;
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
public class ItemProdutoDAOTest {
    private Compra compra;
    
    public ItemProdutoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
        @Before
    public void setUp() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        ResetTable.cleanAllTables();
        System.out.println("create");
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        
        Supermercado supermercado = new Supermercado(-52.2471,-2.5297,"serra 03","44.122.623/0001-02", "EPA", endereco);
        int idSupermercado = SupermercadoDAO.create(supermercado);
        
        supermercado = new Supermercado(idSupermercado,supermercado.getLatitude(),supermercado.getLongitude(),supermercado.getUnidade(),supermercado.getCnpj(), supermercado.getNome(), endereco);
        System.out.println("idSupermercado = "+idSupermercado);
        
        
        Cliente cliente = new Cliente("216.856.707-76", new Date(), PessoaFisica.Genero.M, "joel@hotmail.com", "testedesenha", "Joel", endereco);
        int idCliente = ClienteDAO.create(cliente);
        
        cliente = new Cliente(cliente.getCpf(), cliente.getDataNasc(), cliente.getGenero(), cliente.getLogin(), cliente.getSenha(),idCliente, cliente.getNome(), endereco);
        System.out.println("idCliente = "+idCliente);
        
        Cartao cartao = new Cartao("MasterCard", new  java.util.Date(2019, 8, 1), "5482657412589634", "Maria", Cartao.Tipo.CREDITO);
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
        
        compra = new Compra(new Date(2018,06,20),itens);
        int idCompra = CompraDAO.create(compra,cliente,cartao,supermercado);
        
        compra = new Compra(idCompra, compra.getDataHora());
        System.out.println("idCompra = "+idCompra);
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }


    /**
     * Test of readItensByCompra method, of class ItemProdutoDAO.
     */
    @Test
    public void testReadItensByCompra() throws Exception {
        System.out.println("readItensByCompra");
       
        List<ItemProduto> resultado = ItemProdutoDAO.readItensByCompra(compra);
        assertEquals(1, resultado.size());
        System.out.println(resultado);
    }

}
