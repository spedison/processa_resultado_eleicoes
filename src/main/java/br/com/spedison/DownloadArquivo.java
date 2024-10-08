package br.com.spedison;

import br.com.spedison.vo.Municipio;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadArquivo {

    public static boolean processa(Municipio m)  {

        try {
            System.out.println("Baixando " + m.getNome());
            URL url1 = new URL(DadosEstruturais.url1.formatted(m.getId()));
            File out = Paths.get(DadosEstruturais.diretorioSaida, "download%3d.json".formatted(m.getId())).toFile();
            FileOutputStream fos = new FileOutputStream(out);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url1.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                fos.write(inputLine.getBytes(StandardCharsets.UTF_8));

            in.close();
            fos.close();
            return true;
        } catch (MalformedURLException e) {
            System.err.println("Problemas de formartação do da URL " + m);
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.err.println("Problemas gravação do arquivo " + m);
            e.printStackTrace();
            return false;
        }

    }

    public static void downloadAll() throws IOException {

        Files
                .readAllLines(Path.of(".", "dados", "municipios.txt"))
                .stream()
                .map(Municipio::new)
                .forEach(DownloadArquivo::processa);

    }
}
