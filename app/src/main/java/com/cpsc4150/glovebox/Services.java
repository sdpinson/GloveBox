package com.cpsc4150.glovebox;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Services {
    private String ID = "";
    final private int STATUS_COMPLETE = 1;
    final private int STATUS_IN_PROGRESS = 0;
    private int state;
    private String name;
    private String date;
    private String description;
    private List<String> repairImages = new ArrayList<>();
    private List<String> receiptImages = new ArrayList<>();
    private List<String>  partNumbers = new ArrayList<>();
    private int mileage;
    public Services(){
        ID = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        state = STATUS_IN_PROGRESS;
        name = "Service";
        date = "11/10/19";
        description = "My car";
        mileage = 000000;
    }

    public void addPartNumber(String partNumber){partNumbers.add(partNumber);}

    public String getPartNumber(int partNumber){return partNumbers.get(partNumber);}

    public String getId(){ return ID; }

    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getMileage(){
        return mileage;
    }

    public void setMileage(int mileage){
        this.mileage = mileage;
    }

    public int getState() {return state;}

    public void setStateComplete() {state = STATUS_COMPLETE;}

    public String getRepairImage(int imageNumber) {return(repairImages.get(imageNumber));}

    public void addRepairImage(String imageLink) {repairImages.add(imageLink);}

    public String getReceiptImage(int imageNumber) {return(receiptImages.get(imageNumber));}

    public void addReceiptImage(String imageLink) {receiptImages.add(imageLink);}

    public String toJson() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return(json);
    }

}
