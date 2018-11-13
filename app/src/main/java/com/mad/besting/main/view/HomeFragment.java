package com.mad.besting.main.view;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mad.besting.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IHomeFragment {

    private static final int REQUEST_CODE_CAMERA = 1;
    private FloatingActionButton cameraFAB;
    private String pathPhotoTemp = " ";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar("Home", false, view);
        RecyclerView mPhotoRecyclerView = view.findViewById(R.id.photo_Recycler_View);
        cameraFAB = view.findViewById(R.id.camera_fab);


        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mPhotoRecyclerView.setLayoutManager(mLinearLayoutManager);

        PhotoAdapterRecyclerView mPhotoAdapterRecyclerView = new PhotoAdapterRecyclerView(buildPhotos(), R.layout.cardview_photo, getActivity());

        mPhotoRecyclerView.setAdapter(mPhotoAdapterRecyclerView);

        cameraFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        return view;
    }
    /**
     * starts the camera tos store the photo and upload the picture later
     */
    @Override
    public void takePhoto() {
        Intent intentTakePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intentTakePhoto.resolveActivity(getActivity().getPackageManager()) != null){

            File filePhoto = null;
            
            try{
                filePhoto = createFilePhoto();
                
            }catch (Exception e){
                e.printStackTrace();
            }
            if(filePhoto != null){
                Uri uriPhoto = FileProvider.getUriForFile(getActivity(), "com.mad.besting", filePhoto);
                intentTakePhoto.putExtra(MediaStore.EXTRA_OUTPUT, uriPhoto);
                startActivityForResult(intentTakePhoto, REQUEST_CODE_CAMERA);
            }
            

        }
    }
    /**
     * creates the file where the photo taken with the camera will be stored
     */
    @Override
    public File createFilePhoto() throws IOException {
        String timeStampPhoto = new SimpleDateFormat("ss-mm-hh_ddMMyyyy").format(new Date());
        String nameFilePhoto = "JPEG_" + timeStampPhoto + "_";
        File storageDirectory = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photoTemp = File.createTempFile(nameFilePhoto, ".jpg", storageDirectory);

        pathPhotoTemp = "file:" + photoTemp.getAbsolutePath();

        return photoTemp;
    }

    /**
     * Checks the photo was taken, and stored correctly
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == getActivity().RESULT_OK){
            Log.d("Fragment_Home", "Camera Worked!");
            Intent intent = new Intent(getActivity(), com.mad.besting.main.view.NewPostActivity.class);
            intent.putExtra("PATH_PHOTO_TEMP", pathPhotoTemp);
            startActivity(intent);

        }
    }

    /**
     * populates the Recyclerview with the current users photos
     */
    @Override
    public ArrayList<Photo> buildPhotos(){
        ArrayList<Photo> mPhotos = new ArrayList<>();
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog1.jpeg?alt=media&token=06bf3b0f-17fb-4306-9126-f44428e6750f", "George Rodriguez", "4 days", "3 likes", "0 dislikes"));
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog2.jpeg?alt=media&token=c1ca9028-aff3-49b6-82eb-2e05186366de", "George Rodriguez", "2 days", "7 likes", "2 dislikes"));
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog3.jpeg?alt=media&token=c890122c-032f-4531-b7c1-f4e23904144e", "George Rodriguez", "23 days", "1 likes", "0 dislikes"));
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog4.jpg?alt=media&token=6d37aefd-e485-4d26-8110-9fb81697d6c9", "Guy Ross", "13 days", "0 likes", "4 dislikes"));
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog5.jpeg?alt=media&token=5d8fadee-32ae-422c-889a-19c5ad6f34d5", "Mel Turner", "2 days", "6 likes", "0 dislikes"));
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog6.jpeg?alt=media&token=ed25715e-f070-4564-a46e-35adc21ad5bc", "Tom Phillips", "11 days", "8 likes", "5 dislikes"));
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog7.jpeg?alt=media&token=f53e64e1-e4d9-436a-83c0-e526b9e022fc", "Jerry Team", "17 days", "6 likes", "1 dislike"));
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog8.jpeg?alt=media&token=d7d9f321-b256-4a1f-a0b1-700f959782f3", "Albert Fine", "22 days", "5 likes", "2 dislikes"));
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog9.jpeg?alt=media&token=6b55fb6c-f342-49a8-bb20-3deab9da3dee", "Maria Fun", "28 days", "12 likes", "0 dislikes"));
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog10.jpeg?alt=media&token=554e40b2-99e5-41a4-93d5-f9d77f56bdda", "Laura Jakson", "30 days", "9 likes", "3 dislikes"));


        return mPhotos;
    }

    /**
     * shows the tools bar of the activity
     */
    @Override
    public void showToolbar(String title, boolean upButton, View view){
        android.support.v7.widget.Toolbar mToolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }



}
