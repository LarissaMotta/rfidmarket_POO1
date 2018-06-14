package util.rand;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import database.usuarios.ClienteDAO;
import modelo.usuarios.Cliente;
import modelo.usuarios.PessoaFisica;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;

public class randCartao {

    String titular;
    String bandeira;
    String numero;
    String tipo;
    String validade;

    public static String getNumero() {

        Random gerador = new Random();
        ArrayList<Integer> inteiros = new ArrayList<>();

        //imprime sequência de 10 números inteiros aleatórios
        for (int i = 0; i < 12; i++) {
            inteiros.add(gerador.nextInt(10));
        }

        return inteiros.toString().replaceAll("\\[|]|,|\\s", "");
    }

    public static void main(String[] args) throws IOException {
        String path = "/media/arq/IF/4p/POO1//trab_integrado/rfidmarket_POO1/src/util/rand/";
        String nome_arq = "pessoas_fisicas_nomes.txt";
        System.out.println(randCore.getRandLinha(path + nome_arq));
    }
}
