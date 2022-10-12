package com.jparral.shortdamgames10.entities;

import androidx.lifecycle.ViewModelProvider;

import com.jparral.shortdamgames10.viewmodel.GameViewModel;
import com.jparral.shortdamgames10.viewmodel.PlayerViewModel;

import java.util.ArrayList;

public class BlackJack {

    GameViewModel GameFragment;
    int level;

    public BlackJack(){

    }

    //comprobar
    public int comprobar(ArrayList<Card> cards){


        //-1= no llegas a 21; 0 = justo 21; 1 = te has pasado de 21
        int com = -1;
        int cuenta = 0;

        for(Card e: cards){
            cuenta = cuenta + e.getValue();
        }

        if(cuenta>21){
            com = 1;
        }else if (cuenta==21){
            com = 0;
        }
        return com;
    }

    public int comprobarDealer(ArrayList<Card> cards){

        //-1= no llegas a 21; 0 = justo 21; 1 = te has pasado de 21




        return -1;
    }

}
