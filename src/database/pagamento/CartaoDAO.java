package database.pagamento;

import database.core.CoreDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import modelo.pagamento.Cartao;
import modelo.pagamento.Cartao.Tipo;
import modelo.usuarios.Cliente;

public abstract class CartaoDAO extends CoreDAO {

    public static int create(Cartao cartao) throws ClassNotFoundException, SQLException {

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string básica de sql;
        String sql = "INSERT INTO cartao" +
                "(nome_titular, validade, bandeira, numero, tipo)" +
                " VALUES (?, ?, ?, ?, ?)";

        PreparedStatement st = conexao.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);

        // Defina os valores que ocuparão as '?' na ordem acima;
        st.setString(1, cartao.getTitular());
        st.setDate(2, new Date(cartao.getDataValid().getTime()));
        st.setString(3, cartao.getBandeira());
        st.setString(4, cartao.getNumero());
        st.setString(5, String.valueOf(cartao.getTipo().toChar()));

        // Execute o INSERT e receba o ID do cartão cadastrado no BD;
        st.executeUpdate();
        int id = getIdAtCreate(st);

        st.close();
        conexao.close();

        return id;
    }

    /**
     * Retorna um conjunto de cartões relacionados a um cliente;
     * @param cliente cliente usuário do(s) cartão(ões);
     * @return Conjunto de cartões relacionados ao cliente;
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static List<Cartao> readCartoesByCliente(Cliente cliente) throws SQLException, ClassNotFoundException {

        List<Cartao> cartoes = new ArrayList<>();

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT c.id, c.nome_titular, c.validade, c.bandeira, c.numero," +
                "c.tipo FROM utiliza AS ut INNER JOIN cartao AS c" +
                "ON ut.fk_cartao = c.id WHERE ut.fk_cliente = ?";

        PreparedStatement st = conexao.prepareStatement (sql);
        st.setInt(1, cliente.getId());
        
        ResultSet rs = st.executeQuery();

        // Enquanto houver algum cartão resultado da busca;
        while (rs.next()) {

            int id = rs.getInt("id");
            String bandeira = rs.getString("bandeira");
            Date dataValid = rs.getDate("validade");
            String numero = rs.getString("numero");
            String titular = rs.getString("nome_titular");
            char tipo = rs.getString("tipo").charAt(0);
            
            Tipo type;
            if (tipo == 'C') type = Tipo.CREDITO;
            else type = Tipo.DEBITO;

            cartoes.add(new Cartao(id, bandeira, dataValid, numero, titular, type));
        }

        st.close();
        conexao.close();

        return cartoes;
    }
}


/*
public static void main(String args[]) {
      //-- Qual o meio de pagamento mais utilizado?
        // criacao do hashmap
        HashMap<Cartao, String> map3 = new HashMap<Cartao, String>();
        
        Statement st = null;
        ResultSet rs = null;
        Connection con = getConnection();
        Cartao card;
        //int id, String bandeira, Date dataValid, String numero, String titular, Tipo tipo) 
        try{
            st = con.createStatement();
            rs = st.executeQuery("select c.tipo, count(c.tipo) numero_usuarios  from hist_compra inner join cartao c on c.id = hist_compra.id where hist_compra.fk_supermercado = 101 and hist_compra.timestamp >= '14-06-2018' and hist_compra.timestamp <= '17-06-2018' group by (c.tipo) ");
            while(rs.next()){
               //nome,preco,codigo,descricao,custo,id,estoque, tipo, quant_pratelereira, marca,fk_supermercado
                
                int id = rs.getInt("id");
                Date dataValid = rs.getDate("validade");
                String bandeira = rs.getString("bandeira");
                String numero = rs.getString("numero");
                String titular = rs.getString("titular");
                String tipo = rs.getString("tipo");
                //int id, String codigo, double custo, String descricao, String marca, String nome, double precoVenda, int qtdPrateleira, int qtdEstoque, String tipo)
                
                card = new Cartao(id,dataValid,bandeira,numero,titular,tipo);
                
         
                map3.put(card,tipo);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
   
        }


    public static void main(String args[]) {
     // --Quais meio de pagamento mais lucrativo
        // criacao do hashmap
        HashMap<Cartao, String> map4 = new HashMap<Cartao, String>();
        
        Statement st = null;
        ResultSet rs = null;
        Connection con = getConnection();
        Cartao card;
        //int id, String bandeira, Date dataValid, String numero, String titular, Tipo tipo) 
        try{
            st = con.createStatement();
            rs = st.executeQuery(" select c.tipo, sum(com.preco_compra) lucro  from cartao c inner join hist_compra on hist_compra.fk_cartao = c.id inner join compra com on com.fk_hist_compra = hist_compra.id where hist_compra.fk_supermercado = 101 and hist_compra.timestamp >= '14-06-2018' and hist_compra.timestamp <= '17-06-2018' group by (c.tipo)");
            while(rs.next()){
               //nome,preco,codigo,descricao,custo,id,estoque, tipo, quant_pratelereira, marca,fk_supermercado
                
                int id = rs.getInt("id");
                Date dataValid = rs.getDate("validade");
                String bandeira = rs.getString("bandeira");
                String numero = rs.getString("numero");
                String titular = rs.getString("titular");
                String tipo = rs.getString("tipo");
                //int id, String codigo, double custo, String descricao, String marca, String nome, double precoVenda, int qtdPrateleira, int qtdEstoque, String tipo)
                
                card = new Cartao(id,dataValid,bandeira,numero,titular,tipo);
                
         
                map4.put(card,tipo);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
   
        }
*/




