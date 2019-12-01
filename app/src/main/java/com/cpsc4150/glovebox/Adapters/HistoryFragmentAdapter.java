package com.cpsc4150.glovebox.Adapters;

import android.app.Service;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cpsc4150.glovebox.R;
import com.cpsc4150.glovebox.Services;
import java.util.List;

public class HistoryFragmentAdapter extends RecyclerView.Adapter<HistoryFragmentAdapter.MyViewHolder> {
    //The list of all services added

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

    //Updates the list with more services
    public HistoryFragmentAdapter(List<Services> list){
        this.servicesList = list;
    }

    //Calls the card view to display for each item in the list
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_card, parent, false);
        return new MyViewHolder(view);
    }
    private boolean setImageView(ImageView imageView,int pos, int pic){
        try{
            String imageFilePath = servicesList.get(pos).getRepairImage(pic);
            Bitmap bmImg = BitmapFactory.decodeFile(imageFilePath);
            imageView.setImageBitmap(bmImg);
            Log.i("History Fragment Adapt","setting image\n"+imageFilePath);
            return(true);
        }
        catch(Exception e){
            Log.i("History Fragment Adapt","Image does not exist");
            return(false);
        }
    }

    //Will iterate through the list of all services to display them
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(servicesList.get(position).getName());
        holder.miles.setText(Integer.toString(servicesList.get(position).getMileage()));
        holder.date.setText(servicesList.get(position).getDate());
        //Sets the button for each card view and allows the text to be displayed on button press
        final TextView hiddenText = holder.itemView.findViewById(R.id.hiddenText);
        final Button detailsButton = holder.itemView.findViewById(R.id.detailsButton);
        final ImageView repairImageOne = holder.itemView.findViewById(R.id.hiddenImageOne);
        final ImageView repairImageTwo = holder.itemView.findViewById(R.id.hiddenImageTwo);
        final ImageView repairImageThree = holder.itemView.findViewById(R.id.hiddenImageThree);
        final TextView imageOneDesc = holder.itemView.findViewById(R.id.imageOneDesc);
        final TextView imageTwoDesc = holder.itemView.findViewById(R.id.imageTwoDesc);
        final TextView imageThreeDesc = holder.itemView.findViewById(R.id.imageThreeDesc);
        setImageView(repairImageOne,position,0);
        setImageView(repairImageTwo,position,1);
        setImageView(repairImageThree,position,2);

        Services thisService = servicesList.get(position);
        try{ imageOneDesc.setText(thisService.getImageDesc(0));}
        catch(Exception e){Log.i("History Fragment Adapt","imageOneDesc dne");}
        try{ imageTwoDesc.setText(thisService.getImageDesc(0));}
        catch(Exception e){Log.i("History Fragment Adapt","imageOneDesc dne");}
        try{ imageThreeDesc.setText(thisService.getImageDesc(0));}
        catch(Exception e){Log.i("History Fragment Adapt","imageOneDesc dne");}

        String repairInfo = "\t\tPart Numbers:\n\t\t\t"+thisService.getPartNumber(0)+"\n\t\t\t"+thisService.getPartNumber(1)
                +"\n\t\t\t"+thisService.getPartNumber(2);
        hiddenText.setText(repairInfo);

        detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hiddenText.getVisibility() == View.GONE){
                    repairImageOne.setVisibility(View.VISIBLE);
                    repairImageTwo.setVisibility(View.VISIBLE);
                    repairImageThree.setVisibility(View.VISIBLE);
                    hiddenText.setVisibility(View.VISIBLE);
                    imageOneDesc.setVisibility(View.VISIBLE);
                    imageTwoDesc.setVisibility(View.VISIBLE);
                    imageThreeDesc.setVisibility(View.VISIBLE);
                    detailsButton.setText(R.string.LessDetails);
                }
                else {
                    repairImageOne.setVisibility(View.GONE);
                    repairImageTwo.setVisibility(View.GONE);
                    repairImageThree.setVisibility(View.GONE);
                    imageOneDesc.setVisibility(View.GONE);
                    imageTwoDesc.setVisibility(View.GONE);
                    imageThreeDesc.setVisibility(View.GONE);
                    hiddenText.setVisibility(View.GONE);
                    detailsButton.setText(R.string.MoreDetails);
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
