package com.mad.besting.main.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.mad.besting.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        showToolbar("",false, view);

        RecyclerView mPhotoRecyclerView = view.findViewById(R.id.profile_fragment_Recycler_View);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mPhotoRecyclerView.setLayoutManager(mLinearLayoutManager);

        PhotoAdapterRecyclerView mPhotoAdapterRecyclerView = new PhotoAdapterRecyclerView(buildPhotos(), R.layout.cardview_photo, getActivity());

        mPhotoRecyclerView.setAdapter(mPhotoAdapterRecyclerView);


        return view;
    }

    /**
     * builds and array of photo objects
     */
    public ArrayList<Photo> buildPhotos(){
        ArrayList<Photo> mPhotos = new ArrayList<>();
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog1.jpeg?alt=media&token=06bf3b0f-17fb-4306-9126-f44428e6750f", "George Rodriguez", "4 days", "3 likes", "0 dislikes"));
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog2.jpeg?alt=media&token=c1ca9028-aff3-49b6-82eb-2e05186366de", "George Rodriguez", "2 days", "7 likes", "2 dislikes"));
        mPhotos.add(new Photo("https://firebasestorage.googleapis.com/v0/b/besting-bfcac.appspot.com/o/postedPhotos%2Fdog3.jpeg?alt=media&token=c890122c-032f-4531-b7c1-f4e23904144e", "George Rodriguez", "23 days", "1 likes", "0 dislikes"));
        return mPhotos;
    }
    /**
     * shows the tool bar
     */
    public void showToolbar(String title, boolean upButton, View view){
        android.support.v7.widget.Toolbar mToolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);


    }

}
