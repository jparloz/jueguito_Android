package com.jparral.shortdamgames10.entities;

public class Game {

    private int id;
    private String name;
    private String author;
    private String packageName;
    private int playersNumber;
    private int level;

    public Game(int id, String name, String author, String packageName, int playersNumber,int level){
        this.id=id;
        this.author=author;
        this.name=name;
        this.packageName=packageName;
        this.playersNumber=playersNumber;
        this.level=level;
    }
    public Game(){

    }

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
