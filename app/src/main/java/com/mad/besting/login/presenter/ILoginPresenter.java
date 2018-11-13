package com.mad.besting.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Interface Login Presenter
 */
public interface ILoginPresenter {
    void logIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth);
    void loginSuccessful();
    void loginUnsuccessful(String error);
}
