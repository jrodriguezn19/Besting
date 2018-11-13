package com.mad.besting.login.view;


import android.view.View;

/**
 * Interface Login View
 */
public interface ILoginView {

    void enableInput();
    void disableInput();

    void showLoginProgressBar();
    void hideLoginProgressBar();

    void loginUnsuccessful(String error);

    void goToCreateAccount(View view);
    void goToHome();
}
