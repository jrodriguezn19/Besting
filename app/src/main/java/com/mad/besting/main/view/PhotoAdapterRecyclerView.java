package com.mad.besting.main.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.mad.besting.R;
/**
 * starts the home activity, enable edit text fields and hide progress bar
 */
public class PhotoAdapterRecyclerView extends RecyclerView.Adapter<PhotoAdapterRecyclerView.PhotoViewHolder>{

    private static final int DURATION_EXPLODE_ANIMATION = 1500;
    private ArrayList<Photo> mPhotos;
    private int mResource;
    private Activity mActivity;

    public PhotoAdapterRecyclerView(ArrayList<Photo> photos, int resource, Activity activity) {
        mPhotos = photos;
        mResource = resource;
        mActivity = activity;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(mResource, parent,false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo mPhoto = mPhotos.get(position);
        holder.mUsernameCard.setText(mPhoto.getUsername());
        holder.mTimeCard.setText(mPhoto.getTime());
        holder.mNumberOfLikesCard.setText(mPhoto.getNumberOfLikes());
        holder.mNumberOfDislikesCard.setText(mPhoto.getNumberOfDislikes());
        Picasso.get().load(mPhoto.getPhoto()).into(holder.mPhotoCard);

        holder.mPhotoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, PhotoDetailActivity.class);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(DURATION_EXPLODE_ANIMATION);
                    mActivity.getWindow().setEnterTransition(explode);
                    mActivity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, v, mActivity.getString(R.string.transition_name_to_photo)).toBundle());
                }
                else{
                    mActivity.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder{

        private ImageView mPhotoCard;
        private TextView mUsernameCard;
        private TextView mTimeCard;
        private TextView mNumberOfLikesCard;
        private TextView mNumberOfDislikesCard;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            mPhotoCard = itemView.findViewById(R.id.photo_IV);
            mUsernameCard = itemView.findViewById(R.id.username_card_view_tv);
            mTimeCard = itemView.findViewById(R.id.timeCard_tv);
            mNumberOfLikesCard = itemView.findViewById(R.id.number_of_likes_tv);
            mNumberOfDislikesCard = itemView.findViewById(R.id.number_of_dislikes_tv);
        }
    }
}
