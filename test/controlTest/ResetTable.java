/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlTest;

import static database.core.CoreDAO.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author joel-
 */
public abstract class ResetTable {
    public static void cleanTable(String table) throws ClassNotFoundException, SQLException{
        Connection conn = getConnection();
        
        String sql = "DELETE FROM " + table;
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
        
        st.close();
        conn.close();
    }
    
    public static void cleanAllTables() throws ClassNotFoundException, SQLException{
        String[] tables = 
        {"compra","lote","produto","hist_compra","utiliza","fornecimento","cartao","funcionario","fisica","supermercado","juridica","usuarios","contato"};
        
        for (String table : tables){
            cleanTable(table);
        }
    }
}
