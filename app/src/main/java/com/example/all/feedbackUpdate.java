package com.example.all;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class feedbackUpdate extends AppCompatActivity {
    EditText feedFullaname, feedMobile, feedEmail, feedReviews;
    Button butUpdate,butDelete;

    DatabaseReference reference;
    UserFeedback userfeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_update);

        feedFullaname = findViewById(R.id.editTextTextPersonName);
        feedMobile = findViewById(R.id.editTextPhone);
        feedEmail = findViewById(R.id.editTextTextEmailAddress);
        feedReviews = findViewById(R.id.autoCompleteTextView);

        butUpdate = findViewById(R.id.button9);
        butDelete = findViewById(R.id.button10);

        userfeedback = new UserFeedback();

        reference = FirebaseDatabase.getInstance().getReference().child("FeedbackTable").child("userfeedback1");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    feedFullaname.setText(dataSnapshot.child("fullname").getValue().toString());
                    feedMobile.setText(dataSnapshot.child("mobile").getValue().toString());
                    feedEmail.setText(dataSnapshot.child("email").getValue().toString());
                    feedReviews.setText(dataSnapshot.child("reviews").getValue().toString());

                }
                else
                    Toast.makeText(getApplicationContext(),"No Source to Display",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

           butUpdate.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("FeedbackTable");
                   updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           if(dataSnapshot.hasChild("userfeedback1")) {

                               try {
                                   userfeedback.setFullname(feedFullaname.getText().toString().trim());
                                   userfeedback.setMobile(Integer.parseInt(feedMobile.getText().toString().trim()));
                                   userfeedback.setEmail(feedEmail.getText().toString().trim());
                                   userfeedback.setReviews(feedReviews.getText().toString().trim());

                                   reference = FirebaseDatabase.getInstance().getReference().child("FeedbackTable").child("userfeedback1");
                                   reference.setValue(userfeedback);
                                   //clearControls();

                                   Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();

                               } catch (NumberFormatException e) {
                                   Toast.makeText(getApplicationContext(),"Invalid Contact Number", Toast.LENGTH_SHORT).show();
                               }

                           }
                           else
                               Toast.makeText(getApplicationContext(),"No source to update",Toast.LENGTH_SHORT).show();

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
           });
           butDelete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   reference = FirebaseDatabase.getInstance().getReference().child("FeedbackTable").child("userfeedback1");
                   reference.removeValue();
                   clearControls();
                   Toast.makeText(getApplicationContext(),"sucessfully deleted",Toast.LENGTH_SHORT).show();
               }
               private void clearControls() {
                   feedFullaname.setText("");
                   feedMobile.setText("");
                   feedEmail.setText("");
                   feedReviews.setText("");

               }
           });
    }
}

