package com.info.rating.model;

public class Movie {
    private int id, voters;
    private double rating;
    private String name;

    public Movie(int id, int voters, double rating, String name) {
        this.id = id;
        this.voters = voters;
        this.rating = rating;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoters() {
        return voters;
    }

    public void setVoters(int voters) {
        this.voters = voters;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
