package br.com.spedison;

import br.com.spedison.vo.Candidato;
import br.com.spedison.vo.DadosJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static final List<Candidato> allCandids = new ArrayList<>();

    static class RodaProcessa implements Consumer<DadosJson> {
        @Override
        public void accept(DadosJson s) {
            ParserJson p = new ParserJson(s);
            List<Candidato> k = p.fromJsonString(s.getDadosArquivo());
            allCandids.addAll(k);
        }

    }

    public static void main(String[] args) {

        try {
            DownloadArquivo dw = new DownloadArquivo();
            dw.setProcessadorJson(new RodaProcessa());
            dw.downloadAll();

            allCandids.forEach(System.out::println);
            long quantidadeDeNulos =
            allCandids.
                    stream().
                    filter(p-> Objects.isNull(p.getQuantidadeVotos())).
                            count();
            long total = allCandids.size();

            System.out.println("Temos %d com %d nulos".formatted(total, quantidadeDeNulos));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}