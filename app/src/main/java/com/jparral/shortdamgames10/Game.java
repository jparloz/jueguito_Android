package com.jparral.shortdamgames10;

public class Game {

    private int id;
    private String name;
    private String author;
    private String packageName;
    private int playersNumber;
    private int level;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public int getPlayersNumber() {
        return playersNumber;
    }

    public String getAuthor() {
        return author;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setPlayersNumber(int playersNumber) {
        this.playersNumber = playersNumber;
    }
}
