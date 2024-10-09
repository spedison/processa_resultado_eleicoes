package br.com.spedison;

import br.com.spedison.vo.Candidato;
import br.com.spedison.vo.DadosJson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParserJson {

    DadosJson dadosJson;

    public ParserJson(DadosJson dadosJson) {
        this.dadosJson = dadosJson;
    }

    public List<Candidato> fromJsonString(String json) {
        DocumentContext jsonContext = JsonPath.parse(json);
        List<Candidato> result = new ArrayList<>();

        String nomeCandidato = null;
        Integer quantVotos = null;
        int cand = 0;
        try {
            while (true) {
                nomeCandidato = jsonContext.read("$.carg[0].agr[%d].par[0].cand[0].nm".formatted(cand));
                quantVotos = Integer.parseInt(jsonContext.read("$.carg[0].agr[%d].par[0].cand[0].vap".formatted(cand)));
                cand++;
                if (nomeCandidato != null) {
                    Candidato candi = new Candidato(nomeCandidato, quantVotos, dadosJson.getDadosArquivo(), dadosJson.getUrl());
                    result.add(candi);
                } else {
                    break;
                }
            }
        } catch (RuntimeException rte) {
            System.err.println("Erro durante o processsamento : cand = " + cand + " ");
            rte.printStackTrace();
        }

        return result;
    }

    public List<Candidato> fromJsonFile(String jsonFile) {
        // Implementar a leitura do JSON e retornar um Candidato
        try {
            String json = new String(Files.readAllBytes(Path.of(jsonFile)));
            return this.fromJsonString(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
