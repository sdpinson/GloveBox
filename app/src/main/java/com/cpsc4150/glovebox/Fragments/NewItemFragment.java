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
        View v =  inflater.inflate(R.layout.fragment_newitem,container,
                false);
        final Button coolantServiceButton = (Button) v.findViewById(R.id.CoolantServiceButton);
        final Button oilServiceButton = (Button) v.findViewById(R.id.oilChangeButton);
        final Button otherServiceButton = (Button) v.findViewById(R.id.otherServiceButton);
        final Button transServiceButton = (Button) v.findViewById(R.id.transServiceButton);
        final Button tireRotationButton = (Button) v.findViewById(R.id.tireRotationButton);
        final Button ignitionServiceButton = (Button) v.findViewById(R.id.ignitionButton);

        //Each on click listener now contains a bundle that passes the title of the button pressed
        //to the service fragment for better readability. 


        final FragmentManager fragmentManager = getFragmentManager();
        coolantServiceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.i("onClick coolantServiceButton", "should open ServiceFragment");
                Fragment service = new ServiceFragment();
                Bundle name = new Bundle();
                name.putString("Name",coolantServiceButton.getText().toString());
                service.setArguments(name);
                fragmentManager.beginTransaction().replace(R.id.fragment_container, service).
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
        });
       oilServiceButton.setOnClickListener(new View.OnClickListener(){
            @Override
           public void onClick(View v) {
                Log.i("onClick oilServiceButton", "should open ServiceFragment");
                Fragment service = new ServiceFragment();
                Bundle name = new Bundle();
                name.putString("Name",oilServiceButton.getText().toString());
                service.setArguments(name);
                fragmentManager.beginTransaction().replace(R.id.fragment_container, service).
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
            }
       });
       otherServiceButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Log.i("onClick otherServiceButton", "should open ServiceFragment");
               Fragment service = new ServiceFragment();
               Bundle name = new Bundle();
               name.putString("Name",otherServiceButton.getText().toString());
               service.setArguments(name);
               fragmentManager.beginTransaction().replace(R.id.fragment_container, service).
                       setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
           }
       });
       transServiceButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.i("onClick transServiceButton", "should open ServiceFragment");
               Fragment service = new ServiceFragment();
               Bundle name = new Bundle();
               name.putString("Name",transServiceButton.getText().toString());
               service.setArguments(name);
               fragmentManager.beginTransaction().replace(R.id.fragment_container, service).
                       setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
           }
       });
       tireRotationButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Log.i("onClick tireServiceButton", "should open ServiceFragment");
               Fragment service = new ServiceFragment();
               Bundle name = new Bundle();
               name.putString("Name",tireRotationButton.getText().toString());
               service.setArguments(name);
               fragmentManager.beginTransaction().replace(R.id.fragment_container, service).
                       setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
           }
       });
       ignitionServiceButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Log.i("onClick ignitionServiceButton", "should open ServiceFragment");
               Fragment service = new ServiceFragment();
               Bundle name = new Bundle();
               name.putString("Name",ignitionServiceButton.getText().toString());
               service.setArguments(name);
               fragmentManager.beginTransaction().replace(R.id.fragment_container, service).
                       setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
           }
       });
        return(v);
    }
}
