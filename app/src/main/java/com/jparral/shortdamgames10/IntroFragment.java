package com.jparral.shortdamgames10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jparral.shortdamgames10.entities.Game;
import com.jparral.shortdamgames10.entities.Player;
import com.jparral.shortdamgames10.viewmodel.GameViewModel;
import com.jparral.shortdamgames10.viewmodel.PlayerViewModel;

public class IntroFragment extends Fragment {
    private Button btn_Cambio;
    private PlayerViewModel mplayer = null;
    //TextView jugador;

    public IntroFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mplayer = new ViewModelProvider(getActivity()).get(PlayerViewModel.class);
        GameViewModel GameFragment= new ViewModelProvider(getActivity()).get(GameViewModel.class);
        btn_Cambio =getView().findViewById(R.id.btn_cambio);
        Player player1= mplayer.getPlayer1();
        player1.setName("Juan");
        btn_Cambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game game1 = new Game();

                loadFragment2(new GameFragment());
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