package com.example.all;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginPage extends AppCompatActivity {

    EditText ademail,adpassword;
    Button adlogBtnn,adbackto;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);



        ademail=findViewById(R.id.adminusername);
        adpassword=findViewById(R.id.adminloginpass);
        fAuth=FirebaseAuth.getInstance();

        adlogBtnn=findViewById(R.id.adminsigninbtn);
        adbackto=findViewById(R.id.adminbacksignup);


        adlogBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String AdEmail=ademail.getText().toString().trim();
                String  AdPassword=adpassword.getText().toString().trim();

                if(TextUtils.isEmpty(AdEmail)){
                    ademail.setError("Please enter your admin email..");
                    return;
                }
                if(TextUtils.isEmpty(AdPassword)){
                    adpassword.setError("Please enter your admin password");
                    return;
                }



                fAuth.signInWithEmailAndPassword(AdEmail,AdPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            startActivity(new Intent(getApplicationContext(),ProfilePage.class));
                            Toast.makeText(getApplicationContext(), "login successful", Toast.LENGTH_SHORT).show();

                        }

                        else{
                            Toast.makeText(getApplicationContext(),"login Unsuccessful",Toast.LENGTH_SHORT).show();
                        }

                    }
                });








            }
        });



    }
}