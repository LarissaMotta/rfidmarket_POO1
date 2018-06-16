/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado.mercadoria;

import controlTest.ResetTable;
import database.supermercado.CompraDAO;
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
import objGeneretor.CartaoTDAO;
import objGeneretor.ClienteTDAO;
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
    private ItemProduto itemProduto;
    public ItemProdutoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
        @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
        System.out.println("create");
        Produto produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        itemProduto = new ItemProduto(35.00,02,produto);
        Compra compra = new Compra(1, new Date(14,06,2018));
        int result = ItemProdutoDAO.create(compra.getId(),itemProduto);
        itemProduto = new ItemProduto(result, itemProduto.getPrecoCompra(),itemProduto.getQuantidade(), itemProduto.getProduto());
        
        System.out.println("id = "+result); 
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
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        Supermercado supermercado = new Supermercado(1,-52.2471,-2.5297,"serra 03","44.122.623/0001-02", "EPA", endereco);
        
        List<ItemProduto> itens = new ArrayList<>();
        Cliente cliente = ClienteTDAO.readCliente();
        Cartao cartao = CartaoTDAO.readCartao();
        Produto produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        itemProduto = new ItemProduto(35.00,02,produto);
        itens.add(itemProduto);
        Compra compra = new Compra(new Date(2018,06,20),itens);
        int result = CompraDAO.create(compra,cliente,cartao,supermercado);
        compra = new Compra(result, compra.getDataHora());
        
        List<ItemProduto> resultado = ItemProdutoDAO.readItensByCompra(compra);
        System.out.println(resultado);
    }
    
}
