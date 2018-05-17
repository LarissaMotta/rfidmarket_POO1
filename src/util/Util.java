/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author 20162bsi0511
 */
public abstract class Util {
    
    // faz verificação e caso seja true já lança execeção
    public static void verificaIsObjNull(Object obj) throws IllegalArgumentException{
        if (obj == null) 
            throw new IllegalArgumentException(
                    obj.getClass().getSimpleName() + " é nulo(a)!");
    }
    
    
    public static void verificaID(int id) throws IllegalArgumentException{
        if (id <= 0)
            throw new IllegalArgumentException("ID inválido: menor que 1!");
    }
    
    public static void verificaStringNullVazia(String string) throws IllegalArgumentException{
        if (string == null)
            throw new IllegalArgumentException("String nula!");
        else if (string.length() == 0)
            throw new IllegalArgumentException("String vazia!");
    }
    
}
