package com.jparral.shortdamgames10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jparral.shortdamgames10.entities.BlackJack;
import com.jparral.shortdamgames10.entities.Card;
import com.jparral.shortdamgames10.entities.Dealer;
import com.jparral.shortdamgames10.viewmodel.GameViewModel;
import com.jparral.shortdamgames10.viewmodel.PlayerViewModel;

import java.util.ArrayList;

public class GameFragment extends Fragment {

    Dealer bench;
    BlackJack casino;
    private GameViewModel mgame = null;
    private PlayerViewModel mplayer = null;
    TextView cards;
    TextView d_cards;
    Button btn_stand;
    Button btn_hitCard;
    ImageView iv_playerCard;
    TextView tv_p_hand;
    ImageView iv_dealerCard;
    TextView tv_d_hand;

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
        loadCards();
        cards=getView().findViewById(R.id.tv_player_cards);
        cards.setText(bench.getPlayerHand().toString());
        btn_hitCard = getView().findViewById(R.id.btn_hit_card);
        btn_hitCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bench.addCardPlayer();
                loadCard(tv_p_hand,bench.getPlayerHand(),iv_playerCard);
                cards.setText(bench.getPlayerHand().toString());
                int com=casino.check(bench.getPlayerHand());
                switch (com){
                    case -1:
                        break;
                    case 0:
                        mplayer.getPlayer1().setScore(100);
                        disabledButtons();
                        waitandSkipFragment(4000);
                    case 1:
                        mplayer.getPlayer1().setScore(0);
                        disabledButtons();
                        waitandSkipFragment(4000);
                        break;
                }
            }
        });

        btn_stand = getView().findViewById(R.id.btn_stand);
        btn_stand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d_cards=getView().findViewById(R.id.tv_dealer_cards);
                d_cards.setText(bench.getDealerHand().toString());
                disabledButtons(btn_hitCard);
                disabledButtons(btn_stand);

                int level = mgame.getGame().getLevel();
                int com = -1;
                while (com==-1){
                    com = casino.checkDealer((bench.getDealerHand()),level);
                    int final_game = casino.compare(bench.total_bill(bench.getPlayerHand()),bench.total_bill(bench.getDealerHand()));

                    switch (com){
                        case -1:
                            loadCard(tv_d_hand,bench.getDealerHand(),iv_dealerCard);
                            bench.addCardDealer();
                            d_cards.setText(bench.getDealerHand().toString());
                            loadCard(tv_d_hand,bench.getDealerHand(),iv_dealerCard);
                            switch (final_game){
                                case-1:
                                    mplayer.getPlayer1().setScore(20);
                                    disabledButtons();
                                    waitandSkipFragment(4000);
                                    break;
                                case 0:
                                    mplayer.getPlayer1().setScore(50);
                                    disabledButtons();
                                    waitandSkipFragment(4000);
                                    break;
                                case 1:
                                    mplayer.getPlayer1().setScore(80);
                                    disabledButtons();
                                    waitandSkipFragment(4000);
                                    break;
                            }
                            break;
                        case 0:
                            loadCard(tv_d_hand,bench.getDealerHand(),iv_dealerCard);
                            switch (final_game){
                                case-1:
                                    mplayer.getPlayer1().setScore(20);
                                    disabledButtons();
                                    waitandSkipFragment(4000);
                                    break;
                                case 0:
                                    mplayer.getPlayer1().setScore(50);
                                    disabledButtons();
                                    waitandSkipFragment(4000);
                                    break;
                                case 1:
                                    mplayer.getPlayer1().setScore(80);
                                    disabledButtons();
                                    waitandSkipFragment(4000);
                                    break;
                            }
                            break;
                        case 1:
                            loadCard(tv_d_hand,bench.getDealerHand(),iv_dealerCard);
                            mplayer.getPlayer1().setScore(60);
                            waitandSkipFragment(4000);
                            break;
                    }
                }

            }
        });

    }
    public void instanceObjects(){
        iv_playerCard = getView().findViewById(R.id.iv_playerCard);
        tv_p_hand=getView().findViewById(R.id.tv_p_hand);
        iv_dealerCard = getView().findViewById(R.id.iv_dealerCard);
        tv_d_hand=getView().findViewById(R.id.tv_d_hand);
        bench = new Dealer();
        casino = new BlackJack();
        mgame= new ViewModelProvider(getActivity()).get(GameViewModel.class);
        mplayer= new ViewModelProvider(getActivity()).get(PlayerViewModel.class);
        bench.startAttributes();
    }

    public void waitandSkipFragment(int milsec) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                loadFragment2(new EndFragment());
            }
        }, milsec);
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
        btn_stand.setEnabled(false);
        btn_hitCard.setEnabled(false);
    }
    private void loadCards(){
        loadCard(tv_p_hand,bench.getPlayerHand(),iv_playerCard);
        loadCard(tv_d_hand,bench.getDealerHand(),iv_dealerCard);
    }

    private void loadCard(TextView tv,ArrayList<Card> card,ImageView iv ) {
        tv.setText(String.valueOf(card.get(card.size()-1).getValue()));
        String suit = card.get(card.size()-1).getSuit();
        switch (suit){
            case "Heart":
                iv.setImageResource(R.drawable.hearts);
                break;
            case "Spades":
                iv.setImageResource(R.drawable.spades);
                break;
            case "Clover":
                iv.setImageResource(R.drawable.clovers);
                break;
            case "Diamond":
                iv.setImageResource(R.drawable.diamonds);
                break;
        }
    }
}