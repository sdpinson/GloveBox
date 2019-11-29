package com.cpsc4150.glovebox.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cpsc4150.glovebox.R;
import com.cpsc4150.glovebox.Services;

import java.util.List;

public class UpcomingAdapter {
    private List<Services> servicesList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView miles;
        private TextView date;
        private TextView description;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.historyID);
            miles = (TextView) v.findViewById(R.id.miles);
            date = (TextView) v.findViewById(R.id.date);
            description = (TextView) v.findViewById(R.id.serviceDescription);
        }
    }
}
