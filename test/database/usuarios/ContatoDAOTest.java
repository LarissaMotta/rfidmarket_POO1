/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.usuarios;

import java.util.List;
import modelo.usuarios.Contato;
import modelo.usuarios.Pessoa;
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
public class ContatoDAOTest {
    
    public ContatoDAOTest() {
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
     * Test of create method, of class ContatoDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Contato contato = null;
        Pessoa pessoa = null;
        int expResult = 0;
        int result = ContatoDAO.create(contato, pessoa);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readContatosByPessoa method, of class ContatoDAO.
     */
    @Test
    public void testReadContatosByPessoa() throws Exception {
        System.out.println("readContatosByPessoa");
        Pessoa pessoa = null;
        List<Contato> expResult = null;
        List<Contato> result = ContatoDAO.readContatosByPessoa(pessoa);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
