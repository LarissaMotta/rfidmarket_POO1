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
 * Esta classe ajuda a criar query dinamicas, controlando os filtros que devem ou não ser adicionados a query
 * Principal metodo é o getFilter que retorna uma string com a clausula
 * A clausulas são concatenas com AND, sendo que somente as que retornarem != null no getClause serão adicionadas a string
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
