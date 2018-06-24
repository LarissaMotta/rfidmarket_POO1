/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado;

import controlTest.ResetTable;
import database.pagamento.CartaoDAO;
import database.supermercado.mercadoria.LoteDAO;
import database.supermercado.mercadoria.ProdutoDAO;
import database.usuarios.ClienteDAO;
import database.usuarios.FuncionarioDAO;
import database.usuarios.PessoaJuridicaDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.pagamento.Cartao;
import modelo.supermercado.Compra;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Fornecedor;
import modelo.supermercado.mercadoria.ItemProduto;
import modelo.supermercado.mercadoria.Lote;
import modelo.supermercado.mercadoria.Produto;
import modelo.usuarios.Cliente;
import modelo.usuarios.Endereco;
import modelo.usuarios.Funcionario;
import modelo.usuarios.PessoaFisica;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Larissa
 */
public class SupermercadoDAOTest {
    private Supermercado supermercado;
    private Produto produto;
    private Fornecedor fornecedor;
    private Lote lote; 
    private Funcionario funcionario;
    
    public SupermercadoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        ResetTable.cleanAllTables();
        System.out.println("create");
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        supermercado = new Supermercado(-52.2471,-2.5297,"serra 03","44.122.623/0001-02", "EPA", endereco);
        int result = SupermercadoDAO.create(supermercado);
        supermercado = new Supermercado(result,supermercado.getLatitude(),supermercado.getLongitude(),supermercado.getUnidade(),supermercado.getCnpj(), supermercado.getNome(), endereco);
        
        Endereco end = new Endereco("Jacaraípe", "29177-487", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        funcionario = new Funcionario("estagiario", "atendente","216.856.707-76", new Date(11,06,2018), Funcionario.Genero.M, "joel_tendencia@hotmail.com", "testedesenha", "Joel", end);
        FuncionarioDAO.create(funcionario, supermercado);
        
        System.out.println("id = "+result);
    }
    
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        ResetTable.cleanAllTables();
    }
    
    /*@Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        int id = supermercado.getId();
        PessoaJuridicaDAO.delete(id);
    }*/


    /**
     * Test of readSupermercadoByFuncionario method, of class SupermercadoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testReadSupermercadoByFuncionario() throws Exception {
        System.out.println("readSupermercadoByFuncionario");

      
        //Funcionario funcionario
        Supermercado result = SupermercadoDAO.readSupermercadoByFuncionario(funcionario);
        System.out.println(result);
    }

    
    
    
    /**
     * Test of readSupermercadoByCompra method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByCompra() throws Exception {
        System.out.println("readSupermercadoByCompra");

        
       List<ItemProduto> itens = new ArrayList<>();
        
        Endereco end = new Endereco("Jacaraípe", "29177-488", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        Cliente cliente = new Cliente("216.856.707-76", new Date(), PessoaFisica.Genero.M, "joel@hotmail.com", "testedesenha", "Joel", end);
        int idCliente = ClienteDAO.create(cliente);
        cliente = new Cliente(cliente.getCpf(), cliente.getDataNasc(), cliente.getGenero(), cliente.getLogin(), cliente.getSenha(),idCliente, cliente.getNome(), end);
       
        Cartao cartao = new Cartao("MasterCard", new  Date(2019, 8, 1), "5482657412589634", "Maria", Cartao.Tipo.CREDITO);
        int id = CartaoDAO.create(cartao);
        cartao = new Cartao(id, cartao.getBandeira(), cartao.getDataValid(), cartao.getNumero(), cartao.getTitular(), cartao.getTipo());
        ClienteDAO.addCartao(cliente, cartao);
        
        produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        int idProd = ProdutoDAO.create(produto, supermercado);
        produto = new Produto(idProd, produto.getCodigo(), produto.getCusto() , produto.getDescricao(), produto.getMarca() , 
                produto.getNome(), produto.getPrecoVenda(), produto.getQtdPrateleira(), produto.getQtdEstoque(), produto.getTipo());

        ItemProduto itemProduto = new ItemProduto(35.00,02,produto);
        itens.add(itemProduto);
        Compra compra = new Compra(new Date(2018,06,20),itens);
        int resultado = CompraDAO.create(compra,cliente,cartao,supermercado);
        compra = new Compra(resultado, compra.getDataHora());
        
        Supermercado result = SupermercadoDAO.readSupermercadoByCompra(compra);
        System.out.println(result);

    }

    /**
     * Test of readSupermercadoByFornecedor method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByFornecedor() throws Exception {

        System.out.println("readSupermercadoByFornecedor");

        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 76, "Rua Xablau");
        fornecedor = new Fornecedor("35.415.363/0001-72", "EPA", endereco);
        int idF = PessoaJuridicaDAO.create(fornecedor);
        fornecedor = new Fornecedor(fornecedor.getCnpj(), idF,fornecedor.getNome(), endereco);

        SupermercadoDAO.addFornecedor(fornecedor,supermercado);
        
        produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        int idProd = ProdutoDAO.create(produto, supermercado);
        produto = new Produto(idProd, produto.getCodigo(), produto.getCusto() , produto.getDescricao(), produto.getMarca() , 
                produto.getNome(), produto.getPrecoVenda(), produto.getQtdPrateleira(), produto.getQtdEstoque(), produto.getTipo());

        
        lote = new Lote(new Date(2018,06,20), new Date(2018,02,11),new Date(2019,02,11), 100,"Fralda XG",produto);
        int idLote = LoteDAO.create(lote,fornecedor,produto,supermercado);
        lote = new Lote(idLote,lote.getDataCompra(),lote.getDataFabricacao(),lote.getDataValidade(),lote.getNumUnidades(),lote.getIdentificador(), lote.getProduto());


        List<Supermercado> result = SupermercadoDAO.readSupermercadoByFornecedor(fornecedor);
        System.out.println(result);

    }

    /**
     * Test of readSupermercadoByLote method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByLote() throws Exception {
        System.out.println("readSupermercadoByLote");
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 76, "Rua Xablau");
        fornecedor = new Fornecedor("35.415.363/0001-72", "EPA", endereco);
        int idF = PessoaJuridicaDAO.create(fornecedor);
        fornecedor = new Fornecedor(fornecedor.getCnpj(), idF,fornecedor.getNome(), endereco);

        SupermercadoDAO.addFornecedor(fornecedor,supermercado);
        
        produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        int idProd = ProdutoDAO.create(produto, supermercado);
        produto = new Produto(idProd, produto.getCodigo(), produto.getCusto() , produto.getDescricao(), produto.getMarca() , 
                produto.getNome(), produto.getPrecoVenda(), produto.getQtdPrateleira(), produto.getQtdEstoque(), produto.getTipo());

        
        lote = new Lote(new Date(2018,06,20), new Date(2018,02,11),new Date(2019,02,11), 100,"Fralda XG",produto);
        int idLote = LoteDAO.create(lote,fornecedor,produto,supermercado);
        lote = new Lote(idLote,lote.getDataCompra(),lote.getDataFabricacao(),lote.getDataValidade(),lote.getNumUnidades(),lote.getIdentificador(), lote.getProduto());


        Supermercado result = SupermercadoDAO.readSupermercadoByLote(lote);
        System.out.println(result);

    }

    /**
     * Test of readSupermercadoByProduto method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByProduto() throws Exception {
        System.out.println("readSupermercadoByProduto");
           
          produto = new Produto("0000", 20.00,"Premium care", "Pampers","Fralda XG", 35.00, 30, 40, "fralda");
        int idProd = ProdutoDAO.create(produto, supermercado);
        produto = new Produto(idProd, produto.getCodigo(), produto.getCusto() , produto.getDescricao(), produto.getMarca() , 
                produto.getNome(), produto.getPrecoVenda(), produto.getQtdPrateleira(), produto.getQtdEstoque(), produto.getTipo());

        Supermercado result = SupermercadoDAO.readSupermercadoByProduto(produto);

        System.out.println(result);
    }
    
}
