/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado.mercadoria;

import controlTest.ResetTable;
import java.sql.SQLException;
import java.util.List;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Produto;
import modelo.usuarios.Endereco;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20162bsi0040
 */
public class ProdutoDAOTest {
    private Produto produto;
    public ProdutoDAOTest() {
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
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        Supermercado supermercado = new Supermercado(-52.2471,-2.5297,"serra 03","44.122.623/0001-02", "EPA", endereco);
        produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        int result = ProdutoDAO.create(produto,supermercado);
        produto = new Produto(result, produto.getCodigo(),produto.getCusto(), produto.getDescricao(),produto.getMarca(), produto.getNome(),produto.getPrecoVenda(),produto.getQtdPrateleira(), produto.getQtdEstoque(), produto.getTipo());
        
        System.out.println("id = "+result);
        //int id, String codigo, double custo, String descricao, String marca, String nome, double precoVenda, int qtdPrateleira, int qtdEstoque, String tipo
        
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }



    /**
     * Test of readProdutosBySupermercado method, of class ProdutoDAO.
     */
    @Test
    public void testReadProdutosBySupermercado() throws Exception {
        System.out.println("readProdutosBySupermercado");
        Supermercado supermercado = null;
        List<Produto> expResult = null;
        List<Produto> result = ProdutoDAO.readProdutosBySupermercado(supermercado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readProdutosById method, of class ProdutoDAO.
     */
    @Test
    public void testReadProdutosById() throws Exception {
        System.out.println("readProdutosById");
        int id = 0;
        Produto expResult = null;
        Produto result = ProdutoDAO.readProdutosById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
