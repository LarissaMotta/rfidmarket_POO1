/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.filter;

import java.util.Date;
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
        
        Clause clause = new Clause("%Joel%", Clause.ILIKE);
        filter.addClause("nome", clause);
        
        clause = new Clause(new Date(), Clause.MAIOR_IGUAL);
        filter.addClause("validade", clause);
        
        clause = new Clause(null, Clause.MENOR);
        filter.addClause("numero", clause);
        
        String result = filter.getFilter();
        
        System.out.println("filtro = " + result);
    }
    
}
