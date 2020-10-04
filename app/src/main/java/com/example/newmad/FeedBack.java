package com.example.newmad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedBack extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.complaints.MESSAGE";


    RatingBar ratingBar;
    RatingBar ratingBar1;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar3);
        button = (Button) findViewById(R.id.button);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(FeedBack.this,"Stars:" +(int ) v , Toast.LENGTH_SHORT).show();
            }
        });
        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(FeedBack.this,"Stars:" +(int ) v , Toast.LENGTH_SHORT).show();
            }
        });

    }

}