package com.cpsc4150.glovebox.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.cpsc4150.glovebox.Fragments.InProgressServiceFragment;
import com.cpsc4150.glovebox.Fragments.NewItemFragment;
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
                .inflate(R.layout.inprogress_card, parent, false);
        return new InProgressAdapter.InProgressViewHolder(view);
    }
    //Will iterate through the list of all services to display them
    @Override
    public void onBindViewHolder(InProgressAdapter.InProgressViewHolder holder, int position) {
        holder.miles.setText(Integer.toString(progressList.get(position).getMileage()));
        holder.date.setText(progressList.get(position).getDate());
        Services thisService = progressList.get(position);
        //Sets the button for each card view and allows the text to be displayed on button press
        final Button editButton = holder.itemView.findViewById(R.id.detailsButton);
        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new InProgressServiceFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();
            }
        });
    }

    //Returns the number of services in the list
    @Override
    public int getItemCount() {
        return progressList.size();
    }
}
