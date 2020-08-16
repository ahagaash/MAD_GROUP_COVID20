package com.example.address;

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


    public void add1 (View v){
        //display alert(popup) on screen
        Toast.makeText(this,"Location details placed",Toast.LENGTH_LONG).show();
    }

}