package br.com.spedison.vo;

public class Candidato {
    private String nome;
    private Integer quantidadeVotos;
    private String dadosJson;
    private String url ;

    public Candidato(String nome, Integer quantidadeVotos, String dadosJson, String url) {
        this.nome = nome;
        this.quantidadeVotos = quantidadeVotos;
        this.dadosJson = dadosJson;
        this.url = url;
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

    @Override
    public String toString() {
        return "Candidato{" +
                "nome='" + nome + '\'' +
                ", quantidadeVotos=" + quantidadeVotos +
                ", dadosJson='" + dadosJson + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
