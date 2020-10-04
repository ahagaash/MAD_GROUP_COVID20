package com.example.all;



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

public class Paydlt extends AppCompatActivity {

    EditText txtCardhold, txtCardno, txtExpire, txtcvv;
    RadioButton visa,master;
    DatabaseReference dbRef;
    CusPay cusPay;

    Button butDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                if (dataSnapshot.hasChildren()) {
                    txtCardhold.setText(dataSnapshot.child("cardHolder").getValue().toString());
                    txtCardno.setText(dataSnapshot.child("cardNo").getValue().toString());
                    txtExpire.setText(dataSnapshot.child("expire").getValue().toString());
                    txtcvv.setText(dataSnapshot.child("cvv").getValue().toString());

                } else
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
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
                txtCardhold.setText("");
                txtCardno.setText("");
                txtExpire.setText("");
                txtcvv.setText("");

            }
        });
    }
}
