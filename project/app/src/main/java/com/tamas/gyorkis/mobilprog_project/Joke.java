package com.tamas.gyorkis.mobilprog_project;

public class Joke {

    private String title;
    private String text;

    public Joke(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Joke() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
