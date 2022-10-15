package com.jparral.shortdamgames10;

import android.os.Bundle;

import androidx.core.os.ProcessCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
    TextView cards;
    Button btn_plantarse;
    Button btn_pedirCarta;

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
        instanceObjects();
        Log.d("Pimero", String.valueOf(mgame.getGame().getLevel()));
        cards=getView().findViewById(R.id.tv_cartasJugador);
        cards.setText(bench.getPlayerHand().toString());
        btn_pedirCarta = getView().findViewById(R.id.btn_pedirCarta);
        btn_pedirCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bench.addCardPlayer();
                cards.setText(bench.getPlayerHand().toString());
                int com=casino.comprobar(bench.getPlayerHand());
                Log.d("Pimero", bench.getPlayerHand().toString());
                switch (com){
                    case -1:
                        break;
                    case 0:
                        disabledButtons(btn_pedirCarta);
                        break;
                    case 1:
                        mplayer.getPlayer1().setScore(0);
                        disabledButtons();
                        waitandSkipFragment();
                        break;
                }
            }
        });

        btn_plantarse = getView().findViewById(R.id.btn_plantarse);
        btn_plantarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disabledButtons(btn_pedirCarta);
                disabledButtons(btn_plantarse);
                Log.d("Primero", bench.getDealerHand().toString());
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
                            mplayer.getPlayer1().setScore(60);
                            waitandSkipFragment();
                            break;
                    }
                }
                int final_game = casino.comparar(bench.total_bill(bench.getPlayerHand()),bench.total_bill(bench.getDealerHand()));
                Log.d("Primero", String.valueOf(final_game));
                switch (final_game){
                    case-1:
                        mplayer.getPlayer1().setScore(20);
                        disabledButtons();
                        waitandSkipFragment();
                        break;
                    case 0:
                        mplayer.getPlayer1().setScore(50);
                        disabledButtons();
                        waitandSkipFragment();
                        break;
                    case 1:
                        mplayer.getPlayer1().setScore(80);
                        disabledButtons();
                        waitandSkipFragment();
                        break;
                }
            }
        });

    }
    public void instanceObjects(){
        bench = new Dealer();
        casino = new BlackJack();
        mgame= new ViewModelProvider(getActivity()).get(GameViewModel.class);
        mplayer= new ViewModelProvider(getActivity()).get(PlayerViewModel.class);
        bench.startAttributes();
    }
    public void waitandSkipFragment() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                loadFragment2(new EndFragment());
            }
        }, 5000);
    }
    private void loadFragment2(Fragment fragmento){
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.f_container,fragmento)
                .commit();
    }
    private void disabledButtons(Button btn){
        btn.setEnabled(false);
    }
    private void disabledButtons(){
        btn_plantarse.setEnabled(false);
        btn_pedirCarta.setEnabled(false);
    }
}