package com.example.sampleloginformad;

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



    public void Log (View v){
        //display alert(popup) on screen
        Toast.makeText(this,"Logging",Toast.LENGTH_LONG).show();
    }


    public void reg (View v){
        //display alert(popup) on screen
        Toast.makeText(this,"Make your profile",Toast.LENGTH_LONG).show();
    }

}