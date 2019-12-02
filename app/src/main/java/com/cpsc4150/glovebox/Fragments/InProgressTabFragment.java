/**
 * @author Logan Goss & Shelton Pinson
 * @email ltgoss@clemson.edu & spinson@clemson.edu
 * @version 1.0
 * @AppDescription GloveBox is an application designed for the DIY community to allow the average DIY'er
 *  to track the services they perform on their personal car for their use in the future to determine
 *  when to perform future services, provide proof to dealerships of self performed service or
 *  proof to future owners of performed service.
 * @Description sets up the fragment for the in progress tab
 */
package com.cpsc4150.glovebox.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cpsc4150.glovebox.Adapters.InProgressAdapter;
import com.cpsc4150.glovebox.MainActivity;
import com.cpsc4150.glovebox.R;

public class InProgressTabFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_in_progress_tab,container,false);

        MainActivity main = (MainActivity) this.getActivity();
        RecyclerView progressRV = fragmentView.findViewById(R.id.inProgressRV);

        //find context
        RecyclerView.LayoutManager inProgressLM = new LinearLayoutManager(getActivity());
        progressRV.setLayoutManager(inProgressLM);
        progressRV.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.Adapter progressAdapter = new InProgressAdapter(main.inProgressList);
        progressRV.setAdapter(progressAdapter);
        progressRV.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        return fragmentView;
    }
}
