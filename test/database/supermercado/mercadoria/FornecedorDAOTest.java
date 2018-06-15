/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado.mercadoria;

import controlTest.ResetTable;
import database.usuarios.PessoaJuridicaDAO;
import java.sql.SQLException;
import java.util.List;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Fornecedor;
import modelo.supermercado.mercadoria.Lote;
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
public class FornecedorDAOTest {
    private Fornecedor fornecedor;
    
    public FornecedorDAOTest() {
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
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 76, "Rua Xablau");
        fornecedor = new Fornecedor("44.122.623/0001-02", "EPA", endereco);
        int result = PessoaJuridicaDAO.create(fornecedor);
        fornecedor = new Fornecedor(fornecedor.getCnpj(), result,fornecedor.getNome(), endereco);
        
        System.out.println("id = "+result);
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }


    /**
     * Test of readFornecedoresBySupermercado method, of class FornecedorDAO.
     */
    @Test
    public void testReadFornecedoresBySupermercado() throws Exception {
        System.out.println("readFornecedoresBySupermercado");
        Supermercado supermercado = null;
        List<Fornecedor> expResult = null;
        List<Fornecedor> result = FornecedorDAO.readFornecedoresBySupermercado(supermercado,null,null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readFornecedorByLote method, of class FornecedorDAO.
     */
    @Test
    public void testReadFornecedorByLote() throws Exception {
        System.out.println("readFornecedorByLote");
        Lote lote = null;
        Fornecedor expResult = null;
        Fornecedor result = FornecedorDAO.readFornecedorByLote(lote);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
