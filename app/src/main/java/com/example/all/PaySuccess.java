package com.example.all;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PaySuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
    }

    public void nextpage(View view) {

        Intent intent = new Intent(PaySuccess.this, Hom.class);
        startActivity(intent);
    }
}