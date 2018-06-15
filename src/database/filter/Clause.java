/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.filter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author joel-
 * Esta classe auxilia a criar a string de query do sql
 * Seu principal metodo é o getClause q retorna uma string se o valor for != null
 * Ela trabala em conjunto com a Classe database.Filter para ligar as clausulas
 */
public class Clause {
    private final String campo;
    private final Object valor;
    private final byte opLog;
    
    public static final byte IGUAL = 0;
    public static final byte DIFERENTE = 1;
    public static final byte MAIOR = 2;
    public static final byte MENOR = 3;
    public static final byte MAIOR_IGUAL = 4;
    public static final byte MENOR_IGUAL = 5;
    public static final byte LIKE = 6;
    public static final byte ILIKE = 7;

    public Clause(String campo,Object valor, byte opLog) throws IllegalArgumentException{
        if (opLog < 0 || opLog > 7) throw new IllegalArgumentException("operador lógico invalido!");
        
        this.opLog = opLog;
        this.valor = valor;
        this.campo = campo;
    }
    
    public String getClause(){
        if (valor == null) return null;
        // TODO Verificar se funcionar com char
        String clause = campo + getOpLogStr();
        
        if (valor instanceof String){
            String valorStr = (String) valor;
            if (valorStr.contains("null") || valorStr.isEmpty()) return null;
            else clause += "'" + valor + "'";
        }else if (valor instanceof Character){
            clause += "'" + valor + "'";
        }else if (valor instanceof Date){
            clause += "'" + getDateValorStr() + "'";
        }else {
            clause += String.valueOf(valor);
        }
        
        return clause;
    }
    
    private String getOpLogStr(){
        
        switch (opLog) {
            case 0:
                return " = ";
            case 1:
                return " != ";
            case 2:
                return " > ";
            case 3:
                return " < ";
            case 4:
                return " >= ";
            case 5:
                return " <= ";
            case 6:
                return " LIKE ";
            case 7:
                return " ILIKE ";
        }
        
        return null;
    }
    
    private String getDateValorStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format((Date) valor);
    }
}
