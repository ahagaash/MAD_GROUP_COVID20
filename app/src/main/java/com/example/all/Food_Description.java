package com.example.all;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Food_Description extends AppCompatActivity {

    TextView foodName;
    TextView foodDescription;
    ImageView foodImage;
    TextView foodPrice;
    String key = "";
    String imageUrl = "";
    ElegantNumberButton numberButton;
    String foodName1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__description);

        foodName = (TextView) findViewById(R.id.txtName);
        foodDescription = (TextView) findViewById(R.id.txtDescription);
        foodImage = (ImageView) findViewById(R.id.ivImage2);
        foodPrice = (TextView) findViewById(R.id.txtPrice);
        numberButton=(ElegantNumberButton)findViewById(R.id.number_btn) ;

          foodName1=getIntent().getStringExtra("Name");
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {

            foodName.setText(mBundle.getString("Name"));
            foodDescription.setText(mBundle.getString("Description"));
            key = mBundle.getString("keyValue");
            imageUrl = mBundle.getString("Image");
            foodPrice.setText(mBundle.getString("Price"));
            //foodImage.setImageResource(mBundle.getInt("Image"));

            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(foodImage);


        }

    }

    public void btnDeleteFood(View view) {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Recipe");
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);

        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                reference.child(key).removeValue();
                Toast.makeText(Food_Description.this, "Food is Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    public void btnUpdateFood(View view) {

        startActivity(new Intent(getApplicationContext(), UpdateFoodActivity.class)
                .putExtra("recipeName", foodName1)
                .putExtra("recipeDescription", foodDescription.getText().toString())
                .putExtra("recipePrice", foodPrice.getText().toString())
                .putExtra("oldimageurl", imageUrl)
                .putExtra("key", key)

        );
    }

    public void addtocart(View view) {

        String saveCurrentTime, saveCurrentDate;

        DatabaseReference cartListRef =FirebaseDatabase.getInstance().getReference().child("cart List");
        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("pid", key);
        cartMap.put("pdis", foodDescription.getText().toString().trim());
        cartMap.put("pprice", foodPrice.getText().toString().trim());
        cartMap.put("pname",foodName1);
        cartMap.put("piurl",imageUrl);
        cartMap.put("quantity",numberButton.getNumber());


        cartListRef.child("Recipe").child(key).setValue(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(Food_Description.this, "added to cart", Toast.LENGTH_SHORT).show();
                                        }
                    }
                });





    }
}