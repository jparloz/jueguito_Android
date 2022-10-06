package com.jparral.shortdamgames10.viewmodel;
import androidx.lifecycle.ViewModel;

import com.jparral.shortdamgames10.entities.Game;

public class GameViewModel extends ViewModel {
    Game game;

    public Game getGame() {
        if(game==null){
            this.game= new Game();
        }
        return game;
    }
}
