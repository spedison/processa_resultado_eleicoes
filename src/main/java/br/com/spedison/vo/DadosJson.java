package br.com.spedison.vo;

public class DadosJson {

    private final String municipio;
    private final String nomeArquivo;
    private final String dadosArquivo;
    private final String url;
    private final Integer idMunicipio;

    public DadosJson(String nomeArquivo, String dadosArquivo, String url, String municipio, Integer idMunicipio) {
        this.nomeArquivo = nomeArquivo;
        this.dadosArquivo = dadosArquivo;
        this.url = url;
        this.municipio = municipio;
        this.idMunicipio = idMunicipio;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getDadosArquivo() {
        return dadosArquivo;
    }

    public String getUrl() {
        return url;
    }

    public String getMunicipio() {
        return municipio;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }
}
