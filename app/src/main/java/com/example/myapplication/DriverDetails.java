package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverDetails extends AppCompatActivity {

    EditText txtName, txtID, txtConNo, txtBikeNo;
    Button butAdd, butShow, butUpdate, butDelete;
    DatabaseReference dbRef;
    Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivr_details);

        txtName = findViewById(R.id.editName);
        txtID = findViewById(R.id.editID);
        txtConNo = findViewById(R.id.editNo);
        txtBikeNo = findViewById(R.id.editBike);

        butAdd = findViewById(R.id.BtnAdd);
        butShow = findViewById(R.id.ordAdd);
        butUpdate = findViewById(R.id.button7);
        butDelete = findViewById(R.id.button8);

        driver = new Driver();

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Driver");

                try {
                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtID.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty ID", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtConNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Contact No", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtBikeNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Bike No", Toast.LENGTH_SHORT).show();
                    else {
                        driver.setName(txtName.getText().toString().trim());
                        driver.setId(txtID.getText().toString().trim());
                        driver.setContact(Integer.parseInt(txtConNo.getText().toString().trim()));
                        driver.setBikeNo(txtBikeNo.getText().toString().trim());

                        dbRef.push().setValue(driver);
                        dbRef.child("driver1").setValue(driver);
                        Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        butShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Driver/driver1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            txtName.setText(dataSnapshot.child("name").getValue().toString());
                            txtID.setText(dataSnapshot.child("id").getValue().toString());
                            txtConNo.setText(dataSnapshot.child("contact").getValue().toString());
                            txtBikeNo.setText(dataSnapshot.child("bikeNo").getValue().toString());
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Cannot find driver1", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        butUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Driver");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("driver1")){
                            try{
                                driver.setName(txtName.getText().toString().trim());
                                driver.setId(txtID.getText().toString().trim());
                                driver.setContact(Integer.parseInt(txtConNo.getText().toString().trim()));
                                driver.setBikeNo(txtBikeNo.getText().toString().trim());

                                dbRef = FirebaseDatabase.getInstance().getReference().child("Driver").child("driver1");
                                dbRef.setValue(driver);
                                clearControls();

                                Toast.makeText(getApplicationContext(),"Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            }
                            catch(NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid Contact No", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No source to update", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        butDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Driver").child("driver1");
                dbRef.removeValue();
                clearControls();
                Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearControls() {
        txtName.setText("");
        txtID.setText("");
        txtConNo.setText("");
        txtBikeNo.setText("");
    }
}

