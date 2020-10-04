package com.example.all;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
    }

    public void ordercomfirmation(View view) {

        Intent intent = new Intent(ProfilePage.this, UpdateDeleteOrder.class);
        startActivity(intent);
    }

    public void paymentHistory(View view) {
        Intent intent = new Intent(ProfilePage.this, Paydlt.class);
        startActivity(intent);
    }

    public void Feedbackpage(View view) {
        Intent intent = new Intent(ProfilePage.this, EditResponse.class);
        startActivity(intent);
    }

    public void Logout(View view) {
        Intent intent = new Intent(ProfilePage.this, Login.class);
        startActivity(intent);


    }
}