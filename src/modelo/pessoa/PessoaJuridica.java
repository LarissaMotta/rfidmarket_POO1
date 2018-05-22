package modelo.pessoa;

import util.Util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author joel-
 */
public abstract class PessoaJuridica extends Pessoa{

    // não pode ser nulo, deve ter length igual a 18 e não pode ser alterado
    private String cnpj;

    // Pode ser usada quando para instanciar a partir de dados do BD
    public PessoaJuridica(String cnpj, int id, String nome, Endereco endereco) throws IllegalArgumentException {
        super(id, nome, endereco);
        setCnpj(cnpj);
    }

    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public PessoaJuridica(String cnpj, String nome, Endereco endereco) throws IllegalArgumentException{
        super(nome, endereco);
        setCnpj(cnpj);
    }

    /**
     * Verifica a integridade do CPNJ (padrão brasileiro), indicando possíveis
     * erros de digitação ou fraude;
     * @param cnpj string no formato Brasil (XX.XXX.XXX/XXXX-XX)
     * @return true se o cnpj for válido, senão, false;
     */
    public static boolean validarCnpj(String cnpj) {

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

    public final void setCnpj(String cnpj)
            throws IllegalArgumentException{

        Util.verificaIsObjNull(cnpj);

        //Garanta que só haverá dígitos, '.' ou '-';
        String expression = "^[0-9]{2}.?[0-9]{3}.?[0-9]{3}/?[0-9]{4}-?[0-9]{2}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(cnpj);

        if (!matcher.matches() || !validarCnpj(cnpj))
            throw new IllegalArgumentException("CNPJ não é válido");

        else this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }
}
