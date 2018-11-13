package com.mad.besting.main.view;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.mad.besting.BestingApp;
import com.mad.besting.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
/**
 * shows the complete details of the photo
 */
public class PhotoDetailActivity extends AppCompatActivity {

    private static final String NAME_PHOTO = "dog1.jpeg";
    private ImageView mPhotoImageHeaderIV;
    private BestingApp mBestingApp;
    StorageReference mStorageReference;
    private CircleImageView mProfilePhotoCIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        mBestingApp = (BestingApp) getApplicationContext();
        mStorageReference = mBestingApp.getStorageReference();

        mPhotoImageHeaderIV = findViewById(R.id.photo_header_IV);
        showToolbar("",true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setEnterTransition(new Fade());
        }
        
        showInfo();

    }
    /**
     * loads the photo from Firebase
     */
    private void showInfo() {
        
        mStorageReference.child("postedPhotos/" + NAME_PHOTO).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri.toString()).into(mPhotoImageHeaderIV);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PhotoDetailActivity.this, R.string.error_donwloading_photo, Toast.LENGTH_LONG).show();

            }
        });
        
    }

    /**
     * shows the tool bar
     */
    public void showToolbar(String title, boolean upButton){
        android.support.v7.widget.Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        CollapsingToolbarLayout mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);


    }
}
