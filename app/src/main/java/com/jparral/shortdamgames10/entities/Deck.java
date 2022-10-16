package com.jparral.shortdamgames10.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private int nextCardIndex=-1;

    List<Card> myCards;

    public Deck(){
        String [] suits ={"Heart","Spades","Clover","Diamond"};
        myCards=new ArrayList<Card>();
        for(int k = 0; k<suits.length;k++) {
            for(int j = 1; j<13;j++) {
                myCards.add(new Card(suits[k],j));
            }

        }
    }

    public String toString(){

        String str = "";

        for (int i = 0; i < myCards.size(); i++) {
            str +=	myCards.get(i).toString() + " ";
        }
        return str;
    }


    private void swapCards(int index1, int index2) {

        Card hold;

        hold = myCards.get(index1);
        myCards.set(index1,myCards.get(index2));
        myCards.set(index2,hold);
    }

    public void shuffle()  {
        Random rn = new Random();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < myCards.size(); j++) {
                swapCards(i, rn.nextInt(48));
            }
        }
        nextCardIndex = 0;
    }
    public Card getNextCard(){
        return myCards.get(nextCardIndex);
    }

    public void deleteFirstCard(){
        myCards.remove(0);
    }
}
