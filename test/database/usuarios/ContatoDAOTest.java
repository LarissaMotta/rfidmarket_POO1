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
import modelo.usuarios.*;
import modelo.usuarios.Contato.Tipo;
import modelo.usuarios.Endereco;
import modelo.usuarios.Pessoa;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author 20162bsi0147
 */
public class ContatoDAOTest {
    private Contato contato;
    private Cliente cliente;
  
    
    public ContatoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    @Before
    public void setUp()throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException  {
        ResetTable.cleanAllTables();
        System.out.println("create");
        
        contato = new Contato("98765432", Tipo.CELULAR);
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        cliente = new Cliente("216.856.707-76", new Date(), PessoaFisica.Genero.M, "joel@hotmail.com", "testedesenha",02, "Joel", endereco);
        int idcliente = ClienteDAO.create(cliente);
        cliente = new Cliente(cliente.getCpf(), cliente.getDataNasc(), cliente.getGenero(), cliente.getLogin(), cliente.getSenha(),idcliente, cliente.getNome(), endereco);
                
        int result = ContatoDAO.create(contato,cliente);
        contato = new Contato(result, contato.getDescricao(), contato.getTipo());
        
        System.out.println("id = "+result);
    }
    
    
   /* @AfterClass
    public void tearDownClass() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }*/
    
 
    
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

        List<Contato> result = ContatoDAO.readContatosByPessoa(cliente);
        System.out.println(result);
    }
    
}
