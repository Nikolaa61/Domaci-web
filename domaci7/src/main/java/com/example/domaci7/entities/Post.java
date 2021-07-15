package com.example.domaci7.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
    @NotNull(message = "Autor field is required")
    @NotEmpty(message = "Autor field is required")
    private String autor;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String title;
    @NotNull(message = "Content field is required")
    @NotEmpty(message = "Content field is required")
    private String content;
    private Date date;
    private int id;

    public Post() {
    }

    public Post(String autor, String title, String content) {
        this.autor = autor;
        this.title = title;
        this.content = content;
        this.date = new Date();
    }

    public Post(String autor, String title, String content, Date date, int id) {
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

    public String getAutor() {
        return autor;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
