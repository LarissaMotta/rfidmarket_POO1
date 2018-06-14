/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.usuarios;

import controlTest.ResetTable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import modelo.pagamento.Cartao;
import modelo.usuarios.Cliente;
import modelo.usuarios.Contato;
import modelo.usuarios.Contato.Tipo;
import modelo.usuarios.Endereco;
import modelo.usuarios.Pessoa;
import modelo.usuarios.PessoaFisica;
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
    private Contato contato;
  
    
    public ContatoDAOTest() {
    }
    
    
    /*
    @BeforeClass
    public static void setUpClass()throws ClassNotFoundException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException  {
         ResetTable.cleanAllTables();
        System.out.println("create");
        // RELACIONAR CONTATO COM A PESSOA PELO FK_PESSOA   
        //int id, String descricao, Tipo tipo
        contato = new Contato(2, "telefone", "98765432");
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        Pessoa pessoa = new Pessoa(1,"Teste", endereco) {};
        //int id, String nome, Endereco endereco
        int id = ContatoDAO.create(contato,pessoa);
        int result = ContatoDAO.create(contato, pessoa);
        
        contato = new Contato(id, contato.getDescricao(), contato.getTipo());
        
        System.out.println("id = "+result);
    */
    
    
    @AfterClass
    public static void tearDownClass() {
    }
    
 
    
     @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }
   
      
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = contato.getId();
        ContatoDAO.delete(id);
        
       
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
