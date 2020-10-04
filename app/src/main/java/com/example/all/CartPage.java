package com.example.all;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CartPage extends AppCompatActivity {



    private Button butnn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);


        butnn=(Button)findViewById(R.id.button);

        butnn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                opennExtactivity();

            }


            public void opennExtactivity(){

                Intent i= new Intent(CartPage.this,OrderConfirmation.class);
                startActivity(i);
            }




        });




    }



    public void add (View v){
        //display alert(popup) on screen
        Toast.makeText(this,"Cart is ready and order is ready to place", Toast.LENGTH_LONG).show();
    }



}