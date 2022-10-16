package com.jparral.shortdamgames10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jparral.shortdamgames10.entities.Player;
import com.jparral.shortdamgames10.viewmodel.GameViewModel;
import com.jparral.shortdamgames10.viewmodel.PlayerViewModel;

public class IntroFragment extends Fragment {
    private EditText et_name;
    private GameViewModel mgame;
    private Button btn_start;
    private Spinner sp;
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
        loadXML();
        Player player1= mplayer.getPlayer1();
        player1.setName(String.valueOf(et_name.getText()));
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dificultSrelector();
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
    public void loadXML(){
        mplayer = new ViewModelProvider(getActivity()).get(PlayerViewModel.class);
        mgame= new ViewModelProvider(getActivity()).get(GameViewModel.class);
        btn_start = getView().findViewById(R.id.btn_start);
        et_name = getView().findViewById(R.id.et_name);
        sp = getView().findViewById(R.id.sp_difficult);
        loadSpiner(sp);

    }
    private void loadSpiner(Spinner sp) {
        String[] difSelec = getResources().getStringArray(R.array.difficult_array);
        ArrayAdapter<String> difAdapter = new ArrayAdapter <String>
                (getContext(), android.R.layout.simple_spinner_dropdown_item,difSelec);
        sp.setAdapter(difAdapter);
    }
    private void dificultSrelector (){
        String diflvl = (String)sp.getSelectedItem();
        switch(diflvl){
            case "Hard":
                mgame.getGame().setLevel(2);
                break;
            case "Medium":
                mgame.getGame().setLevel(1);
                break;
            case "Easy":
                mgame.getGame().setLevel(0);
                break;
        }
    }
}