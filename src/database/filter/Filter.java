/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.filter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joel-
 */
public class Filter {
    private final List<Clause> clauses;

    public Filter() {
        clauses = new ArrayList<>();
    }
    
    public void addClause(Clause clause){
        clauses.add(clause);
    }
    
    public String getFilter(){
        String filterStr = "";
        
        int i = 0;
        for (Clause clause : clauses){
            String clauseStr = clause.getClause();
            
            if (clauseStr != null){
                filterStr += "AND ";
                filterStr += clauseStr + " ";
            }

            i++;
        }
        
        return filterStr;
    }
    
}
