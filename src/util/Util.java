/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            throw new IllegalArgumentException(nomeParametro + "não informado (vazio)");
    }
    
    public static boolean isCpfValido(String cpf) {

        char charCpf;
        int contDigit = 0;

        Util.verificaStringNullVazia(cpf,"CPF");

        //Garanta que só haverá dígitos, '.' ou '-';
        String expression = "^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cpf);

        if (!matcher.matches()) return false;
        
        String digitos;
        int multip = 10;
        int somaDigitos = 0;
        digitos = cpf.replace(".","").replace("-","");

        //Primeira verificação:
        for (int i = 0; i < 9; i++) {
            somaDigitos += multip * Character.getNumericValue(digitos.charAt(i));
            multip--;
        }

        //A soma final deve ser igual ao primeiro digito verificador;
        if (somaDigitos * 10 % 11 != Character.getNumericValue(digitos.charAt(9)))
            return false;

        //Segunda verificação:
        multip = 11;
        somaDigitos = 0;

        for (int i = 0; i < 10; i++) {
            somaDigitos += multip * Character.getNumericValue(digitos.charAt(i));
            multip--;
        }

        //A soma final agora deve ser igual ao segundo digito verificador;
        return somaDigitos * 10 % 11 == Character.getNumericValue(digitos.charAt(10));
    }
    
    public static boolean isLoginValido(String login){ // verifica se o login pode ser usado
        verificaStringNullVazia(login, "Login");
        
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
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
        verificaStringNullVazia(cnpj, "CNPJ");
        
        //Garanta que só haverá dígitos, '.' ou '-';
        String expression = "^[0-9]{2}.?[0-9]{3}.?[0-9]{3}/?[0-9]{4}-?[0-9]{2}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cnpj);
        
        if (!matcher.matches()) return false;
        
        int somaDigitos;
        int resultado;

        // Na primeira etapa serão verificados os 12 primeiros dígitos;
        int numDigitos = 12;

        // Peso que multip. cada dig. do cnpj, menor val. é 2, maior 9, inicia c/ 5;
        int pesoFixo = 5;

        // Pegue os dígitos que estão depois do traço, os dígitos verificadores;
        String digVerif = cnpj.split("-")[1];

        // Remova todos chars que não são dígitos;
        String digitos = cnpj.replace(".", "").replace("-", "").replace("/", "");

        // Para cada dígito verificador;
        for (int i = 0; i < digVerif.length(); i++){
            somaDigitos = 0;

            // Guarde na soma a multiplicação (peso * dígito comum [j]);
            for(int j = 0, pesoLocal = pesoFixo; j < numDigitos; j++){
                somaDigitos += pesoLocal * Character.getNumericValue(digitos.charAt(j));

                //Se pesoLocal for 2, reinicie-o para 9, senão, decremente-o;
                pesoLocal = (pesoLocal == 2) ? 9 : --pesoLocal;
            }

            // O resto da (soma * 10) por 11 deve ser igual ao digito verificador;
            resultado = somaDigitos * 10 % 11;

            if (resultado != Character.getNumericValue(digVerif.charAt(i)))
                return false;

            //Se peso for menor que 9, incremente-o, senão, defina-o como 2;
            pesoFixo = (pesoFixo < 9) ? ++pesoFixo : 2;

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
