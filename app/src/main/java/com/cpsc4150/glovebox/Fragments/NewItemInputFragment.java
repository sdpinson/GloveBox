/**
 * @author Logan Goss & Shelton Pinson
 * @email ltgoss@clemson.edu & spinson@clemson.edu
 * @version 1.0
 * @AppDescription GloveBox is an application designed for the DIY community to allow the average DIY'er
 *  to track the services they perform on their personal car for their use in the future to determine
 *  when to perform future services, provide proof to dealerships of self performed service or
 *  proof to future owners of performed service.
 * @Description sets up the fragment for the new item input fragment
 * @see https://developer.android.com/training/camera/photobasics.html#java
 */
package com.cpsc4150.glovebox.Fragments;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;

import com.cpsc4150.glovebox.MainActivity;
import com.cpsc4150.glovebox.R;
import com.cpsc4150.glovebox.Services;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class NewItemInputFragment extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private Services service = new Services();
    private String currentPhotoPath;
    private int view = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_item_input, container,
                false);
        Bundle newName = this.getArguments();
        final TextView titleName = v.findViewById(R.id.titleText);
        titleName.setText(newName.getString("Name"));

        final EditText mileage = (EditText) v.findViewById(R.id.mileage_entered);
        final EditText partNumberOne = (EditText) v.findViewById(R.id.partNumberOne);
        final EditText partNumberTwo = (EditText) v.findViewById(R.id.partNumberTwo);
        final EditText partNumberThree = (EditText) v.findViewById(R.id.partNumberThree);
        final EditText imageDescOne = (EditText) v.findViewById(R.id.imageOneDesc);
        final EditText imageDescTwo = (EditText) v.findViewById(R.id.imageTwoDesc);
        final EditText imageDescThree = (EditText) v.findViewById(R.id.imageThreeDesc);
        final EditText note = (EditText) v.findViewById(R.id.serviceDescriptionInput);

        final ImageButton pictureButtonOne = (ImageButton) v.findViewById(R.id.addImageButtonOne);

        pictureButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                pictureButtonOne.setClickable(false);
                pictureButtonOne.setVisibility(View.GONE);
                imageDescOne.setClickable(true);
                imageDescOne.setText("Picture One");
            }

        });

        final ImageButton pictureButtonTwo = (ImageButton) v.findViewById(R.id.addImageButtonTwo);
        pictureButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                pictureButtonTwo.setClickable(false);
                pictureButtonTwo.setVisibility(View.GONE);
                imageDescTwo.setClickable(true);
                imageDescTwo.setText("Picture Two");
            }
        });

        final ImageButton pictureButtonThree = (ImageButton) v.findViewById(R.id.addImageButtonThree);
        pictureButtonThree.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                pictureButtonThree.setClickable(false);
                pictureButtonThree.setVisibility(View.GONE);
                imageDescThree.setClickable(true);
                imageDescThree.setText("Picture Three");
            }
        });

//        save on click, sends the created service to the in progress list and shows in
//        the in progress tab
        Button saveButton = (Button) v.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Sets the name for each saved service
                service.setStateComplete();
                service.setName(titleName.getText().toString());
                try{service.setMileage((Integer.parseInt(mileage.getText().toString())));}
                catch(Exception e){
                    Log.e("","mileage not entered");
                }
                service.addPartNumber(partNumberOne.getText().toString());
                service.addPartNumber(partNumberTwo.getText().toString());
                service.addPartNumber(partNumberThree.getText().toString());
                service.setDate(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
                if(imageDescOne != null) service.addImageDesc(imageDescOne.getText().toString());
                if(imageDescTwo != null) service.addImageDesc(imageDescTwo.getText().toString());
                if(imageDescThree != null) service.addImageDesc(imageDescThree.getText().toString());
                if(note != null) service.setNote(note.getText().toString());

                // save service into shared prefs
                MainActivity main = (MainActivity) getActivity();
                main.inProgressList.add(service);
                main.saveServices(main.IN_PROGRESS_LIST_ID,main.inProgressList);

                Fragment fragment = new NewItemTabFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });
        Button completeButton = (Button) v.findViewById(R.id.completeButton);
        completeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Sets the name of the service that has been completed
                service.setStateComplete();
                service.setName(titleName.getText().toString());

                try{service.setMileage((Integer.parseInt(mileage.getText().toString())));}
                catch(Exception e){
                    Log.e("","mileage not entered");
                }
                if(partNumberOne != null) service.addPartNumber(partNumberOne.getText().toString());
                if(partNumberTwo != null) service.addPartNumber(partNumberTwo.getText().toString());
                if(partNumberThree != null) service.addPartNumber(partNumberThree.getText().toString());
                if(imageDescOne != null) service.addImageDesc(imageDescOne.getText().toString());
                if(imageDescTwo != null) service.addImageDesc(imageDescTwo.getText().toString());
                if(imageDescThree != null) service.addImageDesc(imageDescThree.getText().toString());
                if(note != null) service.setNote(note.getText().toString());
                service.addPartNumber(partNumberThree.getText().toString());
                service.setDate(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));

                // save service into shared prefs
                MainActivity main = (MainActivity) getActivity();
                main.serviceList.add(service);
                main.saveServices(main.SERVICE_LIST_ID,main.serviceList);

                // check to see if service was previously saved as in progress and remove
                // from that list

                // redirect to History
                Fragment fragment = new NewItemTabFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
                //main.changeTabIndicator();
            }
        });
        return (v);
    }
    /**
     * <p>accesses the camera intent for capturing repair pictures</p>
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try { photoFile = createImageFile(); }
            catch (IOException ex) { Log.e("File error","File null"); }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.cpsc4150.glovebox",photoFile);
                String uri = ""+photoURI;
                Log.i("File Path",uri);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    /**
     * <p>defines the actions to be done after the camera intent is finished</p>
     * @param requestCode whether or not a picture was requested
     * @param resultCode whether or not the picture was obtained
     * @param data the data returned by the intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            ImageView viewOne = (ImageView) getActivity().findViewById(R.id.smallImageView);
            ImageView viewTwo = (ImageView) getActivity().findViewById(R.id.imageView2);
            ImageView viewThree = (ImageView) getActivity().findViewById(R.id.imageView3);
            switch(view){
                case 0:{
                    setPic(viewOne);
                    Log.i("Service Fragment","Image One Set\n"+currentPhotoPath);
                    view++;
                    service.addRepairImage(currentPhotoPath);
                    //disable button
                    break;
                }
                case 1:{
                    setPic(viewTwo);
                    Log.i("Service Fragment","Image Two Set\n"+currentPhotoPath);
                    view++;
                    service.addRepairImage(currentPhotoPath);
                    //disable button
                    break;
                }
                case 2:{
                    setPic(viewThree);
                    Log.i("Service Fragment","Image Three Set\n"+currentPhotoPath);
                    view++;
                    service.addRepairImage(currentPhotoPath);
                    //disable button
                    break;
                }
                default:{
                    Log.e("Image Set Error","Image not able to be set");
                    break;
                }
            }
        }
    }
    /**
     * <p>creates a file path for the image</p>
     * @return returns the file path
     * @throws IOException throws an exception if an image is unable to be created
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    /**
     * <p>creates a image bitmap and sets it in an image view for the picture just taken</p>
     * @param imageView the image view to set the new bitmap to
     */
    private void setPic(ImageView imageView) {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

}
