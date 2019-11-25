package com.cpsc4150.glovebox.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.graphics.Bitmap;

import com.cpsc4150.glovebox.R;
import com.cpsc4150.glovebox.Services;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class ServiceFragment extends Fragment {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    //static final int RESULT_OK = 1;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private Services service;
    String currentPhotoPath;

//    https://developer.android.com/training/camera/photobasics.html#java

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service, container,
                false);

        Button pictureButtonOne = (Button) v.findViewById(R.id.repairImageButton);
        Bundle newName = this.getArguments();

        pictureButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }

        });

        Button pictureButtonTwo = (Button) v.findViewById(R.id.receiptImageButton);
        pictureButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        Button pictureButtonThree = (Button) v.findViewById(R.id.button6);
        pictureButtonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        TextView name = v.findViewById(R.id.titleText);
        name.setText(newName.getString("Name"));

        TextView milleage = v.findViewById(R.id.millage_entered);
        // how to set this?
//        service.setMileage(milleage.get);
        return (v);
    }

//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                Log.e("File error","File null");
//                // Error occurred while creating the File
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(getActivity(),
//                        "com.cpsc4150.glovebox",
//                        photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
//    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        String debug = " "+requestCode+resultCode+" ";
//        Log.i("requestCode, resultCode =",debug);
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == -1) {
//            ImageView imageView = (ImageView) getActivity().findViewById(R.id.smallImageView);
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imageView.setImageBitmap(imageBitmap); // use this to display a thumnail
//            Log.i("image set","image set");
//        }
//    }
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(!data.hasExtra("data")){
            Log.e("Error: ","Bitmap does not exist");
        }
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.smallImageView);
        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        Log.i("imageView","BitMapSet");
        imageView.setImageBitmap(imageBitmap);
    }
}

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

}
