/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.filter;

import java.util.Date;
import modelo.usuarios.PessoaFisica;
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
public class FilterTest {
    
    public FilterTest() {
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
     * Test of getFilter method, of class Filter.
     */
    @Test
    public void testGetFilter() {
        System.out.println("getFilter");
        Filter filter = new Filter();
        
        Clause clause = new Clause("nome","%Joel%", Clause.ILIKE);
        filter.addClause(clause);
        
        clause = new Clause("validade", new Date("2018/05/28"), Clause.MAIOR_IGUAL);
        filter.addClause(clause);
        
        clause = new Clause("salario",5000.00, Clause.DIFERENTE);
        filter.addClause(clause);
        
        clause = new Clause("idade",18, Clause.IGUAL);
        filter.addClause(clause);
        
        clause = new Clause("numero", null, Clause.MENOR);
        filter.addClause(clause);
        
        clause = new Clause("genero", PessoaFisica.Genero.F.toChar(), Clause.IGUAL);
        filter.addClause(clause);

        String expected = "AND nome ILIKE '%Joel%' AND validade >= '2018-05-28' AND salario != 5000.0 AND idade = 18 AND genero = 'F' ";

        String result = filter.getFilter();
        
        assertEquals(expected,result);
    }
    
}
