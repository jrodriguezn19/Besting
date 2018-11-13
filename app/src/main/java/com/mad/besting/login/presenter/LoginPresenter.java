package com.mad.besting.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.mad.besting.login.interactor.ILonginInteractor;
import com.mad.besting.login.interactor.LoginInteractor;
import com.mad.besting.login.view.ILoginView;

/**
 * bridge between the UI and model business
 */

public class LoginPresenter implements ILoginPresenter{
    private ILoginView mLoginView;
    private ILonginInteractor mLonginInteractor;

    public LoginPresenter(ILoginView iLoginView) {
        mLoginView = iLoginView;
        mLonginInteractor = new LoginInteractor(this);
    }
    /**
     * Login Firebase
     */
    @Override
    public void logIn(String username, String password, Activity activity, FirebaseAuth firebaseAuth) {
        mLoginView.disableInput();
        mLoginView.showLoginProgressBar();
        mLonginInteractor.logIn(username, password, activity, firebaseAuth);
    }
    /**
     * starts the home activity, enable edit text fields and hide progress bar
     */
    @Override
    public void loginSuccessful() {
        mLoginView.goToHome();
        mLoginView.enableInput();
        mLoginView.hideLoginProgressBar();
    }
    /**
     * enables edit text fields, hide progress bar and show error
     */
    @Override
    public void loginUnsuccessful(String error) {
        mLoginView.enableInput();
        mLoginView.hideLoginProgressBar();
        mLoginView.loginUnsuccessful(error);
    }
}
