package com.cpsc4150.glovebox.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cpsc4150.glovebox.R;

public class NewItemFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_newitemfragment,container,false);
        Button coolantServiceButton = (Button) v.findViewById(R.id.CoolantServiceButton);
        Button oilServiceButton = (Button) v.findViewById(R.id.oilChangeButton);
        Button otherServiceButton = (Button) v.findViewById(R.id.otherServiceButton);
        Button transServiceButton = (Button) v.findViewById(R.id.otherServiceButton);
        Button tireRotationButton = (Button) v.findViewById(R.id.tireRotationButton);
        Button ignitionServiceButton = (Button) v.findViewById(R.id.ignitionButton);

        coolantServiceButton.setOnClickListener(this){

        };
        oilServiceButton.setOnClickListener(){

        };
        otherServiceButton.setOnClickListener(){

        };
        transServiceButton.setOnClickListener() {

        };
        tireRotationButton.setOnClickListener(){

        };
        ignitionServiceButton.setOnClickListener(){

        };
        return(v);
    }
}
