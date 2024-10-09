package br.com.spedison.vo;

public class Candidato {
    private final String nomeMunicipio;
    private final Integer idMunicipio;
    private String nome;
    private Integer quantidadeVotos;
    private String dadosJson;
    private String url ;


    public Candidato(String nome, Integer quantidadeVotos, String dadosJson, String url, String nomeMunicipio, Integer idMunicipio) {
        this.nome = nome;
        this.quantidadeVotos = quantidadeVotos;
        this.dadosJson = dadosJson;
        this.url = url;
        this.nomeMunicipio = nomeMunicipio;
        this.idMunicipio = idMunicipio;
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidadeVotos() {
        return quantidadeVotos;
    }

    public String getDadosJson() {
        return dadosJson;
    }

    public String getUrl() {
        return url;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    @Override
    public String toString() {
        return "Candidato{" +
                "nomeMunicipio='" + nomeMunicipio + '\'' +
                ", idMunicipio=" + idMunicipio +
                ", nome='" + nome + '\'' +
                ", quantidadeVotos=" + quantidadeVotos +
                ", url='" + url + '\'' +
                '}';
    }
}
