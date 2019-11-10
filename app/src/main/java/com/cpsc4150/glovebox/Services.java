package com.cpsc4150.glovebox;

public class Services {
    private String name;
    private String date;
    private String description;
    private int mileage;
    public Services(){
        name = "Null";
        date = "11/10/19";
        description = "My car";
        mileage = 000;
    }

    public void setName(String newName){
        name = newName;
    }

    public String getName(){
        return name;
    }
}
