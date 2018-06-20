/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.pagamento;

import controlTest.ResetTable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import modelo.pagamento.Cartao;
import modelo.usuarios.Cliente;
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
public class CartaoDAOTest {
     private Cartao cartao;
    
    public CartaoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
     @Before
    public void setUp() throws ClassNotFoundException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        ResetTable.cleanAllTables();
        System.out.println("create");
        
        cartao = new Cartao("MasterCard", new  java.util.Date(2019, 8, 1), "5482657412589634", "Maria", Cartao.Tipo.CREDITO);
        int id = CartaoDAO.create(cartao);
         int result = CartaoDAO.create(cartao);
        
        cartao = new Cartao(id, cartao.getBandeira(), cartao.getDataValid(), cartao.getNumero(), cartao.getTitular(), cartao.getTipo());
        
        System.out.println("id = "+result);
    }
    
    ///LALALA
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }

    /**
     * Test of readCartoesByCliente method, of class CartaoDAO.
     */
    @Test
    public void testReadCartoesByCliente() throws Exception {
        System.out.println("readCartoesByCliente");
        
        cartao = CartaoTDAO.readCartao();
        Cliente cliente = ClienteTDAO.readCliente();
        cliente.setNome(cartao.getTitular());
        
        List<Cartao> result = CartaoDAO.readCartoesByCliente(cliente);
        System.out.println(result);
    }
    
    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
   

  

  
        

}
