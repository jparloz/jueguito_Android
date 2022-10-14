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
import com.jparral.shortdamgames10.viewmodel.PlayerViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment {

    Dealer bench;
    BlackJack casino;
    private GameViewModel mgame = null;
    private PlayerViewModel mplayer = null;

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
        casino = new BlackJack();
        mgame= new ViewModelProvider(getActivity()).get(GameViewModel.class);
        mplayer= new ViewModelProvider(getActivity()).get(PlayerViewModel.class);
        bench.startAttributes();
        Log.d("Pimero", String.valueOf(mgame.getGame().getLevel()));

        Button btn_pedirCarta = getView().findViewById(R.id.btn_pedirCarta);
        btn_pedirCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bench.addCardPlayer();
                int com=casino.comprobar(bench.getPlayerHand());
                Log.d("Pimero", bench.getPlayerHand().toString());
                switch (com){
                    case -1:
                        break;
                    case 0:
                        btn_pedirCarta.setEnabled(false);
                        break;
                    case 1:
                        mplayer.getPlayer1().setScore(0);
                        loadFragment2(new EndFragment());
                        break;
                }
            }
        });

        Button btn_plantarse = getView().findViewById(R.id.btn_plantarse);
        btn_plantarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mostrar segunda carta dealer
                int level = mgame.getGame().getLevel(); //sustituir por dato level gameviewmodel
                int com = -1;
                while (com==-1){
                    Log.d("Pimero", bench.getDealerHand().toString());
                    com = casino.comprobarDealer((bench.getDealerHand()),level);//falta pasar parametro level

                    switch (com){
                        case -1:
                            bench.addCardDealer();
                            break;
                        case 0:
                            break;
                        case 1:
                            mplayer.getPlayer1().setScore(80);
                            loadFragment2(new EndFragment());
                            break;
                    }
                }
                int final_game = casino.comparar(bench.total_bill(bench.getPlayerHand()),bench.total_bill(bench.getDealerHand()));
                switch (final_game){
                    case-1:
                        mplayer.getPlayer1().setScore(20);
                        loadFragment2(new EndFragment());
                        break;
                    case 0:
                        mplayer.getPlayer1().setScore(50);
                        loadFragment2(new EndFragment());
                        break;
                    case 1:
                        mplayer.getPlayer1().setScore(60);
                        loadFragment2(new EndFragment());
                        break;
                }
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