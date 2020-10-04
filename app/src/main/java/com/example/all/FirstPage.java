package com.example.all;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstPage extends AppCompatActivity {


    private Button button,btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);



        button =(Button) findViewById(R.id.button2);
        btn=(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity22();


            }


            public void openActivity22(){

                Intent intent = new Intent(FirstPage.this,Login.class);
                startActivity(intent);
            }


        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity23();
            }


            public void openActivity23(){

                Intent intentu = new Intent(FirstPage.this,Signup.class);
                startActivity(intentu);
            }

        });



    }















}