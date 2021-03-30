package com.example.rememberourstory;

public class Heros {
    private String name;
    private String urlSource;

    @Override
    public String toString() {
        return "Heros{" +
                "name='" + name + '\'' +
                ", urlSource='" + urlSource + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlSource() {
        return urlSource;
    }

    public void setUrlSource(String urlSource) {
        this.urlSource = urlSource;
    }

    public Heros(String name, String urlSource) {
        this.name = name;
        this.urlSource = urlSource;
    }
}
