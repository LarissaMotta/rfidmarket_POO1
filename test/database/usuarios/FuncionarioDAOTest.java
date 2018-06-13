/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.usuarios;

import java.util.List;
import modelo.supermercado.Supermercado;
import modelo.usuarios.Funcionario;
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
public class FuncionarioDAOTest {
    
    public FuncionarioDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class FuncionarioDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Funcionario funcionario = null;
        Supermercado supermercado = null;
        int expResult = 0;
        int result = FuncionarioDAO.create(funcionario, supermercado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readFuncionariosBySupermercado method, of class FuncionarioDAO.
     */
    @Test
    public void testReadFuncionariosBySupermercado() throws Exception {
        System.out.println("readFuncionariosBySupermercado");
        Supermercado supermercado = null;
        List<Funcionario> expResult = null;
        List<Funcionario> result = FuncionarioDAO.readFuncionariosBySupermercado(supermercado);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
