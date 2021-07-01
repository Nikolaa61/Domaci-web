package com.example.Domaci6.models;

public class Komentar {
    String autor;
    String tekst;

    public Komentar() {
    }

    public Komentar(String autor, String tekst) {
        this.autor = autor;
        this.tekst = tekst;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getAutor() {
        return autor;
    }

    public String getTekst() {
        return tekst;
    }
}
