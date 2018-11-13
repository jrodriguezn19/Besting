package com.mad.besting.main.view;

import android.view.View;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Interface for the home fragment
 */
public interface IHomeFragment {

    void takePhoto();

    ArrayList<Photo> buildPhotos();

    File createFilePhoto() throws IOException;

    void showToolbar(String title, boolean upButton, View view);
}
