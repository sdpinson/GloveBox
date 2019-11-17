package com.cpsc4150.glovebox.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cpsc4150.glovebox.R;

public class NewItemFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_newitemfragment,container,
                false);
        Button coolantServiceButton = (Button) v.findViewById(R.id.CoolantServiceButton);
        Button oilServiceButton = (Button) v.findViewById(R.id.oilChangeButton);
        Button otherServiceButton = (Button) v.findViewById(R.id.otherServiceButton);
        Button transServiceButton = (Button) v.findViewById(R.id.otherServiceButton);
        Button tireRotationButton = (Button) v.findViewById(R.id.tireRotationButton);
        Button ignitionServiceButton = (Button) v.findViewById(R.id.ignitionButton);

        final FragmentManager fragmentManager = getFragmentManager();
        coolantServiceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i("onClick coolantServiceButton", "should open ServiceFragment");
                Fragment service = new ServiceFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, service).
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
        });
       oilServiceButton.setOnClickListener(new View.OnClickListener(){
            @Override
           public void onClick(View v) {
                Log.i("onClick oilServiceButton", "should open ServiceFragment");
                Fragment service = new ServiceFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, service).
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
       });
       otherServiceButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Log.i("onClick oilServiceButton", "should open ServiceFragment");
               Fragment service = new ServiceFragment();
               fragmentManager.beginTransaction().replace(R.id.fragment_container, service).
                       setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
           }
       });
       transServiceButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.i("onClick oilServiceButton", "should open ServiceFragment");
               Fragment service = new ServiceFragment();
               fragmentManager.beginTransaction().replace(R.id.fragment_container, service).
                       setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
           }
       });
       tireRotationButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Log.i("onClick oilServiceButton", "should open ServiceFragment");
               Fragment service = new ServiceFragment();
               fragmentManager.beginTransaction().replace(R.id.fragment_container, service).
                       setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
           }
       });
       ignitionServiceButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Log.i("onClick oilServiceButton", "should open ServiceFragment");
               Fragment service = new ServiceFragment();
               fragmentManager.beginTransaction().replace(R.id.fragment_container, service).
                       setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
           }
       });
        return(v);
    }
}
