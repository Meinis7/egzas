package com.example.filmai.model;

public class Movie {
    private int id;
    private String title;
    private float IMDB;
    private String kategorija;
    private String aprasymas;
    private int user_id;

    public Movie(){
    }

    public Movie(int id, String title, float IMDB, String kategorija, String aprasymas,int user_id) {
        this.id = id;
        this.title = title;
        this.IMDB = IMDB;
        this.kategorija = kategorija;
        this.aprasymas = aprasymas;
        this.user_id = user_id;
    }

    public Movie(int id, String title, float IMDB, String kategorija, String aprasymas) {
        this.id = id;
        this.title = title;
        this.IMDB = IMDB;
        this.kategorija = kategorija;
        this.aprasymas = aprasymas;
    }

    public Movie(String title, float IMDB, String kategorija, String aprasymas, int user_id) {
        this.title = title;
        this.IMDB = IMDB;
        this.kategorija = kategorija;
        this.aprasymas = aprasymas;
        this.user_id=user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getIMDB() {
        return IMDB;
    }

    public void setIMDB(float IMDB) {
        this.IMDB = IMDB;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getAprasymas() {
        return aprasymas;
    }

    public void setAprasymas(String aprasymas) {
        this.aprasymas = aprasymas;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", IMDB=" + IMDB +
                ", kategorija='" + kategorija + '\'' +
                ", aprasymas='" + aprasymas + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
