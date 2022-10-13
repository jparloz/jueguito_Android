package com.jparral.shortdamgames10.entities;

import androidx.lifecycle.ViewModelProvider;

import com.jparral.shortdamgames10.viewmodel.GameViewModel;
import com.jparral.shortdamgames10.viewmodel.PlayerViewModel;

import java.util.ArrayList;

public class BlackJack {

    int level;

    public BlackJack(){

    }

    //comprobar
    public int comprobar(ArrayList<Card> cards){

        //-1= no llegas a 21; 0 = justo 21; 1 = te has pasado de 21
        int com = -1;
        int bill = 0;

        for(Card e: cards){
            bill = bill + e.getValue();
        }

        if(bill>21){
            com = 1;
        }else if (bill==21){
            com = 0;
        }
        return com;
    }

    public int comprobarDealer(ArrayList<Card> cards, int level){

        int i = level;//devolver cuenta total
        int bill = 0;
        int com = -1;

        for(Card e: cards){
            bill = bill + e.getValue();
        }

        //-1= no llegas a 21; 0 = 21 o menos; 1 = te has pasado de 21
        if (i==0){//Niveles de dificultad
            //facil
            if(bill<=14){
                //no hace nada y pide otra carta
                com = -1;
            }else if(bill==21){
                //no pide carta y devuelve 1
                com = 0;
            }else if(bill>21){
                //pierde y devuelve 1
                com = 1;
            }else{
                com = 0;
            }



        }else if(i==1){
            //normal
            if(bill<=16){
                //no hace nada y pide otra carta
                com = -1;
            }else if(bill==21){
                //no pide carta y devuelve 1
                com = 0;
            }else if(bill>21){
                //pierde y devuelve 1
                com = 1;
            }else{
                com = 0;
            }

        }else if(i==2){
            //dificil
            if(bill<=18){
                //no hace nada y pide otra carta
                com = -1;
            }else if(bill==21){
                //no pide carta y devuelve 1
                com = 0;
            }else if(bill>21){
                //pierde y devuelve 1
                com = 1;
            }else{
                com = 0;
            }
        }
        return com;
    }

    public int comparar(int player, int dealer){
        int i;

        if(player>dealer){
            i = 1;//victoria jugador
        }else if(dealer<player){
            i = -1;//victoria dealer
        }else{
            i = 0;//empate
        }


        //Llamaremos a este mñetodo desde el endfragment y comprobamos quien ha ganado
        return i;
    }



}
