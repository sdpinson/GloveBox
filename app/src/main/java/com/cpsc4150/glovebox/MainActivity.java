/**
 * @author Logan Goss & Shelton Pinson
 * @email ltgoss@clemson.edu & spinson@clemson.edu
 * @version 1.0
 * @AppDescription GloveBox is an application designed for the DIY community to allow the average DIY'er
 *  to track the services they perform on their personal car for their use in the future to determine
 *  when to perform future services, provide proof to dealerships of self performed service or
 *  proof to future owners of performed service.
 * @ClassDescription the main and currently on activity for the app responsible for all control of the app
 */
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

    public final String SERVICE_LIST_ID = "SERVICE_LIST";   // the location in system pref of the service list
    public final String IN_PROGRESS_LIST_ID = "IN_PROGRESS_LIST_ID"; // the location in system pref of the in progress list
    public List<Services> serviceList; // a new list of Services objects for services that have been completed
    public List<Services> inProgressList; // a new list of Services objects for services that are still in progress
    //Media Player to play sounds
    private MediaPlayer mMediaPlayer; // a new media player for player app sound effects

    //Plays a sound when the app first starts

    /**
     * <P>allows for the recreation of the media player when the app is resumed and the playing of a sound</P>
     */
    @Override
    protected void onResume() {
        super.onResume();
        mMediaPlayer = MediaPlayer.create(this, R.raw.carhorn);
        mMediaPlayer.start();
    }

    /**
     * <p>saves the list of services into shared prefs</p>
     * @param SAVE_LOCATION the location key for the service list to be saved at
     * @param list the list of Services to be saved
     */
    public void saveServices(String SAVE_LOCATION, List<Services> list) {
        SharedPreferences prefs = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(SAVE_LOCATION, json);
        editor.apply();
    }

    /**
     * <p>load the service list from shared prefs</p>
     * @param SAVE_LOCATION the location key of the services list
     * @return the service list to be loaded
     */
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

    /**
     * <p>Defines the actions to be taken when the main activity is created</p>
     * @param savedInstanceState a saved instance of the main activity id one exist
     */
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

    /**
     * <p>sets up the tab layout seen as the main activity in app</p>
     */
    private TabLayout.OnTabSelectedListener tabListener =
            new TabLayout.OnTabSelectedListener() {
                /**
                 * <p>controls the app when the user selects a tab</p>
                 * @param tab the tab index of the selected tab
                 */
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

                /**
                 * <p>defines what happens when a user re-selects the tab they are already on</p>
                 * @param tab the tab that has been reselected
                 */
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
