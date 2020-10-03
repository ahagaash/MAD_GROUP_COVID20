package com.example.amithapkitchen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText fnamez,addrez,emailz,contact,pass,conpass;

    FirebaseAuth fAuth;
    Button regBtn;
  //  ProgressBar progressBar;
    DatabaseReference dbRef;
    Signupclass signupclass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

       // Intent intent = getIntent();

        fnamez=findViewById(R.id.editTextTextPersonName);
        addrez=findViewById(R.id.editTextTextPersonName2);
        emailz=findViewById(R.id.editTextTextEmailAddress);
        contact=findViewById(R.id.editTextPhone);
        pass=findViewById(R.id.editTextTextPassword);
        conpass=findViewById(R.id.editTextTextPassword2);
      //  progressBar=findViewById(R.id.progressBar2);

        signupclass=new Signupclass();


        regBtn=findViewById(R.id.buttonsu);

        fAuth=FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),Login.class));
        }


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String suname=fnamez.getText().toString().trim();
                final String suaddress=addrez.getText().toString().trim();
                final String suemail=emailz.getText().toString().trim();
                final String suphone=contact.getText().toString().trim();
                final String supassw=pass.getText().toString().trim();
                final String suconpassw=conpass.getText().toString().trim();


                if (TextUtils.isEmpty(suname)){
                    fnamez.setError("please enter your name!!!");
                    return;
                }

                if (TextUtils.isEmpty(suaddress)){
                    addrez.setError("please enter your address!!");
                    return;
                }

                if (TextUtils.isEmpty(suemail)){
                    emailz.setError("please enter your email!!");
                    return;
                }

                if (TextUtils.isEmpty(suphone)){
                    contact.setError("please enter your contact number!!");
                    return;
                }

                if (TextUtils.isEmpty(supassw)){
                    pass.setError("please enter your password");
                    return;
                }

                if (TextUtils.isEmpty(suconpassw)){
                    conpass.setError("please confirm your password");
                    return;
                }

                if (!supassw.equals(suconpassw)){
                    pass.setError("password dont tally!!");
                    return;
                }

                if (supassw.length()<8){

                    pass.setError("Password must have length at least 8");
                    return;
                }


               // progressBar.setVisibility(view.VISIBLE);

                dbRef= FirebaseDatabase.getInstance().getReference().child("Register");

                fAuth.createUserWithEmailAndPassword(suemail,supassw).addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        signupclass.setId(fAuth.getCurrentUser().getUid());
                        signupclass.setSuname(suname);
                        signupclass.setSuaddress(suaddress);
                        signupclass.setSuemail(suemail);
                        signupclass.setSuphone(Integer.parseInt(suphone));
                        signupclass.setSupassw(supassw);
                        signupclass.setSuconpassw(suconpassw);



                        dbRef.child(String.valueOf(signupclass.getId())).setValue(signupclass);


                        Toast.makeText(getApplicationContext(),"registration successful", Toast.LENGTH_SHORT).show();


                        ////////////add dialog box



                        AlertDialog.Builder builder =new AlertDialog.Builder(Signup.this);

                        builder.setCancelable(true);

                        builder.setTitle("Welcome");
                        builder.setMessage("You have Successfully Registered");
///////////intent

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent packageContent = null;
                                Intent intent1= new Intent(Signup.this,Login.class);
                                startActivity(intent1);
                            }
                        });

                        builder.show();

////////////////////////////////////////////
                    }

                    else {

                        Toast.makeText(getApplicationContext(),"registration is unsuccessful",Toast.LENGTH_SHORT).show();

                    }

                    }
                });

            }
        });


    }

}