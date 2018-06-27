/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado.mercadoria;

import controlTest.ResetTable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import database.supermercado.SupermercadoDAO;
import database.usuarios.PessoaJuridicaDAO;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Fornecedor;
import modelo.supermercado.mercadoria.Lote;
import modelo.supermercado.mercadoria.Produto;
import modelo.usuarios.Endereco;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author 20162bsi0147
 */
public class LoteDAOTest {
    private Lote lote;
    private Supermercado supermercado;
    private Produto produto;
    private Fornecedor fornecedor;
    
    public LoteDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp()throws ClassNotFoundException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        ResetTable.cleanAllTables();
        System.out.println("create");
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        
        supermercado = new Supermercado(-52.2471,-2.5297,"serra 03","44.122.623/0001-02", "EPA", endereco);
        int idSupermercado = SupermercadoDAO.create(supermercado);
        
        supermercado = new Supermercado(idSupermercado,supermercado.getLatitude(),supermercado.getLongitude(),supermercado.getUnidade(),supermercado.getCnpj(), supermercado.getNome(), endereco);
        System.out.println("idSupermercado = "+idSupermercado);
        
        produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        int idProd = ProdutoDAO.create(produto, supermercado);
        
        System.out.println("idProduto = "+idProd);
        produto = new Produto(idProd, produto.getCodigo(), produto.getCusto(), produto.getDescricao(), produto.getMarca(), produto.getNome(), produto.getPrecoVenda(), produto.getQtdPrateleira(), produto.getQtdEstoque(),produto.getTipo());
        
        fornecedor = new Fornecedor("35.415.363/0001-72", "EPA", endereco);
        int idForc = PessoaJuridicaDAO.create(fornecedor);
        
        System.out.println("idForc = "+idForc);
        fornecedor = new Fornecedor(fornecedor.getCnpj(), idForc,fornecedor.getNome(), endereco);
        
        lote = new Lote(new Date("2018/05/28"), new Date("2018/05/01"),new Date("2018/06/28"), 100,"Fralda XG",produto);
        int idLote = LoteDAO.create(lote,fornecedor,produto,supermercado);
        
        System.out.println("idLote = "+idLote);
        lote = new Lote(idLote,lote.getDataCompra(),lote.getDataFabricacao(),lote.getDataValidade(),lote.getNumUnidades(),lote.getIdentificador(), lote.getProduto());

        SupermercadoDAO.addFornecedor(fornecedor,supermercado);
    }
    
  @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }

    
    /**
     * Test of readLotesBySupermercado method, of class LoteDAO.
     */
   @Test
    public void testReadLotesBySupermercado() throws Exception {
        System.out.println("readLotesBySupermercado");

 
        List<Lote> result = LoteDAO.readLotesBySupermercado(supermercado,null,null,null,null,null,null,null);
        assertEquals(1, result.size());
        System.out.println(result);
    }

    /**
     * Test of readLotesByProduto method, of class LoteDAO.
     */
    @Test
    public void testReadLotesByProduto() throws Exception {
        System.out.println("readLotesByProduto");

        List<Lote> result = LoteDAO.readLotesByProduto(produto);
        assertEquals(1, result.size());
        System.out.println(result);
    }

    /**
     * Test of readLotesByFornecedor method, of class LoteDAO.
     */
    @Test
    public void testReadLotesByFornecedor() throws Exception {
        System.out.println("readLotesByFornecedor");

        List<Lote> result = LoteDAO.readLotesByFornecedor(fornecedor,supermercado);
        assertEquals(1, result.size());
        System.out.println(result);
    }
    
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        
        int id = lote.getId();
        LoteDAO.delete(id);
    }

    /**
     * Test of readLotesProxVal method, of class LoteDAO.
     */
    @Test
    public void testReadLotesProxVal() throws Exception {
        System.out.println("readLotesProxVal");
        
        List<Lote> result = LoteDAO.readLotesProxVal(produto, 30);
        assertEquals(1, result.size());
    }

    /**
     * Test of update method, of class LoteDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        
        lote = new Lote(lote.getId(),new Date("2018/04/28"), new Date("2018/04/01"),new Date("2018/05/28"), 100,"54478678",produto);
        LoteDAO.update(lote);
    }

    
}
