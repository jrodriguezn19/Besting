package com.mad.besting;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class BestingApp extends Application {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseStorage mFirebaseStorage;


    @Override
    public void onCreate() {
        super.onCreate();

        mFirebaseStorage = FirebaseStorage.getInstance();

    }

    public StorageReference getStorageReference(){
        return mFirebaseStorage.getReference();
    }
}
