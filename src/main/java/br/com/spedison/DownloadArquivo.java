package br.com.spedison;

import br.com.spedison.vo.DadosJson;
import br.com.spedison.vo.Municipio;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class DownloadArquivo {

    private Consumer<DadosJson> processadorJson;

    public boolean processa(Municipio m) {
        return processa(m, null);
    }

    public void setProcessadorJson(Consumer<DadosJson> processadorJson) {
        this.processadorJson = processadorJson;
    }

    public boolean processa(Municipio m, Consumer<DadosJson> consumer) {

        try {
            System.out.println("Baixando " + m.getNome());

            URL url1 = new URL(DadosEstruturais.url1.formatted(m.getId()));
            File out = Paths.get(DadosEstruturais.diretorioSaida, "download%3d.json".formatted(m.getId())).toFile();
            FileOutputStream fos = new FileOutputStream(out);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url1.openStream()));

            String inputLine;
            StringBuilder allData = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                fos.write(inputLine.getBytes(StandardCharsets.UTF_8));
                allData.append(inputLine);
            }

            in.close();
            fos.close();

            if (consumer != null)
                consumer.accept(new DadosJson(out.getName(), allData.toString(), url1.toString(), m.getNome(), m.getId()));

            if (this.processadorJson != null)
                processadorJson.accept(new DadosJson(out.getName(), allData.toString(), url1.toString(), m.getNome(), m.getId()));

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

    public void downloadAll() throws IOException {

        Files
                .readAllLines(Path.of(".", "dados", "municipios.txt"))
                .stream()
                .map(Municipio::new)
                .forEach(this::processa);

    }
}
