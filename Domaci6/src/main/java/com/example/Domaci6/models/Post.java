package com.example.Domaci6.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Post implements Serializable {
    private String autor;
    private String title;
    private String content;
    private GregorianCalendar date;
    private int id;

    private List<Komentar> komentari = new ArrayList<>();

    public Post() {
    }

    public Post(String autor, String title, String content) {
        this.autor = autor;
        this.title = title;
        this.content = content;
        this.date = new GregorianCalendar();
    }

    public Post(String autor, String title, String content, GregorianCalendar date, int id) {
        this.autor = autor;
        this.title = title;
        this.content = content;
        this.date = date;
        this.id = id;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setKomentari(List<Komentar> komentari) {
        this.komentari = komentari;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Komentar> getKomentari() {
        return komentari;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
