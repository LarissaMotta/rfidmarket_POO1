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
    private Compra compra;
    private Cliente cliente;
    private Cartao cartao;
    private Supermercado superm;
    private Produto prod;
    
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
        superm = new Supermercado(18.5774, 15.1741, "Serra", "35.415.363/0001-72", "Carone", endereco);
        int idSuperm = SupermercadoDAO.create(superm);
        superm = new Supermercado(idSuperm, superm.getLatitude(), superm.getLongitude(), superm.getUnidade(), superm.getCnpj(), superm.getNome(), endereco);
        
        List<ItemProduto> itens = new ArrayList<>();
        
        Endereco end = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        cliente = new Cliente("216.856.707-76", new Date(), PessoaFisica.Genero.M, "joel@hotmail.com", "testedesenha", "Joel", end);
        int result = ClienteDAO.create(cliente);
        cliente = new Cliente(cliente.getCpf(), cliente.getDataNasc(), cliente.getGenero(), cliente.getLogin(), cliente.getSenha(),result, cliente.getNome(), end);
       
        cartao = new Cartao("MasterCard", new  Date(2019, 8, 1), "5482657412589634", "Maria", Cartao.Tipo.CREDITO);
        int id = CartaoDAO.create(cartao);
        cartao = new Cartao(id, cartao.getBandeira(), cartao.getDataValid(), cartao.getNumero(), cartao.getTitular(), cartao.getTipo());
        ClienteDAO.addCartao(cliente, cartao);
        
        prod = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        int idProd = ProdutoDAO.create(prod, superm);
        prod = new Produto(idProd, prod.getCodigo(), prod.getCusto() , prod.getDescricao(), prod.getMarca() , 
                prod.getNome(), prod.getPrecoVenda(), prod.getQtdPrateleira(), prod.getQtdEstoque(), prod.getTipo());
        
        ItemProduto itemProduto = new ItemProduto(35.00,02,prod);
        itens.add(itemProduto);
        compra = new Compra(new Date(2018,06,20),itens);
        int resultado = CompraDAO.create(compra,cliente,cartao,superm);
        compra = new Compra(result, compra.getDataHora());
        
        System.out.println("id = "+resultado);
    }
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }



    /**
     * Test of readHistoricoComprasByCliente method, of class CompraDAO.
     */
    @Test
    public void testReadHistoricoCompras() throws Exception {
        System.out.println("readHistoricoCompras");
        
        List<Compra> result = CompraDAO.readHistoricoComprasByCliente(cliente);
        System.out.println(result);
    }

    /**
     * Test of readHistoricoComprasByCartao method, of class CompraDAO.
     */
    @Test
    public void testReadHistoricoComprasByCartao() throws Exception {
        System.out.println("readHistoricoComprasByCartao");
        
        
        List<Compra> result = CompraDAO.readHistoricoComprasByCartao(cliente, cartao);
        System.out.println(result);
    }

    /**
     * Test of readHistoricoComprasBySupermercado method, of class CompraDAO.
     */
    @Test
    public void testReadHistoricoComprasBySupermercado() throws Exception {
        System.out.println("readHistoricoComprasBySupermercado");
     
        List<Compra> result = CompraDAO.readHistoricoComprasBySupermercado(superm);
        System.out.println(result);
        
    }
    
}
