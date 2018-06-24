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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20162bsi0147
 */
public class LoteDAOTest {
    private Lote lote;
    private Supermercado supermercado;
    private Produto prod;
    private Fornecedor fornecedor;
    
    public LoteDAOTest() {
    }
    
   
    
 
    
    @Before
    public void setUp()throws ClassNotFoundException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
         ResetTable.cleanAllTables();
        System.out.println("create");
        
        Endereco end = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        supermercado = new Supermercado(-52.2471,-2.5297,"serra 03","44.122.623/0001-02", "EPA", end);
        int idSuper = SupermercadoDAO.create(supermercado);
        supermercado = new Supermercado(idSuper,supermercado.getLatitude(),supermercado.getLongitude(),supermercado.getUnidade(),supermercado.getCnpj(), supermercado.getNome(), end);
        
        prod = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        int idProd = ProdutoDAO.create(prod, supermercado);
        prod = new Produto(idProd, prod.getCodigo(), prod.getCusto() , prod.getDescricao(), prod.getMarca() , 
                prod.getNome(), prod.getPrecoVenda(), prod.getQtdPrateleira(), prod.getQtdEstoque(), prod.getTipo());
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-483", "SERRA", Endereco.Estado.ES, 76, "Rua Xablau");
        fornecedor = new Fornecedor("35.415.363/0001-72", "Paul", endereco);
        int idForn = PessoaJuridicaDAO.create(fornecedor);
        fornecedor = new Fornecedor(fornecedor.getCnpj(), idForn,fornecedor.getNome(), endereco);
        
        SupermercadoDAO.addFornecedor(fornecedor, supermercado); 
        
        lote = new Lote(new Date(20,06,2018), new Date(11,02,2018),new Date(11,02,2019), 333,"Fralda XG",prod);
        int result = LoteDAO.create(lote,fornecedor,prod,supermercado);
        lote = new Lote(result,lote.getDataCompra(),lote.getDataFabricacao(),lote.getDataValidade(),lote.getNumUnidades(),lote.getIdentificador(), lote.getProduto());
        
        
        
        System.out.println("id = "+result);
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
        System.out.println(result);
    }

    /**
     * Test of readLotesByProduto method, of class LoteDAO.
     */
    @Test
    public void testReadLotesByProduto() throws Exception {
        System.out.println("readLotesByProduto");

        List<Lote> result = LoteDAO.readLotesByProduto(prod);
        System.out.println(result);
    }

    /**
     * Test of readLotesByFornecedor method, of class LoteDAO.
     */
    @Test
    public void testReadLotesByFornecedor() throws Exception {
        System.out.println("readLotesByFornecedor");

        List<Lote> result = LoteDAO.readLotesByFornecedor(fornecedor,supermercado);
        System.out.println(result);
    }
    
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = lote.getId();
        LoteDAO.delete(id);
    }

    
}
