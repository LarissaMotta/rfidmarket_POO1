package util.rand;

import java.io.*;

public class RandObjects {

    private static String getString(String arqName)
            throws IOException {

        FileReader fr = null;
        LineNumberReader lnr;

        try {
            fr = new FileReader(arqName);
            lnr = new LineNumberReader(fr);
            lnr.skip(Long.MAX_VALUE);
            long totalLinhas = lnr.getLineNumber();

            System.out.println("\nLINHAS: " + totalLinhas);
        }

        finally {
            if (fr != null) fr.close();
        }

        return "";
    }

    public static void main() throws IOException {

        getString("/home/x/Área de Trabalho/POO1/trab_integrado/rfidmarket_POO1/src/rand/cart_bandeiras.txt");
    }
}
