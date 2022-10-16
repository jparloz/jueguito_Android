package com.jparral.shortdamgames10.entities;

import java.util.ArrayList;

public class BlackJack {

    public BlackJack(){

    }

    //comprobar
    public int check(ArrayList<Card> cards){
        //-1= no llegas a 21; 0 = justo 21; 1 = te has pasado de 21
        int com = -1;
        int bill = 0;
        for(Card e: cards){
            bill = bill + e.getValue();
        }

        if(bill>21){
            com = 1;
        }else {
            if (bill==21){
            com = 0;
            }
        }
        return com;
    }

    public int checkDealer(ArrayList<Card> cards, int level){

        int bill = 0;
        int com=0;
        int ret;

        for(Card e: cards){
            bill += e.getValue();
        }

        switch(level){
            case 0:
                if(bill<=14){
                    //no hace nada y pide otra carta
                    com = -1;
                }else{
                    if(bill==21){
                        //no pide carta y devuelve 1
                        com = 0;
                    }else{
                        if (bill>14 && bill<21) {
                            com = 0;
                        }else{
                            com=1;
                        }
                    }
                }
                break;
            case 1:
                if(bill<=16){
                    //no hace nada y pide otra carta
                    com = -1;
                }else{
                    if(bill==21){
                        //no pide carta y devuelve 1
                        com = 0;
                    }else{
                        if (bill>16 && bill<21) {
                            com = 0;
                        }else{
                            com=1;
                        }
                    }
                }
                break;
            case 2:
                if(bill<=18){
                    //no hace nada y pide otra carta
                    com = -1;
                }else{
                    if(bill==21){
                        //no pide carta y devuelve 1
                        com = 0;
                    }else{
                        if (bill>18 && bill<21) {
                            com = 0;
                        }else{
                            com=1;
                        }
                    }
                }
                break;
        }
        ret = com;
        return ret;

    }

    public int compare(int player, int dealer){
        int i;

        if(player>dealer){
            i=1;//victoria jugador
        }else{
            if (dealer>player){
                i=-1;//victoria dealer
            }else{
                i=0;//empate
            }
        }

        //Llamaremos a este mñetodo desde el endfragment y comprobamos quien ha ganado
        return i;
    }
}
