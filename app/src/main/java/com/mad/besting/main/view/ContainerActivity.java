package com.mad.besting.main.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mad.besting.login.view.LoginActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import com.mad.besting.R;


/**
 * Creates the container of fragments for the home screen
 */
public class ContainerActivity extends AppCompatActivity {

    private static final String TAG = "ContainerActivity";
    HomeFragment mHomeFragment;
    ProfileFragment mProfileFragment;
    SearchFragment mSearchFragment;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        fireBaseInit();
        BottomBar bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setDefaultTab(R.id.home_tab);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId){
                    case R.id.home_tab:
                        HomeFragment homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame_layout, homeFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;

                    case  R.id.profile_tab:
                        ProfileFragment profileFragment = new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame_layout, profileFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;

                    case R.id.search_tab:
                        SearchFragment searchFragment = new SearchFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_frame_layout, searchFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                        break;
                }
            }
        });
    }

    /**
     * Initializes the Firebase login module
     */
    private void fireBaseInit(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Log.w(TAG, "User Logged in " + firebaseUser.getEmail());
                }
                else{
                    Log.w(TAG, "User NOT Logged in ");
                }
            }
        };
    }
    /**
     * creates menu to sign out option
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;

    }
    /**
     * adds sing out option to the menu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_sign_out:
                mFirebaseAuth.signOut();

                Toast.makeText(this,"Closed Session",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ContainerActivity.this, LoginActivity.class);
                startActivity(intent);

                break;

            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
