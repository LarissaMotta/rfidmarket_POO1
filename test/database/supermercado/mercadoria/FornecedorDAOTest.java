/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado.mercadoria;

import controlTest.ResetTable;
import database.supermercado.SupermercadoDAO;
import database.usuarios.PessoaJuridicaDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Fornecedor;
import modelo.supermercado.mercadoria.Lote;
import modelo.supermercado.mercadoria.Produto;
import modelo.usuarios.Endereco;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20162bsi0040
 */
public class FornecedorDAOTest {
    private Fornecedor fornecedor;
    private Lote lote;
    private Supermercado supermercado;
    
    public FornecedorDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
        System.out.println("create");
        
        Endereco endere = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 76, "Rua Xablau");
        fornecedor = new Fornecedor("44.122.623/0001-02", "EPA", endere);
        int result = PessoaJuridicaDAO.create(fornecedor);
        fornecedor = new Fornecedor(fornecedor.getCnpj(), result,fornecedor.getNome(), endere);
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        supermercado = new Supermercado(18.5774, 15.1741, "Serra", "35.415.363/0001-72", "Carone", endereco);
        int idSuperm = SupermercadoDAO.create(supermercado);
        supermercado = new Supermercado(idSuperm, supermercado.getLatitude(), supermercado.getLongitude(), supermercado.getUnidade(), supermercado.getCnpj(), supermercado.getNome(), endereco);
        
        Produto produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        int idProd = ProdutoDAO.create(produto,supermercado);
        produto = new Produto(idProd, produto.getCodigo(), produto.getCusto() , produto.getDescricao(), produto.getMarca() , 
                produto.getNome(), produto.getPrecoVenda(), produto.getQtdPrateleira(), produto.getQtdEstoque(), produto.getTipo());
               
        
        lote = new Lote(new Date(2018,06,20), new Date(2018,02,11),new Date(2019,02,11), 100,"Fralda XG",produto);
        int idLote = LoteDAO.create(lote,fornecedor,produto,supermercado);
        lote = new Lote(idLote ,lote.getDataCompra(),lote.getDataFabricacao(),lote.getDataValidade(),lote.getNumUnidades(),lote.getIdentificador(), lote.getProduto());
        
        SupermercadoDAO.addFornecedor(fornecedor, supermercado);
        
        System.out.println("id = "+result);
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }


    /**
     * Test of readFornecedoresBySupermercado method, of class FornecedorDAO.
     */
    @Test
    public void testReadFornecedoresBySupermercado() throws Exception {
        System.out.println("readFornecedoresBySupermercado");
        
        List<Fornecedor> resultado = FornecedorDAO.readFornecedoresBySupermercado(supermercado, "EPA","44.122.623/0001-02");
        System.out.println(resultado);
    
    }

    /**
     * Test of readFornecedorByLote method, of class FornecedorDAO.
     */
    @Test
    public void testReadFornecedorByLote() throws Exception {
        System.out.println("readFornecedorByLote");
 
        Fornecedor result = FornecedorDAO.readFornecedorByLote(lote);
        System.out.println(result);

    }
    
}
