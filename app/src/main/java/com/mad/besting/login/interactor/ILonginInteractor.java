package com.mad.besting.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Login Interactor Interface
 */
public interface ILonginInteractor {
    void logIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);
}
