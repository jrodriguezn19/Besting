package com.mad.besting.main.view;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mad.besting.BestingApp;
import com.squareup.picasso.Picasso;

import com.mad.besting.R;

import java.io.ByteArrayOutputStream;
/**
 * creates a new post with the new photo
 */
public class NewPostActivity extends AppCompatActivity {

    private static final String Tag = "New_Post_Activity";
    private ImageView mPhotoPost;
    private Button mCreatePostBt;
    private String mPathPhoto;
    private BestingApp mBestingApp;
    private StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        mBestingApp = (BestingApp)getApplicationContext();
        mStorageReference = mBestingApp.getStorageReference();

        mPhotoPost = findViewById(R.id.photo_post_IV);
        mCreatePostBt = findViewById(R.id.post_photo_BT);
        if(getIntent().getExtras() !=null){
            mPathPhoto = getIntent().getExtras().getString("PATH_PHOTO_TEMP");
            showPhoto();
        }

        mCreatePostBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPhoto();

            }
        });

    }
    /**
     * uploads the photo to Firebase
     */
    private void uploadPhoto() {

        mPhotoPost.setDrawingCacheEnabled(true);
        mPhotoPost.buildDrawingCache();

        Bitmap bitmap = mPhotoPost.getDrawingCache();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 ,byteArrayOutputStream);

        byte [] photoByte = byteArrayOutputStream.toByteArray();
        String namePhoto = mPathPhoto.substring(mPathPhoto.lastIndexOf("/")+1,mPathPhoto.length());
        StorageReference photoReference = mStorageReference.child(getString(R.string.posted_photos_folder) + namePhoto);

        UploadTask uploadTask = photoReference.putBytes(photoByte);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(Tag, getString(R.string.error_uploading_photo) + e.toString());
                e.printStackTrace();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri photoUri = taskSnapshot.getUploadSessionUri();
                String urlPhoto = photoUri.toString();
                Log.w(Tag, getString(R.string.url_photo) + urlPhoto);
                finish();
            }
        });


    }
    /**
     * Loads the photo from Firebase
     */
    private void showPhoto(){
        Picasso.get().load(mPathPhoto).into(mPhotoPost);
    }
}
