package com.example.all;

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

public class AssignOrder extends AppCompatActivity {
    EditText txtOrderNo, txtName, txtID, txtBikeNo;
    Button butAdd, butShow;
    DatabaseReference dbRef;
    Orders orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_order);

        txtOrderNo = findViewById(R.id.ordNo);
        txtName = findViewById(R.id.drNam);
        txtID = findViewById(R.id.drID);
        txtBikeNo = findViewById(R.id.bikeNo);

        butAdd = findViewById(R.id.ordAdd);
        butShow = findViewById(R.id.ordSho);

        orders = new Orders();

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Orders");

                try {
                    if (TextUtils.isEmpty(txtOrderNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Order Number", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtID.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty ID", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtBikeNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Bike No", Toast.LENGTH_SHORT).show();
                    else {
                        orders.setOrdNo(txtOrderNo.getText().toString().trim());
                        orders.setName(txtName.getText().toString().trim());
                        orders.setId(txtID.getText().toString().trim());
                        orders.setBike(txtBikeNo.getText().toString().trim());

                        dbRef.push().setValue(orders);
                        dbRef.child("orders1").setValue(orders);
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
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Orders/orders1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            txtOrderNo.setText(dataSnapshot.child("ordNo").getValue().toString());
                            txtName.setText(dataSnapshot.child("name").getValue().toString());
                            txtID.setText(dataSnapshot.child("id").getValue().toString());
                            txtBikeNo.setText(dataSnapshot.child("bike").getValue().toString());
                        } else
                            Toast.makeText(getApplicationContext(), "Cannot find orders", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    private void clearControls() {
        txtOrderNo.setText("");
        txtName.setText("");
        txtID.setText("");
        txtBikeNo.setText("");
    }
}