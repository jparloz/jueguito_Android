package com.jparral.shortdamgames10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jparral.shortdamgames10.entities.BlackJack;
import com.jparral.shortdamgames10.entities.Card;
import com.jparral.shortdamgames10.entities.Dealer;
import com.jparral.shortdamgames10.entities.Deck;
import com.jparral.shortdamgames10.entities.Game;
import com.jparral.shortdamgames10.viewmodel.GameViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment {

    Dealer bench;
    BlackJack casino;

    public GameFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        bench = new Dealer();
        bench.startAttributes();


        Button btn_pedirCarta = getView().findViewById(R.id.btn_pedirCarta);
        btn_pedirCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bench.getCardPlayer();
                int com = casino.comprobar(bench.getPlayerHand());

                if(com>=0){
                    loadFragment2(new EndFragment());
                }
            }
        });

        Button btn_plantarse = getView().findViewById(R.id.btn_plantarse);
        btn_plantarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bench.getDealerHand();//banca pide carta
                int level = 0; //sustituir por dato level gameviewmodel
                int com = casino.comprobarDealer((bench.getDealerHand()),level);//falta pasar parametro level




            }
        });

    }
    private void loadFragment2(Fragment fragmento){
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.f_container,fragmento)
                .addToBackStack(null)
                .commit();
    }
}