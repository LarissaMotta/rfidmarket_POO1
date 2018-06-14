/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado;

import controlTest.ResetTable;
import database.usuarios.PessoaJuridicaDAO;
import java.sql.SQLException;
import java.util.List;
import modelo.supermercado.Compra;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Fornecedor;
import modelo.supermercado.mercadoria.Lote;
import modelo.supermercado.mercadoria.Produto;
import modelo.usuarios.Endereco;
import modelo.usuarios.Funcionario;
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
public class SupermercadoDAOTest {
    private Supermercado supermercado;
      
    public SupermercadoDAOTest() {
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
        supermercado = new Supermercado(-52.2471,-2.5297,"serra 03","44.122.623/0001-02", "EPA", endereco);
        int result = PessoaJuridicaDAO.create(supermercado);
        supermercado = new Supermercado(result,supermercado.getLatitude(),supermercado.getLongitude(),supermercado.getUnidade(),supermercado.getCnpj(), supermercado.getNome(), endereco);
        
        System.out.println("id = "+result);
    }
    
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }
    
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = supermercado.getId();
        PessoaJuridicaDAO.delete(id);
    }


    /**
     * Test of readSupermercadoByFuncionario method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByFuncionario() throws Exception {
        System.out.println("readSupermercadoByFuncionario");
        Funcionario funcionario = null;
        Supermercado expResult = null;
        Supermercado result = SupermercadoDAO.readSupermercadoByFuncionario(funcionario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readSupermercadoByCompra method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByCompra() throws Exception {
        System.out.println("readSupermercadoByCompra");
        Compra compra = null;
        Supermercado expResult = null;
        Supermercado result = SupermercadoDAO.readSupermercadoByCompra(compra);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readSupermercadoByFornecedor method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByFornecedor() throws Exception {
        System.out.println("readSupermercadoByFornecedor");
        Fornecedor fornecedor = null;
        List<Supermercado> expResult = null;
        List<Supermercado> result = SupermercadoDAO.readSupermercadoByFornecedor(fornecedor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readSupermercadoByLote method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByLote() throws Exception {
        System.out.println("readSupermercadoByLote");
        Lote lote = null;
        Supermercado expResult = null;
        Supermercado result = SupermercadoDAO.readSupermercadoByLote(lote);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readSupermercadoByProduto method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByProduto() throws Exception {
        System.out.println("readSupermercadoByProduto");
        Produto produto = null;
        Supermercado expResult = null;
        Supermercado result = SupermercadoDAO.readSupermercadoByProduto(produto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
