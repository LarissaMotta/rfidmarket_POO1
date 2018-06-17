/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author 20162bsi0511
 */
public abstract class Util {
    
    // faz verificação e caso seja true já lança execeção
    public static void verificaIsObjNull(Object obj, String nomeParametro) throws IllegalArgumentException{
        if (obj == null) 
            throw new IllegalArgumentException(nomeParametro + " inválido (nulo)!");
    }
    
    public static void verificaID(int id) throws IllegalArgumentException{
        if (id <= 0)
            throw new IllegalArgumentException("ID inválido: menor que 1!");
    }
    
    public static void verificaStringNullVazia(String string, String nomeParametro) throws IllegalArgumentException{
        verificaIsObjNull(string, nomeParametro);
        if (string.trim().isEmpty())
            throw new IllegalArgumentException(nomeParametro + " não informado (vazio)");
    }
    
    public static boolean isIntervalValid(Date dataMin, Date dataMax){
        if (dataMin == null || dataMax == null) return true;
        else if (dataMin.before(dataMax) || dataMin.equals(dataMax)) return true;
        else return false;
    }
    
    public static boolean isCpfValido(String cpf) {

        //Garanta que só haverá dígitos, '.' ou '-';
        String expression = "^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cpf);

        if (!matcher.matches()) return false;
        
        String digitos;
        int multip = 10;
        int somaDigitos = 0;
        int resto;
        int digVerificador;
        digitos = cpf.replace(".","").replace("-","");

        //Primeira verificação:
        for (int i = 0; i < 9; i++) {
            somaDigitos += multip * Character.getNumericValue(digitos.charAt(i));
            multip--;
        }

        resto = somaDigitos % 11;
        digVerificador = resto < 2 ? 0 : 11 - resto;

        //Se o digVerificador calculador for igual ao primeiro digito verificador;
        if (digVerificador != Character.getNumericValue(digitos.charAt(9)))
            return false;

        //Segunda verificação:
        multip = 11;
        somaDigitos = 0;

        for (int i = 0; i < 10; i++) {
            somaDigitos += multip * Character.getNumericValue(digitos.charAt(i));
            multip--;
        }

        resto = somaDigitos % 11;
        digVerificador = resto < 2 ? 0 : 11 - resto;

        //Se o digVerificador calculador for igual ao segundo digito verificador;
        return digVerificador == Character.getNumericValue(digitos.charAt(10));
    }
    
    public static boolean isLoginValido(String login){ // verifica se o login pode ser usado
        String expression = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(login);
        return matcher.matches();
    }
    
    /**
     * Verifica a integridade do CPNJ (padrão brasileiro), indicando possíveis
     * erros de digitação ou fraude;
     * @param cnpj string no formato Brasil (XX.XXX.XXX/XXXX-XX)
     * @return true se o cnpj for válido, senão, false;
     */
    public static boolean isCnpjValido(String cnpj) {
        //Garanta que só haverá dígitos, '.' ou '-';
        String expression = "^[0-9]{2}.?[0-9]{3}.?[0-9]{3}/?[0-9]{4}-?[0-9]{2}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cnpj);
        
        if (!matcher.matches()) return false;
        
        int somaDigitos;
        int digVerificador;
        int peso;
        int resto;

        // Na primeira etapa serão verificados os 12 primeiros dígitos;
        int numDigitos = 11;

        // Pegue os dígitos que estão depois do traço, os dígitos verificadores;
        String digVerif = cnpj.split("-")[1];

        // Remova todos chars que não são dígitos;
        String digitos = cnpj.replace(".", "").replace("-", "").replace("/", "");

        // Para cada dígito verificador;
        for (int i = 0; i < digVerif.length(); i++){
            somaDigitos = 0;
            peso = 2;

            // Guarde na soma a multiplicação (peso * dígito comum [j]);
            for(int j = numDigitos; j >= 0; j--){
                somaDigitos += peso * Character.getNumericValue(digitos.charAt(j));

                //Se peso chegar como 9, reinicie-o para 2, senão, incremente-o;
                peso = (peso == 9) ? 2 : ++peso;
            }

            // Calcule o resto da soma por 11;
            resto = somaDigitos % 11;
            digVerificador = resto < 2 ? 0 : (11 - resto);

            //Se o digVerificador calculador for igual ao verificador do cnpj de entrada;
            if (digVerificador != Character.getNumericValue(digVerif.charAt(i)))
                return false;

            //Incremente o número de digitos a serem verificados;
            numDigitos++;
        }

        return true;
    }
    
    public static String criptografar(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
        byte messageDigest[] = algorithm.digest(string.getBytes("UTF-8"));
        
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
          hexString.append(String.format("%02X", 0xFF & b));
        }
        
        return hexString.toString();
    }
}
