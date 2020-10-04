package com.example.all;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Payment extends AppCompatActivity {
    private Button butAdd;

    EditText txtCardhold, txtCardno, txtExpire, txtcvv;
    RadioButton visa,master;
    DatabaseReference dbRef;
    CusPay cusPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        butAdd = findViewById(R.id.buttonPay);
        cusPay = new CusPay();
        visa = findViewById(R.id.visa);
        master = findViewById(R.id.master);
        txtCardhold = findViewById(R.id.hold);
        txtCardno = findViewById(R.id.editTextTextPersonName4);
        txtExpire = findViewById(R.id.date);
        txtcvv = findViewById(R.id.cvv);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Payment");

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p1 = visa.getText().toString();
                String p2 = master.getText().toString();


                if (txtCardno.length()>16){

                    txtCardno.setError("card number must have length of 16");
                    return;
                }

                if (txtcvv.length()>3){

                    txtcvv.setError("cvv code must have length of 3");
                    return;
                }




                if(visa.isChecked()){
                    cusPay.setPaymentMethod(p1);


                }
                else {
                    cusPay.setPaymentMethod(p2);

                }
                cusPay.setCardHolder(txtCardhold.getText().toString());
                cusPay.setCardNo(txtCardno.getText().toString());
                cusPay.setExpire(txtExpire.getText().toString());
                cusPay.setCvv(Integer.parseInt(txtcvv.getText().toString()));

                dbRef.push().setValue(cusPay);
                dbRef.child("cusPay1").setValue(cusPay);

                Intent intent = new Intent(Payment.this, PaySuccess.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_SHORT).show();
                clearControls();
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