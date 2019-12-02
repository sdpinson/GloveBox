/**
 * @author Logan Goss & Shelton Pinson
 * @email ltgoss@clemson.edu & spinson@clemson.edu
 * @version 1.0
 * @AppDescription GloveBox is an application designed for the DIY community to allow the average DIY'er
 *  to track the services they perform on their personal car for their use in the future to determine
 *  when to perform future services, provide proof to dealerships of self performed service or
 *  proof to future owners of performed service.
 * @ClassDescription adapter for the history recycler view
 */
package com.cpsc4150.glovebox.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.cpsc4150.glovebox.Fragments.InProgressServiceFragment;
import com.cpsc4150.glovebox.R;
import com.cpsc4150.glovebox.Services;

import java.util.List;

public class InProgressAdapter extends RecyclerView.Adapter<InProgressAdapter.InProgressViewHolder>{

    // creates a new progressList
    private List<Services> progressList;

    public static class InProgressViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView miles;
        private TextView date;
        private TextView description;
        /**
         * <p>creates a view holder given view v</p>
         */
        public InProgressViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.inProgressID);
            miles = (TextView) v.findViewById(R.id.miles);
            date = (TextView) v.findViewById(R.id.date);
            description = (TextView) v.findViewById(R.id.serviceDescription);
        }
    }

    //Updates the list with more services

    /**
     * <p> sets the progressList to list</p>
     * @param list the list to set progress list to
     */
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
    public void onBindViewHolder(InProgressAdapter.InProgressViewHolder holder, final int position) {
        holder.name.setText(progressList.get(position).getName());
        holder.miles.setText(Integer.toString(progressList.get(position).getMileage()));
        holder.date.setText(progressList.get(position).getDate());
        Services thisService = progressList.get(position);
        //Sets the button for each card view and allows the text to be displayed on button press
        final Button editButton = holder.itemView.findViewById(R.id.detailsButton);
        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment inProgressService = new InProgressServiceFragment(position);
                activity.getSupportFragmentManager().beginTransaction().replace(
                        R.id.fragment_container, inProgressService).addToBackStack(null).commit();
            }
        });
    }

    //Returns the number of services in the list
    @Override
    public int getItemCount() {
        return progressList.size();
    }
}
