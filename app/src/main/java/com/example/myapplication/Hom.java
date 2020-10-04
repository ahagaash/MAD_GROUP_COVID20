package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Hom extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hom);


            b1 = (Button) findViewById(R.id.button4);
            b2 = (Button) findViewById(R.id.button5);
            b3 = (Button) findViewById(R.id.button2);
            b4 = (Button) findViewById(R.id.button3);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openActivity2();
                }

                public void openActivity2() {
                    Intent intent = new Intent(Hom.this, ContactUs.class);
                    startActivity(intent);
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    openActivity3();
                }

                public void openActivity3() {
                    Intent intent1 = new Intent(Hom.this, Feed.class);
                    startActivity(intent1);
                }
            });

           // b3.setOnClickListener(new View.OnClickListener() {
               // @Override
                //public void onClick(View view2) {
                    //openActivity4();
                //}

               // public void openActivity4() {
                    //Intent intent2 = new Intent(MainActivity.this, GooglePlay.class);
                    //startActivity(intent2);

               // }
           // });

           // b4.setOnClickListener(new View.OnClickListener() {
               // @Override
               // public void onClick(View view2) {
                   // openActivity5();
               // }
               // public void openActivity5(){
                   // Intent intent3 = new Intent(MainActivity.this, GooglePlay.class);
                   // startActivity(intent3);
               // }
            //});
        }
    }