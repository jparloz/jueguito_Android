package com.jparral.shortdamgames10.entities;

public class Player {
    private int id,score,level;
    private String name;

    public Player(){
    }
    public Player(int id, int score, int level, String name)
    {
        this.id=id;
        this.score=score;
        this.level=level;
        this.name=name;
    }


    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
