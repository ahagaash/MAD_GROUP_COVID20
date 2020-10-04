package com.example.all;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDeleteOrder extends AppCompatActivity {

    DatabaseReference dbRef;
    EditText txtdate,txttime,txtlocation,phone;
    Button updbtn,delbtn;
   // RadioGroup rdgrp;
    RadioButton apart,office,hme;
    orderclass oclass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_del_order);

        //rdgrp=(RadioGroup)findViewById(R.id.grpradio);
        apart=findViewById(R.id.radioButton7);
        office=findViewById(R.id.radioButton8);
        hme=findViewById(R.id.radioButton9);

        txtdate=findViewById(R.id.editTextDate);
        txttime=findViewById(R.id.editTextTime);
        txtlocation=findViewById(R.id.editTextTextMultiLine);
        phone=findViewById(R.id.editTextPhone);

        oclass= new orderclass();

        updbtn=(Button)findViewById(R.id.buttonorder2);
        delbtn=(Button)findViewById(R.id.buttonorder1);
        //db
        dbRef = FirebaseDatabase.getInstance().getReference().child("OrderConfirmation").child("order1");


        //show**********

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){



                    txtdate.setText(snapshot.child("ordate").getValue().toString());
                    txttime.setText(snapshot.child("ortime").getValue().toString());
                    txtlocation.setText(snapshot.child("orlocation").getValue().toString());
                    phone.setText(snapshot.child("orphoneno").getValue().toString());
                    //apart.setText(snapshot.child("oroption").getValue().toString());
                    //office.setText(snapshot.child("oroption").getValue().toString());
                   // hme.setText(snapshot.child("oroption").getValue().toString());



                }


                else
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//updatee**********

    updbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("OrderConfirmation");
            updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild("order1")){

                        //
                        String p1 = apart.getText().toString();
                        String p2 = office.getText().toString();
                        String p3= hme.getText().toString();

                        if(apart.isChecked()){
                            oclass.setOroption(p1);


                        }
                        else if (office.isChecked()){
                            oclass.setOroption(p2);

                        }
                        else {
                            oclass.setOroption(p3);
                        }

//

                        try {

                            oclass.setOrdate(txtdate.getText().toString().trim());
                            oclass.setOrtime(txttime.getText().toString().trim());
                            oclass.setOrlocation(txtlocation.getText().toString().trim());
                            oclass.setOrphoneno(Integer.parseInt(phone.getText().toString().trim()));

                            dbRef = FirebaseDatabase.getInstance().getReference().child("OrderConfirmation").child("order1");
                            dbRef.setValue(oclass);
                            clearControls();

                            Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();

                        } catch (NumberFormatException e){
                            Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_SHORT).show();

                        }

                    } else
                        Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    });


        ////////////delete*******************

delbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dbRef= FirebaseDatabase.getInstance().getReference().child("OrderConfirmation").child("order1");
        dbRef.removeValue();
        clearControls();
        Toast.makeText(getApplicationContext(),"sucessfully deleted",Toast.LENGTH_SHORT).show();





    }
});












    }
    private void clearControls(){

        apart.setChecked(false);
        office.setChecked(false);
        hme.setChecked(false);
        txtdate.setText("");
        txttime.setText("");
        txtlocation.setText("");
        phone.setText("");
    }


}