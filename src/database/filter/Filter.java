/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.filter;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author joel-
 */
public class Filter {
    private Map<String,Clause> mapClauses;

    public Filter() {
        mapClauses = new HashMap<>();
    }
    
    public void addClause(String campo,Clause clause){
        mapClauses.put(campo, clause);
    }
    
    public String getFilter(){
        String filterStr = "";
        
        int i = 0;
        for (String campo : mapClauses.keySet()){
            String clauseStr = mapClauses.get(campo).getClause();
            
            if (clauseStr != null){
                filterStr += "AND ";
                filterStr += campo + clauseStr + " ";
            }
            i++;
        }
        
        return filterStr;
    }
    
}
