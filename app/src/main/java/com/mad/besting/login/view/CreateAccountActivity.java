package com.mad.besting.login.view;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mad.besting.R;

/**
 * Creates Account making use of Firebase Authentication
 */
public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private Button mJoinBestingBT;
    private TextInputEditText mEmailTI, mPasswordTI;
    private ProgressBar mProgressBarCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.tool_bar_title), true);

        mJoinBestingBT = findViewById(R.id.join_besting_bt);
        mEmailTI = findViewById(R.id.email_create_account_TI);
        mPasswordTI = findViewById(R.id.password_create_account_TI);
        mProgressBarCreateAccount = findViewById(R.id.progress_bar_create_Account);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mProgressBarCreateAccount.setVisibility(View.GONE);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Log.w(TAG, getString(R.string.user_logged_in_) + firebaseUser.getEmail());
                }
                else{
                    Log.w(TAG, getString(R.string.user_not_loggen_in));
                }
            }
        };

        mJoinBestingBT.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                newAccount();
            }
        });

    }

    /**
     * Shows Toolbar on Create Account Activity
     * @param title
     * @param upButton
     */
    public void showToolbar(String title, boolean upButton){
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
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


    /**
     * Creates new account using Firebase Authentication
     */
    private void newAccount() {
        if(TextUtils.isEmpty(mEmailTI.getText().toString()) || TextUtils.isEmpty(mPasswordTI.getText().toString())){
            Toast.makeText(CreateAccountActivity.this, R.string.validate_email_password, Toast.LENGTH_LONG).show();
        }
        else{
            mProgressBarCreateAccount.setVisibility(View.VISIBLE);
            String email = mEmailTI.getText().toString();
            String password = mPasswordTI.getText().toString();

            mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    mProgressBarCreateAccount.setVisibility(View.GONE);
                    if (task.isSuccessful()){
                        Toast.makeText(CreateAccountActivity.this, R.string.account_created,Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(CreateAccountActivity.this, R.string.validate_email_password, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }





    }
}
