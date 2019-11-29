package com.cpsc4150.glovebox.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cpsc4150.glovebox.R;
import com.cpsc4150.glovebox.Services;

import java.util.List;

public class InProgressAdapter extends RecyclerView.Adapter<InProgressAdapter.InProgressViewHolder>{

    private List<Services> progressList;

    public static class InProgressViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView miles;
        private TextView date;
        private TextView description;

        public InProgressViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.historyID);
            miles = (TextView) v.findViewById(R.id.miles);
            date = (TextView) v.findViewById(R.id.date);
            description = (TextView) v.findViewById(R.id.serviceDescription);
        }
    }

    //Updates the list with more services
    public InProgressAdapter(List<Services> list){
        this.progressList = list;
    }

    //Calls the card view to display for each item in the list
    @NonNull
    @Override
    public InProgressAdapter.InProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_card, parent, false);
        return new InProgressAdapter.InProgressViewHolder(view);
    }

    //Will iterate through the list of all services to display them
    @Override
    public void onBindViewHolder(InProgressAdapter.InProgressViewHolder holder, int position) {
        holder.name.setText(progressList.get(position).getName());
        holder.miles.setText(Integer.toString(progressList.get(position).getMileage()));
        holder.date.setText(progressList.get(position).getDate());
        //Sets the button for each card view and allows the text to be displayed on button press
        final TextView hiddenText = holder.itemView.findViewById(R.id.hiddenText);
        final Button detailsButton = holder.itemView.findViewById(R.id.detailsButton);
        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hiddenText.getVisibility() == View.GONE){
                    hiddenText.setVisibility(View.VISIBLE);
                    detailsButton.setText(R.string.LessDetails);
                }
                else {
                    hiddenText.setVisibility(View.GONE);
                    detailsButton.setText(R.string.MoreDetails);
                }
            }
        });
    }

    //Returns the number of services in the list
    @Override
    public int getItemCount() {
        return progressList.size();
    }
}
