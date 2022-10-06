package com.jparral.shortdamgames10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jparral.shortdamgames10.viewmodel.PlayerViewModel;

import org.w3c.dom.Text;


public class EndFragment extends Fragment {

    public EndFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_end, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        TextView nombre = getView().findViewById(R.id.tv_mostrar);
        PlayerViewModel mplayer =new ViewModelProvider(getActivity()).get(PlayerViewModel.class);
        nombre.setText(mplayer.getPlayer1().getName());
    }
}