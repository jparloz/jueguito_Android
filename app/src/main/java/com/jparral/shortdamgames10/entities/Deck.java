package com.jparral.shortdamgames10.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private int nextCardIndex;

    List<Card> myCards ;
    Card[] deck = new Card[52];

    public Deck(){
        String [] suits ={"Heart","Spades","Clover","Diamond"};
        new ArrayList<Card>();

        for(int k = 0; k<suits.length;k++) {
            for(int j = 0; j<13;j++) {
                myCards.add(new Card(suits[k],j));

            }

        }
    }

    public String toString(){

        String str = "";

        for (int i = 0; i < deck.length; i++) {
            str +=	deck[i].toString() + " ";
        }
        return str;
    }


    private void swapCards(int index1, int index2) { //cambiar cartas

        Card hold;

        hold = myCards.get(index1);
        myCards.set(index1,myCards.get(index2));
        myCards.set(index2,hold);
    }

    public void shuffle()  { //barajar
        Random rn = new Random();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < deck.length; j++) {
                swapCards(i, rn.nextInt(52));
            }
        }
        nextCardIndex = 0;
    }
}
