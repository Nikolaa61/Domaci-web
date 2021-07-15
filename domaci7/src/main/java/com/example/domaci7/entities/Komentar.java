package com.example.domaci7.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Komentar implements Serializable {
    @NotNull(message = "Autor field is required")
    @NotEmpty(message = "Autor field is required")
    String autor;
    @NotNull(message = "Tekst field is required")
    @NotEmpty(message = "Tekst field is required")
    String tekst;
    Integer idPosta;

    public Komentar() {
    }

    public Komentar(String autor, String tekst, Integer idPosta) {
        this.autor = autor;
        this.tekst = tekst;
        this.idPosta = idPosta;
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

    public Integer getIdPosta() {
        return idPosta;
    }

    public void setIdPosta(Integer idPosta) {
        this.idPosta = idPosta;
    }


}
