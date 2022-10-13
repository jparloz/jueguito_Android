package com.jparral.shortdamgames10.entities;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    Deck deck;
    List<Card> playerHand;
    List<Card> dealerHand;


    public Dealer(){
    }

    public void startAttributes(){
        deck = new Deck();
        deck.shuffle();
        Log.d("Pimero",deck.toString());
        playerHand = new ArrayList<Card>();
        dealerHand = new ArrayList<Card>();
        for (int i = 0; i < 2 ;i++ ){
            playerHand.add(deck.getNextCard());
            delCard();
            //deck.deleteFirstCard();
            dealerHand.add(deck.getNextCard());
            delCard();
            //deck.deleteFirstCard();
            Log.d("Pimero",playerHand.get(i).toString() + " " +dealerHand.get(i).toString());
        }
    }

    public void setCardPlayer(){
        playerHand.add(deck.getNextCard());
        delCard();
    }
    public void setCardDealer(){
        dealerHand.add(deck.getNextCard());
        delCard();
    }

    public void delCard(){
        deck.deleteFirstCard();
    }

    public int total_bill(ArrayList<Card> cards){
        int bill = 0;

        for (Card i: cards){
            bill = bill + i.getValue();
        }
        return bill;
    }



    public ArrayList<Card> getPlayerHand(){
        return (ArrayList<Card>) playerHand;
    }

    public ArrayList<Card> getDealerHand(){
        return (ArrayList<Card>) dealerHand;
    }






}
