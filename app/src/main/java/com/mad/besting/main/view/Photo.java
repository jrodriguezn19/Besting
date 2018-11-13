package com.mad.besting.main.view;

/**
 * stores all details to be showed in the Card view
 */
public class Photo {

    private String mPhoto;
    private String mUsername;
    private String mTime;
    private String mNumberOfLikes;
    private String mNumberOfDislikes;

    public Photo(String photo, String username, String time, String numberOfLikes, String numberOfDislikes) {
        mPhoto              = photo;
        mUsername           = username;
        mTime               = time;
        mNumberOfLikes      = numberOfLikes;
        mNumberOfDislikes   = numberOfDislikes;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getNumberOfLikes() {
        return mNumberOfLikes;
    }

    public void setNumberOfLikes(String numberOfLikes) {
        mNumberOfLikes = numberOfLikes;
    }

    public String getNumberOfDislikes() {
        return mNumberOfDislikes;
    }

    public void setNumberOfDislikes(String numberOfDislikes) {
        mNumberOfDislikes = numberOfDislikes;
    }
}
