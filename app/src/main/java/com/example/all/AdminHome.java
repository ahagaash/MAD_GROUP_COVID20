package com.example.all;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminHome extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_hom);

        b1 = (Button) findViewById(R.id.button16);
        b2 = (Button) findViewById(R.id.button17);
        b3 = (Button) findViewById(R.id.button11);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }

            public void openActivity2() {
                Intent intent = new Intent(AdminHome.this, DriverDetails.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                openActivity3();
            }

            public void openActivity3() {
                Intent intent1 = new Intent(AdminHome.this, AssignOrder.class);
                startActivity(intent1);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                openActivity3();
            }

            public void openActivity3() {
                Intent intent1 = new Intent(AdminHome.this, MainActivity.class);
                startActivity(intent1);
            }
        });


    }
}
