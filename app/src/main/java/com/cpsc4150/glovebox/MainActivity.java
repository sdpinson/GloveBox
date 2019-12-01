package com.cpsc4150.glovebox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;


import com.cpsc4150.glovebox.Fragments.HistoryTabFragment;
import com.cpsc4150.glovebox.Fragments.InProgressTabFragment;
import com.cpsc4150.glovebox.Fragments.NewItemTabFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final String SERVICE_LIST_ID = "SERVICE_LIST";
    public final String IN_PROGRESS_LIST_ID = "IN_PROGRESS_LIST_ID";
    public List<Services> serviceList;
    public List<Services> inProgressList;
    //Media Player to play sounds
    private MediaPlayer mMediaPlayer;

    //Plays a sound when the app first starts
    @Override
    protected void onResume() {
        super.onResume();
        mMediaPlayer = MediaPlayer.create(this, R.raw.carhorn);
        mMediaPlayer.start();
    }

    public void saveServices(String SAVE_LOCATION, List<Services> list) {
        SharedPreferences prefs = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(SAVE_LOCATION, json);
        editor.apply();
    }

    public List<Services> loadServices(String SAVE_LOCATION) {
        List<Services> list;
        Log.i("loadServices","Service Object List loading");
        SharedPreferences prefs = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(SAVE_LOCATION,null);
        Type type = new TypeToken<ArrayList<Services>>() {}.getType();
        list = gson.fromJson(json, type);

        return(list);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        serviceList = loadServices(SERVICE_LIST_ID);
        if (serviceList == null) serviceList = new ArrayList<>();
        inProgressList = loadServices(IN_PROGRESS_LIST_ID);
        if (inProgressList == null) inProgressList = new ArrayList<>();

                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                TabLayout tabs = findViewById(R.id.tabs);
                tabs.setOnTabSelectedListener(tabListener);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HistoryTabFragment()).commit();
    }
    private TabLayout.OnTabSelectedListener tabListener =
            new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    Fragment selectedFragment = null;
                    switch(tab.getPosition()){
                        case 0:
                            //Plays a sound on each tab selection
                            mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.carhorn);
                            mMediaPlayer.start();
                            Log.i("Selected Fragment", "History");
                            selectedFragment = new HistoryTabFragment();
                            break;
                        case 1:
                            mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.carstart);
                            mMediaPlayer.start();
                            Log.i("Selected Fragment", "History");
                            selectedFragment = new NewItemTabFragment();
                            break;
                        case 2:
                            mMediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.carhorn);
                            mMediaPlayer.start();
                            Log.i("Selected Fragment", "History");
                            selectedFragment = new InProgressTabFragment();
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
                    Fragment selectedFragment = null;
                    switch(tab.getPosition()){
                        case 0:
                            selectedFragment = new HistoryTabFragment();
                            break;
                        case 1:
                            selectedFragment = new NewItemTabFragment();
                            break;
                        case 2:
                            selectedFragment = new InProgressTabFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
            };
}
