package com.example.all;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback extends AppCompatActivity {

    EditText feedFullaname, feedMobile, feedEmail, feedReviews, rating1, rating2;

    Button Add;
    DatabaseReference reference;
    UserFeedback userfeedback;
    RatingBar ratingBar;
    RatingBar ratingBar1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedFullaname = findViewById(R.id.editTextTextPersonName);
        feedMobile = findViewById(R.id.editTextPhone);
        feedEmail = findViewById(R.id.editTextTextEmailAddress);
        feedReviews = findViewById(R.id.autoCompleteTextView);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar1 = findViewById(R.id.ratingBar3);

        Add = findViewById(R.id.buttonPay);
        userfeedback = new UserFeedback();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(feedback.this,"Stars:" +(int ) v , Toast.LENGTH_SHORT).show();
            }
        });
        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(feedback.this,"Stars:" +(int ) v , Toast.LENGTH_SHORT).show();
            }
        });


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference().child("FeedbackTable");


                try {
                    if (TextUtils.isEmpty(feedFullaname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(feedMobile.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty PhoneNumber", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(feedEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Contact Email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(feedReviews.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter the reviews", Toast.LENGTH_SHORT).show();

                    else {
                        userfeedback.setFullname(feedFullaname.getText().toString().trim());
                        userfeedback.setMobile(Integer.parseInt(feedMobile.getText().toString().trim()));
                        userfeedback.setEmail(feedEmail.getText().toString().trim());
                        userfeedback.setReviews(feedReviews.getText().toString().trim());

                        reference.push().setValue(userfeedback);
                        reference.child("userfeedback1").setValue(userfeedback);

                        Intent i = new Intent(getApplicationContext(), PopActivity.class);
                        startActivity(i);
                        clearControls();


                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }


            private void clearControls() {
                feedFullaname.setText("");
                feedMobile.setText("");
                feedEmail.setText("");
                feedReviews.setText("");

            }

        });


    }}