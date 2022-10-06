package com.jparral.shortdamgames10.viewmodel;

import androidx.lifecycle.ViewModel;

import com.jparral.shortdamgames10.entities.Player;

public class PlayerViewModel extends ViewModel{

    private Player player1;
    private Player player2;

    public Player getPlayer1() {
        if(player1==null){
            player1 = new Player();
        }
        return player1;
    }

    public Player getPlayer2() {
        if(player1==null){
            player2 = new Player();
        }
        return player2;
    }
}
