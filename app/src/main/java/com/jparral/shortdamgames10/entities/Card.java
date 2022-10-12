package com.jparral.shortdamgames10.entities;

public class Card {

    private int value;

    private String suit;

    public Card( String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public String toString() {

        return this.suit + " " + this.value;

    }
    public String getValorString(){

        String name = "";

        switch(this.value) {
            case 1:
                name = "Ace";
                break;
            case 2:
                name = "Two";
                break;
            case 3:
                name = "Three";
                break;
            case 4:
                name = "Four";
                break;
            case 5:
                name = "Five";
                break;
            case 6:
                name = "Six";
                break;
            case 7:
                name = "Seven";
                break;
            case 8:
                name = "Eight";
                break;
            case 9:
                name = "Nine";
                break;
            case 10:
                name = "Ten";
                break;
            case 11:
                name = "Jack";
                break;
            case 12:
                name = "Queen";
                break;
            case 13:
                name = "King";
                break;
        }

        return name;

    }
}
