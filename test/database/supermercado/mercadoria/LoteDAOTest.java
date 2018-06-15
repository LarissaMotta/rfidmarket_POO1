/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado.mercadoria;

import controlTest.ResetTable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Fornecedor;
import modelo.supermercado.mercadoria.Lote;
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
 * @author 20162bsi0147
 */
public class LoteDAOTest {
    private Lote lote;
    
    public LoteDAOTest() {
    }
    
   
    
 
    
    @Before
    public void setUp()throws ClassNotFoundException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
         ResetTable.cleanAllTables();
        System.out.println("create");
        
        Produto produto = new Produto (2,"6666",127.66,"Ta pegando fogo","top","Churras",356.56,12,15,"npox");
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 76, "Rua Xablau");
        Fornecedor fornecedor = new Fornecedor("44.122.623/0001-02", "EPA", endereco);
        lote = new Lote("666", new Date(20,06,2018), new Date(11,02,2018),new Date(11,02,2019), 333,"Churrasqueira do Faustop",produto);
        Supermercado supermercado = new Supermercado(1,12,13,"Vila Velha","85685","Carone",endereco);
       
        int result = LoteDAO.create(lote,fornecedor,produto,supermercado);
        lote = new Lote(lote.getId(),lote.getCodigoProd(),lote.getDataCompra(),lote.getDataFabricacao(),lote.getDataValidade(),lote.getNumUnidades(),lote.getIdentificador(), lote.getProduto());
        System.out.println("id = "+result);
    }
    
  @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }


    /**
     * Test of readLotesBySupermercado method, of class LoteDAO.
     */
    @Test
    public void testReadLotesBySupermercado() throws Exception {
        System.out.println("readLotesBySupermercado");
        Supermercado superm = null;
        List<Lote> expResult = null;
        List<Lote> result = LoteDAO.readLotesBySupermercado(superm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readLotesByProduto method, of class LoteDAO.
     */
    @Test
    public void testReadLotesByProduto() throws Exception {
        System.out.println("readLotesByProduto");
        Produto produto = null;
        List<Lote> expResult = null;
        List<Lote> result = LoteDAO.readLotesByProduto(produto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readLotesByFornecedor method, of class LoteDAO.
     */
    @Test
    public void testReadLotesByFornecedor() throws Exception {
        System.out.println("readLotesByFornecedor");
        Fornecedor fornecedor = null;
        List<Lote> expResult = null;
        List<Lote> result = LoteDAO.readLotesByFornecedor(fornecedor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = lote.getId();
        LoteDAO.delete(id);
        
       
    }

    
}
