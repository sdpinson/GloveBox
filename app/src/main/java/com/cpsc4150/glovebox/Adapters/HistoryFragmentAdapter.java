package com.cpsc4150.glovebox.Adapters;


import android.app.Activity;
import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.cpsc4150.glovebox.R;
import com.cpsc4150.glovebox.Services;
import java.util.List;

public class HistoryFragmentAdapter extends RecyclerView.Adapter<HistoryFragmentAdapter.MyViewHolder> {
    //The list of all services added
    List<Services> servicesList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public View textView;
        public TextView name;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.historyID);
        }
    }

    //Updates the list with more services
    public HistoryFragmentAdapter(List<Services> list){
        this.servicesList = list;
    }

    //Calls the card view to display for each item in the list
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_card, parent, false);
        return new MyViewHolder(view);
    }

    //Will iterate through the list of all services to display them
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(servicesList.get(position).getName());
        final TextView hiddenText = holder.itemView.findViewById(R.id.hiddenText);
        final Button detailsButton = holder.itemView.findViewById(R.id.detailsButton);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hiddenText.getVisibility() == View.GONE){
                    hiddenText.setVisibility(View.VISIBLE);
                }
                else {
                    hiddenText.setVisibility(View.GONE);
                }
            }
        });
    }

    //Returns the number of services in the list
    @Override
    public int getItemCount() {
        return servicesList.size();
    }

}
