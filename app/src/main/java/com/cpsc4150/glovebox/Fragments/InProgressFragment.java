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

import com.cpsc4150.glovebox.Adapters.HistoryFragmentAdapter;
import com.cpsc4150.glovebox.Adapters.InProgressAdapter;
import com.cpsc4150.glovebox.MainActivity;
import com.cpsc4150.glovebox.R;

public class InProgressFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_in_progress,container,false);

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
