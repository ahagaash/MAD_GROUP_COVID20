package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Paydtl extends AppCompatActivity {

    EditText txtCardhold, txtCardno, txtExpire, txtcvv;
    RadioButton visa,master;
    DatabaseReference dbRef;
    CusPay cusPay;

    Button butDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paydlt);

        butDelete = findViewById(R.id.buttonPay);
        cusPay = new CusPay();
        visa = findViewById(R.id.visa);
        master = findViewById(R.id.master);
        txtCardhold = findViewById(R.id.hold);
        txtCardno = findViewById(R.id.cardnum);
        txtExpire = findViewById(R.id.date);
        txtcvv = findViewById(R.id.cvv);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Payment").child("cusPay1");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String p1 = visa.getText().toString();
                String p2 = master.getText().toString();

                if (dataSnapshot.hasChildren()) {
                    if (visa.isChecked()) {
                        cusPay.setPaymentMethod(p1);

                    } else {
                        cusPay.setPaymentMethod(p2);

                    }
                    cusPay.setCardHolder(txtCardhold.getText().toString());
                    cusPay.setCardNo(Integer.parseInt(txtCardno.getText().toString()));
                    cusPay.setExpire(txtExpire.getText().toString());
                    cusPay.setCvv(Integer.parseInt(txtcvv.getText().toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        butDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Payment").child("cusPay1");
                dbRef.removeValue();
                clearControls();
                Toast.makeText(getApplicationContext(), "sucessfully deleted", Toast.LENGTH_SHORT).show();
            }

            private void clearControls() {
                visa.setChecked(false);
                master.setChecked(false);
                txtCardhold.setText("");
                txtCardno.setText("");
                txtExpire.setText("");
                txtcvv.setText("");

            }

        });
    }
}
