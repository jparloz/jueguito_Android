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
        bench.startAttributes();
        Log.d("Pimero", String.valueOf(mgame.getGame().getLevel()));

        Button btn_pedirCarta = getView().findViewById(R.id.btn_pedirCarta);
        btn_pedirCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bench.addCardPlayer();
                int com=casino.comprobar(bench.getPlayerHand());
                Log.d("Pimero", bench.getPlayerHand().toString());
                if(com==1){
                    //Derrota no es necesario guardar score, se queda en 0 salimos a end
                    loadFragment2(new EndFragment());
                }//en caso -1 no hacemos nada, en caso 0 tampoco, se debe comparar con el dealer o podemos pedir carta
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

                    if(com==-1){//pedimos carta
                        bench.addCardDealer();
                    }else if(com==0){
                        //entre dificulta y 21, salimos del bucle y comparamos
                    }else if(com==1){
                        //Pierde la banca, se anuncia jugador ganador, se guarda score y vamos al end

                        loadFragment2(new EndFragment());
                    }
                }
                int final_game = casino.comparar(bench.total_bill(bench.getPlayerHand()),bench.total_bill(bench.getDealerHand()));
                if (final_game==1){
                    loadFragment2(new EndFragment());
                }else if(final_game==-1){
                    loadFragment2(new EndFragment());
                }else{
                    loadFragment2(new EndFragment());
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