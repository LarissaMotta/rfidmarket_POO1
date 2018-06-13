package util.rand;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class randCore {

    /**
     * Retorna uma linha de número aleatório de um arquivo;
     * @param nomeArq Nome do arquivo com os dados;
     * @return
     */

    public static String getRandLinha(String nomeArq)
            throws IOException {

        BufferedReader br = null;
        FileReader fr = null;
        String linha;
        Random aleat = new Random();

        fr = new FileReader(nomeArq);
        br = new BufferedReader(fr);

        int total_linhas = Integer.parseInt(br.readLine().trim());
        int num_aleat = aleat.nextInt(total_linhas);
        int cont = 0;

        while ((linha = br.readLine()) != null && cont < num_aleat) {
            cont++;
        }

        br.close();
        fr.close();

        return linha;
    }
}
