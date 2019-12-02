/**
 * @author Logan Goss & Shelton Pinson
 * @email ltgoss@clemson.edu & spinson@clemson.edu
 * @version 1.0
 * @AppDescription GloveBox is an application designed for the DIY community to allow the average DIY'er
 *  to track the services they perform on their personal car for their use in the future to determine
 *  when to perform future services, provide proof to dealerships of self performed service or
 *  proof to future owners of performed service.
 * @ClassDescription a service object for use with the GloveBox App, allows for the storage of
 *  data related to services saved within the application.
 */
package com.cpsc4150.glovebox;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Services {
    private String ID = ""; // Holds the service ID
    final private int STATUS_COMPLETE = 1; // The value used to indicate that the service is complete
    final private int STATUS_IN_PROGRESS = 0; // The value used to indicate that the service is incomplete
    private int state; // State of the service, either STATUS_COMPLETE or STATUS_IN_PROGROSS
    private String name; // Service name
    private String date; // Date the service was started
    private String description; // A user provided description of the service
    private List<String> repairImages = new ArrayList<>(); // ArrayList of repair images
    private List<String> repairImagesDesc = new ArrayList<>(); // ArrayList of image descriptions, this and repairImages could be converted to its own object
    private List<String>  partNumbers = new ArrayList<>(); // ArrayList of partNumbers entered for the service performed
    private int mileage; // Vehicle mileage at the start of the service
    // Service Constructor
    public Services(){
        ID = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        state = STATUS_IN_PROGRESS;
        name = "";
        date = "";
        description = "";
        mileage = 000000;
    }

    /**
     * <p>adds a part number to the partNumbers ArrayList</p>
     * @param partNumber should be an alphaNumeric part number used in the service
     */
    public void addPartNumber(String partNumber){partNumbers.add(partNumber);}

    /**
     * <p>adds a image description to the repairImagesDesc ArrayList</p>
     * @param imgDescription the description of the image to be added
     */
    public void addImageDesc(String imgDescription){repairImagesDesc.add(imgDescription);}

    /**
     * <p>retrieves the image description for the image stored at position imageNum</p>
     * @param imageNum the position of the image description to be returned
     * @return the image description of the description at position imageNum
     */
    public String getImageDesc(int imageNum){
        if(repairImagesDesc.size() > imageNum) return this.repairImagesDesc.get(imageNum);
        else return null;
    }

    /**
     * <<p>retrieves the part number stored in partNumbers at position partNumber</p>
     * @param partNumber the position in the arraylist of the part number to be returned
     * @return returns the part number at the given position if it exist otherwise it returns null
     */
    public String getPartNumber(int partNumber){
        if(partNumbers.size() > partNumber) return partNumbers.get(partNumber);
        else return null;
    }

    /**\
     * <p>returns the id of the service for which it is called on</p>
     * @return the id of the service stored in the private variable ID
     */
    public String getId(){ return ID; }

    /**
     * <p>returns the name of service stored in the private variable name</p>
     * @return the name of service
     */
    public String getName(){
        return name;
    }

    /**
     * <p>sets the name of the service</p>
     * @param newName the name to be set
     */
    public void setName(String newName){
        name = newName;
    }

    /**
     * <p>returns the date at which the service was started</p>
     * @return the date the service was started
     */
    public String getDate(){
        return date;
    }

    /**
     * <p>sets the date that the service was started on</p>
     * @param date the date which the service was started
     */
    public void setDate(String date){
        this.date = date;
    }

    /**
     * <p>returns the description of the service</p>
     * @return the description of service
     */
    public String getDescription(){
        return description;
    }

    /**
     * <p>sets the description for the service</p>
     * @param description the description to be set
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * <p>returns the mileage at which the service was started</p>
     * @return the mileage that was set when the service was started
     */
    public int getMileage(){
        return mileage;
    }

    /**
     * <p>sets the mileage at which the service was started</p>
     * @param mileage the mileage of the vehicle at which the service was started
     */
    public void setMileage(int mileage){
        this.mileage = mileage;
    }

    /**
     * <p>returns the state of the service, either STATUS_COMPLETE or STATUS_IN_PROGRESS</p>
     * @return the current state of the service
     */
    public int getState() {return state;}
    /**
     * <p>sets ther service status to STATUS_COMPLETE</p>
     */
    public void setStateComplete() {state = STATUS_COMPLETE;}

    /**
     * <p>returns the file path at the location of imageNumber in the repairImage list for the image</p>
     * @param imageNumber the image that needs to be accessed
     * @return the file path of the image located at imageNumber in the list
     */
    public String getRepairImage(int imageNumber) {
        if(repairImages.size() > imageNumber) return(repairImages.get(imageNumber));
        else return null;
    }

    /**
     * <p>adds the file path for a new repair image to the list</p>
     * @param imagePath the file path to be added
     */
    public void addRepairImage(String imagePath) {repairImages.add(imagePath);}




}
