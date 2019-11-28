package com.cpsc4150.glovebox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;


import com.cpsc4150.glovebox.Fragments.HistoryFragment;
import com.cpsc4150.glovebox.Fragments.NewItemFragment;
import com.cpsc4150.glovebox.Fragments.InProgressFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final String SERVICE_LIST_ID = "SERVICE_LIST";
    public List<Services> serviceList;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onResume() {
        super.onResume();
        mMediaPlayer = MediaPlayer.create(this, R.raw.carhorn);
        mMediaPlayer.start();
    }

    public void saveServices() {
        SharedPreferences prefs = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(serviceList);
        editor.putString(SERVICE_LIST_ID, json);
        editor.apply();
    }

    public void loadServices() {
        Log.i("loadServices","Service Object List loading");
        SharedPreferences prefs = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(SERVICE_LIST_ID,null);
        Type type = new TypeToken<ArrayList<Services>>() {}.getType();
        serviceList = gson.fromJson(json, type);


        if(serviceList == null) {serviceList  = new ArrayList<>(); Log.i("loadServices","no existing service list");}
        else{Log.i("loadServices",serviceList.get(0).getId());}
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadServices();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setOnTabSelectedListener(tabListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HistoryFragment()).commit();
    }
    private TabLayout.OnTabSelectedListener tabListener =
            new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    Fragment selectedFragment = null;
                    switch(tab.getPosition()){
                        case 0:
                            mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.carhorn);
                            mMediaPlayer.start();
                            Log.i("Selected Fragment", "History");
                            selectedFragment = new HistoryFragment();
                            break;
                        case 1:
                            mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.carstart);
                            mMediaPlayer.start();
                            Log.i("Selected Fragment", "History");
                            selectedFragment = new NewItemFragment();
                            break;
                        case 2:
                            mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.carhorn);
                            mMediaPlayer.start();
                            Log.i("Selected Fragment", "History");
                            selectedFragment = new InProgressFragment();
                            break;
                    }
                    Log.i("Committing Fragment: ", "Selected Fragment");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            };


}
