package com.cpsc4150.glovebox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.cpsc4150.glovebox.Adapters.HistoryFragmentAdapter;
import com.cpsc4150.glovebox.Fragments.HistoryFragment;
import com.cpsc4150.glovebox.Fragments.NewItemFragment;
import com.cpsc4150.glovebox.Fragments.UpcomingFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

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
                            selectedFragment = new HistoryFragment();
                            RecyclerView hisotryRV = (RecyclerView)findViewById(R.id.historyRV);
                            hisotryRV.setLayoutManager(new LinearLayoutManager());
                            break;
                        case 1:
                            selectedFragment = new NewItemFragment();
                            break;
                        case 2:
                            selectedFragment = new UpcomingFragment();
                            break;
                    }
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
