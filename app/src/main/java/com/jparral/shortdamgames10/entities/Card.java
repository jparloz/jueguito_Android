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
    public int getValue(){
        return value;
    }

    public String getSuit() {
        return suit;
    }
}
