package com.example.all;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

;
import java.util.Calendar;

public class OrderConfirmation extends AppCompatActivity {

    DatabaseReference dbRef;
    EditText txtdate,txttime,txtlocation,phone;
    Button orbtn;
    //RadioGroup rdgrp;
    RadioButton apart,office,hme;
    orderclass oclass;


    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confirmation);

       // rdgrp=(RadioGroup)findViewById(R.id.grpradio);
        apart=findViewById(R.id.radioButton7);
        office=findViewById(R.id.radioButton8);
        hme=findViewById(R.id.radioButton9);

        txtdate=findViewById(R.id.editTextDate);
        txttime=findViewById(R.id.editTextTime);
        txtlocation=findViewById(R.id.editTextTextMultiLine);
        phone=findViewById(R.id.editTextPhone);

        orbtn=(Button)findViewById(R.id.buttonorder);

       oclass= new orderclass();

        Intent i = getIntent();

        //date pickerrrrrrrrrr


        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (v == txtdate) {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(OrderConfirmation.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    txtdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();


                }
            }
        });

        //pick up time


        txttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (v == txttime) {

                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(OrderConfirmation.this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    txttime.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, false);
                    timePickerDialog.show();
                }




            }
        });







        //end









        orbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("OrderConfirmation");
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

                    if(TextUtils.isEmpty(txtdate.getText().toString()))
                          Toast.makeText(getApplicationContext(),"date is empty",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txttime.getText().toString()))
                        Toast.makeText(getApplicationContext(),"time is empty",Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(txtlocation.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Location is empty",Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(phone.getText().toString()))
                        Toast.makeText(getApplicationContext(),"phone is empty",Toast.LENGTH_SHORT).show();

                    else {

                        oclass.setOrdate(txtdate.getText().toString().trim());
                        oclass.setOrtime(txttime.getText().toString().trim());
                        oclass.setOrlocation(txtlocation.getText().toString().trim());
                        oclass.setOrphoneno(Integer.parseInt(phone.getText().toString().trim()));

                        dbRef.push().setValue(oclass);
                        dbRef.child("order1").setValue(oclass);



                        Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OrderConfirmation.this, Payment.class);
                        startActivity(intent);
                        clearControls();
                    }
                }catch (NumberFormatException e){

                    e.printStackTrace();
                }


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



    public void add1 (View v){
        //display alert(popup) on screen
        Toast.makeText(this,"Location details placed",Toast.LENGTH_LONG).show();
       // startActivity(new Intent(this,Payment.class));
    }

}