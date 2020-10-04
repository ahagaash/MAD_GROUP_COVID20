package com.example.myapplication;

import android.widget.RatingBar;
import android.widget.TextView;

public class UserFeedback {
    String fullname;
    Integer mobile;
    String email;
    String reviews;

    public float getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(float ratingBar) {
        this.ratingBar = ratingBar;
    }

    public String getTxtRatingValue() {
        return txtRatingValue;
    }

    public void setTxtRatingValue(String txtRatingValue) {
        this.txtRatingValue = txtRatingValue;
    }

    float ratingBar;
   String txtRatingValue;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}