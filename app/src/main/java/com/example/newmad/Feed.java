package com.example.newmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URL;

public class Feed extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed);
        b1 = (Button) findViewById(R.id.button4);
        b2 = (Button) findViewById(R.id.button5);
        b3 = (Button) findViewById(R.id.button6);
        b4 = (Button) findViewById(R.id.button7);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }

            public void openActivity2() {
                Intent intent = new Intent(Feed.this, Help.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                openActivity3();
            }

            public void openActivity3() {
                Intent intent1 = new Intent(Feed.this, FeedBack.class);
                startActivity(intent1);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                openActivity4();
            }

            public void openActivity4() {
                Intent intent2 = new Intent(Feed.this, GooglePlay.class);
                startActivity(intent2);

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                openActivity5();
            }
        public void openActivity5(){
            Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pg/Amithap-Kitchen-103587208183277/community/"));
            startActivity(intent3);
        }
    });
    }}

