package com.example.amithapkitchen;

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

public class Login extends AppCompatActivity {


    EditText email,password;
    Button logBtnn,backto;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        Intent intent = getIntent();
        Intent intent1=getIntent();


        email=findViewById(R.id.loginusername);
        password=findViewById(R.id.loginpass);
        fAuth=FirebaseAuth.getInstance();

        logBtnn=findViewById(R.id.signinbtn);
        backto=findViewById(R.id.backsignup);


        logBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=email.getText().toString().trim();
                String  Password=password.getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    email.setError("Please enter your email..");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    password.setError("Please enter your password");
                    return;
                }


             fAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()){

                         startActivity(new Intent(getApplicationContext(),CartPage.class));
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



    public void Log (View v){
        //display alert(popup) on screen
        Toast.makeText(this,"Logging",Toast.LENGTH_LONG).show();
    }


    public void reg (View v){
        //display alert(popup) on screen
        Toast.makeText(this,"Make your profile",Toast.LENGTH_LONG).show();
    }

}