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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        int idSupermercado = SupermercadoDAO.create(supermercado);
        
        supermercado = new Supermercado(idSupermercado,supermercado.getLatitude(),supermercado.getLongitude(),supermercado.getUnidade(),supermercado.getCnpj(), supermercado.getNome(), endereco);
        System.out.println("idSupermercado = "+idSupermercado);
        
        funcionario = new Funcionario("estagiario", "atendente","216.856.707-76", new Date("2018/05/28"), Funcionario.Genero.M, "joel_tendencia@hotmail.com", "testedesenha", "Joel", endereco);
        int idFuncionario = FuncionarioDAO.create(funcionario, supermercado);
        
        funcionario = new Funcionario(funcionario.getCargo(), funcionario.getSetor(), funcionario.getCpf(), funcionario.getDataNasc(), funcionario.getGenero(), funcionario.getLogin(), funcionario.getSenha(), idFuncionario, funcionario.getNome(),funcionario.getEndereco());
        System.out.println("idFuncionario = "+idFuncionario);
        
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
     * Test of readSupermercadoByFuncionario method, of class SupermercadoDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testReadSupermercadoByFuncionario() throws Exception {
        System.out.println("readSupermercadoByFuncionario");

        //Funcionario funcionario
        Supermercado result = SupermercadoDAO.readSupermercadoByFuncionario(funcionario);
    }
    
    /**
     * Test of readSupermercadoByCompra method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByCompra() throws Exception {
        System.out.println("readSupermercadoByCompra");
        
        Endereco endereco = new Endereco("Jacaraípe", "29177-486", "SERRA", Endereco.Estado.ES, 75, "Rua Xablau");
        
        Cliente cliente = new Cliente("216.856.707-76", new Date(), PessoaFisica.Genero.M, "joel@hotmail.com", "testedesenha", "Joel", endereco);
        int idCliente = ClienteDAO.create(cliente);
        
        cliente = new Cliente(cliente.getCpf(), cliente.getDataNasc(), cliente.getGenero(), cliente.getLogin(), cliente.getSenha(),idCliente, cliente.getNome(), endereco);
        System.out.println("idCliente = "+idCliente);
        
        Cartao cartao = new Cartao("MasterCard", new  java.util.Date(2019, 8, 1), "5482657412589634", "Maria", Cartao.Tipo.CREDITO);
        int idCartao = CartaoDAO.create(cartao);
        
        cartao = new Cartao(idCartao, cartao.getBandeira(), cartao.getDataValid(), cartao.getNumero(), cartao.getTitular(), cartao.getTipo());
        System.out.println("idCartao = "+idCartao);
        
        ClienteDAO.addCartao(cliente, cartao);
        
        ItemProduto itemProduto = new ItemProduto(35.00,02,produto);
        
        List<ItemProduto> itens = new ArrayList<>();
        itens.add(itemProduto);
        
        Compra compra = new Compra(new Date(2018,06,20),itens);
        int idCompra = CompraDAO.create(compra,cliente,cartao,supermercado);
        
        compra = new Compra(idCompra, compra.getDataHora());
        
        Supermercado result = SupermercadoDAO.readSupermercadoByCompra(compra);

    }

    /**
     * Test of readSupermercadoByFornecedor method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByFornecedor() throws Exception {
        System.out.println("readSupermercadoByFornecedor");

        List<Supermercado> result = SupermercadoDAO.readSupermercadoByFornecedor(fornecedor);
    }

    /**
     * Test of readSupermercadoByLote method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByLote() throws Exception {
        System.out.println("readSupermercadoByLote");

        Supermercado result = SupermercadoDAO.readSupermercadoByLote(lote);
    }

    /**
     * Test of readSupermercadoByProduto method, of class SupermercadoDAO.
     */
    @Test
    public void testReadSupermercadoByProduto() throws Exception {
        System.out.println("readSupermercadoByProduto");
           
        Supermercado result = SupermercadoDAO.readSupermercadoByProduto(produto);
    }
}
