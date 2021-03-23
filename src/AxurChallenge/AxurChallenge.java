package AxurChallenge;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.lang.model.SourceVersion;

public class AxurChallenge {
    
    public void getPage(URL url, File file) throws IOException {
        BufferedReader in
                = new BufferedReader(new InputStreamReader(url.openStream()));

        BufferedWriter out = new BufferedWriter(new FileWriter(file));

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            out.write(inputLine.toLowerCase());
            out.newLine();
        }

        in.close();
        out.flush();
        out.close();
    }

    public static void verificacao(File file) {
        BufferedReader b = null;
        try {
            List<String> palavra = new ArrayList<String>();
            palavra.add("black friday");
            palavra.add("blackfriday");
            palavra.add("promoção");
            palavra.add("promocao");
            palavra.add("senha");
            palavra.add("password");

            StringBuffer textoDoArquivo = new StringBuffer();
            String linha = null;
            b = new BufferedReader(new FileReader(file));
            try {
                while ((linha = b.readLine()) != null) {
                    textoDoArquivo.append(linha);
                }
            } catch (IOException ex) {
                System.out.println("IOException");
            }
            int size = palavra.size();
            int numeroDeOcorrencias = 0;
            int i = 0;
            do {
                Matcher m = Pattern.compile(palavra.get(i)).matcher(textoDoArquivo.toString().toLowerCase().trim());
                while (m.find()) {
                    numeroDeOcorrencias++;
                }
                i++;
                if (i == size && numeroDeOcorrencias == 0) {
                    System.out.println("safe");
                    System.exit(0);
                }
            } while (numeroDeOcorrencias == 0);
            if (numeroDeOcorrencias > 1) {
                System.out.println("suspicious");
            } else {
                System.out.println("safe");
            }
            b.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não existe");
        } catch (IOException ex) {
            System.out.println("IOExcepition");
        } finally {
            try {
                b.close();
            } catch (IOException ex) {
                System.out.println("IOExcepition");
            }
        }
    }

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        try {
            URL url = null;
            File file = new File("..\\page.html");
            System.out.println(args[0]);
            try {
                url = new URL(args[0]);
                new AxurChallenge().getPage(url, file);
                verificacao(file);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Faltam argumentos para execução do codigo");
        }
    }
}
