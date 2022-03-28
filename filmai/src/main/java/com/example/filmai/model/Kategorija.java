package com.example.filmai.model;

public class Kategorija {
    private String kategorija;
    private int id;

    public Kategorija(int id,String kategorija) {
        this.kategorija = kategorija;
        this.id=id;
    }
    public Kategorija(String kategorija){
        this.kategorija=kategorija;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Kategorija{" +
                "kategorija='" + kategorija + '\'' +
                ", id=" + id +
                '}';
    }
}
