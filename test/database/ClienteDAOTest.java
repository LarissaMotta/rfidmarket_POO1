package database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controlTest.ResetTable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.ItemProduto;
import modelo.cliente.Cartao;
import modelo.cliente.Cliente;
import modelo.cliente.Compra;
import modelo.pessoa.Endereco;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Produto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joel-
 */
public class ClienteDAOTest {
    private Cliente cliente;
    
    public ClienteDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        System.out.println("create");
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", "ES", 75, "Rua Xablau");
        cliente = new Cliente("216.856.707-76", new Date(), 'M', "joel@hotmail.com", "testedesenha", "Joel", endereco);
        
        int result = ClienteDAO.create(cliente);
        
        cliente = new Cliente(cliente.getCpf(), cliente.getDataNasc(), cliente.getGenero(), cliente.getLogin(), cliente.getSenha(),result, cliente.getNome(), endereco);
        System.out.println("id = "+result);
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }

    /**
     * Test of addCartao method, of class ClienteDAO.
     */
    @Test
    public void testAddCartao() throws Exception {
        System.out.println("addCartao");
        
        Cartao cartao = new Cartao("MasterCard", new  Date(2019, 8, 1), 514256985, "Maria", 'C');
        int id = CartaoDAO.create(cartao);
        
        cartao = new Cartao(id, cartao.getBandeira(), cartao.getDataValid(), cartao.getNumero(), cartao.getTitular(), cartao.getTipo());
        
        ClienteDAO.addCartao(cliente, cartao);
    }

    /**
     * Test of readClientesBySupermercado method, of class ClienteDAO.
     */
    @Test
    public void testReadClientesBySupermercado() throws Exception {
        System.out.println("readClientesBySupermercado");
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", "ES", 75, "Rua Xablau");
        Supermercado superm = new Supermercado(18.5774, 15.1741, "Serra", "35.415.363/0001-72", "Carone", endereco);
        
        int idSuperm = SupermercadoDAO.create(superm);
        
        superm = new Supermercado(idSuperm, superm.getLatitude(), superm.getLongitude(), superm.getUnidade(), superm.getCnpj(), superm.getNome(), endereco);
        
        Produto prod = new Produto("Lol", 15.50, "Dwa", "dwa", "gfjkdwa", 19.5, 15, 50, "dwa");
        int idProd = ProdutoDAO.create(prod, superm);
        prod = new Produto(idProd, prod.getCodigo(), prod.getCusto(), prod.getDescricao(), prod.getMarca(), 
                prod.getNome(), prod.getPrecoVenda(), prod.getQtdPrateleira(), prod.getQtdEstoque(), prod.getTipo());
        
        
        Cartao cartao = new Cartao("MasterCard", new  Date(2019, 8, 1), 514256985, "Maria", 'C');
        int idCartao = CartaoDAO.create(cartao);
        
        cartao = new Cartao(idCartao, cartao.getBandeira(), cartao.getDataValid(), cartao.getNumero(), cartao.getTitular(), cartao.getTipo());
        
        ItemProduto item = new ItemProduto(54.6, 12, prod);
        List<ItemProduto> itens = new ArrayList<>();
        itens.add(item);
        Compra compra = new Compra(new Date(), itens);
        
        CompraDAO.create(compra, cliente, cartao, superm);
        
        List<Cliente> result = ClienteDAO.readClientesBySupermercado(superm);
    }
}
