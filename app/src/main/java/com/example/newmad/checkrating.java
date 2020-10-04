package com.example.newmad;

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

import com.example.newmad.PopActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class checkrating extends AppCompatActivity {

    EditText feedFullaname, feedMobile, feedEmail, feedReviews, rating1, rating2;
    TextView textView5, showRating;
    Button Add;
    DatabaseReference reference;

    RatingBar ratingBar;
    RatingBar ratingBar1;
    float rateValue;String temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkrating);


        textView5 = findViewById(R.id.textView5);


        feedFullaname = findViewById(R.id.editTextTextPersonName);
        feedMobile = findViewById(R.id.editTextPhone);
        feedEmail = findViewById(R.id.editTextTextEmailAddress);
        feedReviews = findViewById(R.id.autoCompleteTextView);
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar1 = findViewById(R.id.ratingBar3);


        Add = findViewById(R.id.button);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = ratingBar.getRating();
                if(rateValue<=1 && rateValue>0)
                    textView5.setText("Bad" + rateValue + "/5");
                else if(rateValue<=2 && rateValue>1)
                    textView5.setText("OK" + rateValue + "/5");
                else if(rateValue<=3 && rateValue>2)
                    textView5.setText("Good" + rateValue + "/5");
                else if(rateValue<=4 && rateValue>3)
                    textView5.setText("VeryGood" + rateValue + "/5");
                else if(rateValue<=5 && rateValue>4)
                    textView5.setText("Best" + rateValue + "/5");

            }
        });



        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                temp = textView5.getText().toString();
                feedReviews.setText("Your Rating :" + temp + "\n" + feedReviews.getText());
                feedReviews.setText("");
                ratingBar.setRating(0);
                textView5.setText("");
                Intent i = new Intent(getApplicationContext(), Displaycomments.class);
                startActivity(i);
                Toast.makeText(checkrating.this, "Stars:" + (int)ratingBar.getRating(), Toast.LENGTH_SHORT ).show();
            }

        });




    }}