package com.example.all;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class help extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hlp);

        b1 = (Button) findViewById(R.id.button9);
        b2 = (Button) findViewById(R.id.button10);
        b3 = (Button) findViewById(R.id.button12);
        b4 = (Button) findViewById(R.id.button13);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
            public void openActivity2() {
                Intent intent = new Intent(help.this, Question.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                openActivity3();}
            public void openActivity3(){
                Intent intent1 = new Intent(help.this,Question.class);
                startActivity(intent1);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                openActivity4();}
            public void openActivity4(){
                Intent intent2 = new Intent(help.this,Question.class);
                startActivity(intent2);

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                openActivity5();}
            public void openActivity5(){
                Intent intent3 = new Intent(help.this,Question.class);
                startActivity(intent3);

            }
        });
    }
}
