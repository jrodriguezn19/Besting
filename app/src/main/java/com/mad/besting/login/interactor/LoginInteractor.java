package com.mad.besting.login.interactor;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mad.besting.R;
import com.mad.besting.login.presenter.ILoginPresenter;

public class LoginInteractor implements ILonginInteractor{

    private ILoginPresenter mLoginPresenter;

    public LoginInteractor(ILoginPresenter iLoginPresenter) {
        mLoginPresenter = iLoginPresenter;
    }

    /**
     * Receives parameters to log in using Firebase Authentication
     * @param username
     * @param password
     * @param activity
     * @param firebaseAuth
     */
    @Override
    public void logIn(String username, String password, final Activity activity, FirebaseAuth firebaseAuth) {
        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = task.getResult().getUser();
                    SharedPreferences preferences = activity.getSharedPreferences("USER", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(activity.getString(R.string.email_word), user.getEmail());
                    editor.commit();
                    mLoginPresenter.loginSuccessful();
                }
                else{
                    mLoginPresenter.loginUnsuccessful(activity.getString(R.string.error_login));
                }
            }
        });

    }
}
