package com.example.demo.model;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Anotacoes")
public class Anotacoes {
    
    private String id;
    private String titulo;
    private String conteudo;

    public Anotacoes() {
    }

    public Anotacoes (String id, String titulo, String conteudo) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

}
