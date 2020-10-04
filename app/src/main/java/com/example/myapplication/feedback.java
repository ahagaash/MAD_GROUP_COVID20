package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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

    EditText feedFullaname, feedMobile, feedEmail, feedReviews;
    Button Add;
    DatabaseReference reference;
    UserFeedback userfeedback;
    RatingBar ratingBar;
    TextView txtRatingValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedFullaname = findViewById(R.id.editTextTextPersonName);
        feedMobile = findViewById(R.id.editTextPhone);
        feedEmail = findViewById(R.id.editTextTextEmailAddress);
        feedReviews = findViewById(R.id.autoCompleteTextView);

        Add = findViewById(R.id.buttonPay);

        userfeedback = new UserFeedback();


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference().child("FeedbackTable");

                try {
                    if (TextUtils.isEmpty(feedFullaname.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(feedMobile.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty ID", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(feedEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Contact No", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(feedReviews.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Bike No", Toast.LENGTH_SHORT).show();
                    else {
                        userfeedback.setFullname(feedFullaname.getText().toString().trim());
                        userfeedback.setMobile(Integer.parseInt(feedMobile.getText().toString().trim()));
                        userfeedback.setEmail(feedEmail.getText().toString().trim());
                        userfeedback.setReviews(feedReviews.getText().toString().trim());

                        reference.push().setValue(userfeedback);
                        reference.child("userfeedback1").setValue(userfeedback);


                        clearControls();

                        addListenerOnRatingBar();
                        addListenerOnButton();
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            private void addListenerOnButton() {
                ratingBar = (RatingBar) findViewById(R.id.ratingBar);
                Add = (Button) findViewById(R.id.button);

                //if click on me, then display the current rating value.
                Add.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Toast.makeText(feedback.this,
                                String.valueOf(ratingBar.getRating()),
                                Toast.LENGTH_SHORT).show();

                    }


                });

            }

            private void clearControls() {
                feedFullaname.setText("");
                feedMobile.setText("");
                feedEmail.setText("");
                feedReviews.setText("");
            }


            public void addListenerOnRatingBar() {

                ratingBar = (RatingBar) findViewById(R.id.ratingBar);


                //if rating value is changed,
                //display the current rating value in the result (textview) automatically
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    public void onRatingChanged(RatingBar ratingBar, float rating,
                                                boolean fromUser) {

                        txtRatingValue.setText(String.valueOf(rating));

                    }
                });
            }

        });
    }
}