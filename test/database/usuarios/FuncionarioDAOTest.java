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
import modelo.supermercado.Supermercado;
import modelo.usuarios.Endereco;
import modelo.usuarios.Funcionario;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20162bsi0147
 */
public class FuncionarioDAOTest {
    private Funcionario funcionario;
    
    public FuncionarioDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    
    @Before
    public void setUp()throws ClassNotFoundException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
         ResetTable.cleanAllTables();
        System.out.println("create");
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        funcionario = new Funcionario("estagiario", "atendente","216.856.707-76", new Date(11,06,2018), Funcionario.Genero.M, "joel@hotmail.com", "testedesenha", "Joel", endereco);
        Supermercado supermercado = new Supermercado(1,12,13,"Vila Velha","85685","Carone",endereco);
        //int id, double latitude, double longitude, String unidade, String cnpj, String nome, Endereco endereco
        
        int result = FuncionarioDAO.create(funcionario,supermercado);
        
        funcionario = new Funcionario(funcionario.getCargo(),funcionario.getSetor(),funcionario.getCpf(), funcionario.getDataNasc(), funcionario.getGenero(), funcionario.getLogin(), funcionario.getSenha(),result, funcionario.getNome(), endereco);
        System.out.println("id = "+result);
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }

      
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = funcionario.getId();
        FuncionarioDAO.delete(id);
        PessoaFisicaDAO.delete(id);
        PessoaDAO.delete(id);
       
    }

    /**
     * Test of readFuncionariosBySupermercado method, of class FuncionarioDAO.
     */
    @Test
    public void testReadFuncionariosBySupermercado() throws Exception {
        System.out.println("readFuncionariosBySupermercado");
        Supermercado supermercado = null;
        List<Funcionario> expResult = null;
        List<Funcionario> result = FuncionarioDAO.readFuncionariosBySupermercado(supermercado,null,null,null,null,null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
