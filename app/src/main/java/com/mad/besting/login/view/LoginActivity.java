package com.mad.besting.login.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mad.besting.R;
import com.mad.besting.main.view.ContainerActivity;
import com.mad.besting.login.presenter.LoginPresenter;

/**
 * Controls Login Activity
 */
public class LoginActivity extends AppCompatActivity implements ILoginView{

    private TextInputEditText mUsernameET, mPasswordET;
    private Button mLoginBt;
    private ProgressBar mProgressBarLogin;
    private LoginPresenter mLoginPresenter;

    private static final String TAG = "LoginRepository";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Log.w(TAG, getString(R.string.user_logged_in) + firebaseUser.getEmail());
                }
                else{
                    Log.w(TAG, getString(R.string.user_not_logged_in));
                }
            }
        };

        TextView appNameTV = findViewById(R.id.title_tv);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Confetti Stream.ttf");
        appNameTV.setTypeface(typeface);

        mUsernameET = findViewById(R.id.username_et);
        mPasswordET = findViewById(R.id.password_et);
        mLoginBt    = findViewById(R.id.login_bt);
        mProgressBarLogin = findViewById(R.id.progress_bar_login);
        hideLoginProgressBar();

        mLoginPresenter = new LoginPresenter(this);

        mLoginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((TextUtils.isEmpty(mUsernameET.getText().toString())) || (TextUtils.isEmpty(mPasswordET.getText().toString()))){
                    Toast.makeText(LoginActivity.this, R.string.validate_username_password, Toast.LENGTH_LONG).show();
                }
                else{
                    logIn(mUsernameET.getText().toString(), mPasswordET.getText().toString());
                }
            }
        });


    }

    private void logIn(String username, String password) {
        mLoginPresenter.logIn(username, password, this, mFirebaseAuth);
    }

    /**
     * enables input fields of the login
     */

    @Override
    public void enableInput() {
        mUsernameET.setEnabled(true);
        mPasswordET.setEnabled(true);
        mLoginBt.setEnabled(true);

    }
    /**
     * disables input fields of the login
     */
    @Override
    public void disableInput() {
        mUsernameET.setEnabled(false);
        mPasswordET.setEnabled(false);
        mLoginBt.setEnabled(false);

    }

    /**
     * show progress bar
     */
    @Override
    public void showLoginProgressBar() {
        mProgressBarLogin.setVisibility(View.VISIBLE);
    }
    /**
     * hide progress bar
     */
    @Override
    public void hideLoginProgressBar() {
        mProgressBarLogin.setVisibility(View.GONE);
    }

    /**
     * show error when login unsuccessful
     */
    @Override
    public void loginUnsuccessful(String error) {
        Toast.makeText(this, getString(R.string.error_found__) + error, Toast.LENGTH_LONG).show();

    }
    /**
     * start create account Activity
     */
    @Override
    public void goToCreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    /**
     * start home screen of the app
     */
    @Override
    public void goToHome() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }
}
