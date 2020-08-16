package com.example.mycart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void add (View v){
        //display alert(popup) on screen
        Toast.makeText(this,"Cart is ready and order is ready to place", Toast.LENGTH_LONG).show();
    }



}