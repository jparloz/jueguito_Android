package com.jparral.shortdamgames10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jparral.shortdamgames10.entities.Card;
import com.jparral.shortdamgames10.entities.Deck;

import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment {

    Deck deck;
    List<Card> playerHand;
    List<Card> dealerHand;
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
        startAttributes();
        Log.d("Pimero",deck.toString());
        Button btn_pedirCarta = getView().findViewById(R.id.btn_pedirCarta);
        btn_pedirCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerHand.add(deck.getNextCard());
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
    private void startAttributes(){
        deck = new Deck();
        deck.shuffle();
        Log.d("Pimero",deck.toString());
        playerHand = new ArrayList<Card>();
        dealerHand = new ArrayList<Card>();
        for (int i = 0; i < 2 ;i++ ){
            playerHand.add(deck.getNextCard());
            deck.deleteFirstCard();
            dealerHand.add(deck.getNextCard());
            Log.d("Pimero",playerHand.get(i).toString() + " " +dealerHand.get(i).toString());
        }
    }
}