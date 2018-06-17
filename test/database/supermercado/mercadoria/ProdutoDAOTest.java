/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado.mercadoria;

import controlTest.ResetTable;
import database.supermercado.SupermercadoDAO;
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
        Supermercado supermercado = new Supermercado(1,-52.2471,-2.5297,"serra 03","44.122.623/0001-02", "EPA", endereco);
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
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        Supermercado supermercado = new Supermercado(18.5774, 15.1741, "Serra", "35.415.363/0001-72", "Carone", endereco);
        int idSuperm = SupermercadoDAO.create(supermercado);
        supermercado = new Supermercado(idSuperm, supermercado.getLatitude(), supermercado.getLongitude(), supermercado.getUnidade(), supermercado.getCnpj(), supermercado.getNome(), endereco);

        produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        int idProd = ProdutoDAO.create(produto,supermercado);
        produto = new Produto(idProd,produto.getCodigo(),produto.getCusto(),produto.getDescricao(),produto.getMarca(),produto.getNome(),produto.getPrecoVenda(),produto.getQtdPrateleira(),produto.getQtdEstoque(),produto.getTipo());


        List<Produto> result = ProdutoDAO.readProdutosBySupermercado(supermercado,"Fralda XG","Pampers","fralda","0000");
        System.out.println(result);
    }

    /**
     * Test of create method, of class ProdutoDAO.
     */
    @Test
    public void testCreate() throws Exception {
    }

    /**
     * Test of readProdutos method, of class ProdutoDAO.
     */
    @Test
    public void testReadProdutos() throws Exception {
    }
}
