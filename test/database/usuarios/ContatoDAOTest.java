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
import java.util.List;
import modelo.usuarios.*;
import modelo.usuarios.Contato.Tipo;
import modelo.usuarios.Endereco;
import modelo.usuarios.Pessoa;
import objGeneretor.ClienteTDAO;
import org.junit.After;
import org.junit.AfterClass;
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
    
    
    
    @BeforeClass
    public void setUpClass()throws ClassNotFoundException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException  {
        ResetTable.cleanAllTables();
        System.out.println("create");
        contato = new Contato("98765432", Tipo.CELULAR);
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        Pessoa pessoa = new Pessoa(1,"Teste", endereco){};
        int result = ContatoDAO.create(contato,pessoa);
        
        contato = new Contato(result, contato.getDescricao(), contato.getTipo());
        
        System.out.println("id = "+result);
    }
    
    
    @AfterClass
    public void tearDownClass() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
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

        Cliente cliente = ClienteTDAO.readCliente();
        ContatoDAO.create(new Contato("98765432", Tipo.CELULAR),cliente);
        List<Contato> result = ContatoDAO.readContatosByPessoa(cliente);
        System.out.println(result);
    }
    
}
