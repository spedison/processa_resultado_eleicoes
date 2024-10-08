package br.com.spedison.vo;

public class Municipio {
    private int id;
    private String nome;

    public Municipio(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Municipio(String linha){
        String[] dados = linha.split(";");
        this.id = Integer.parseInt(dados[0]);
        this.nome = dados[1];
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Municipio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
