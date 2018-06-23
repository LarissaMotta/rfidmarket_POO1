/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.usuarios;

import controlTest.ResetTable;
import database.supermercado.SupermercadoDAO;
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
    private Supermercado supermercado;
    
    public FuncionarioDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    
    @Before
    public void setUp()throws ClassNotFoundException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
         ResetTable.cleanAllTables();
        System.out.println("create");
        
        Endereco end = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 76, "Rua Xablau");
        supermercado = new Supermercado(18.5774, 15.1741, "Serra", "11.457.914/0001-00", "Carone", end);
        int idSuperm = SupermercadoDAO.create(supermercado);
        supermercado = new Supermercado(idSuperm, supermercado.getLatitude(), supermercado.getLongitude(), supermercado.getUnidade(), supermercado.getCnpj(), supermercado.getNome(), end);
                
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        funcionario = new Funcionario("estagiario", "atendente","216.856.707-76", new Date(11,06,2018), Funcionario.Genero.M, "JXSEP@hotmail.com", "testedesenha", "Joel", endereco);
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

        List<Funcionario> result = FuncionarioDAO.readFuncionariosBySupermercado(supermercado,funcionario.getNome(),funcionario.getCpf(),funcionario.getGenero(),funcionario.getSetor(),funcionario.getCargo());
        System.out.println(result);//vai printar vazio
    }
    
}
