package com.example.all;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity {

    TextView foodName;
    TextView foodDescription;
    ImageView foodImage;
    TextView foodPrice;
    String key="";
    String imageUrl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        foodName=(TextView)findViewById(R.id.txtName);
        foodDescription =(TextView)findViewById(R.id.txtDescription);
        foodImage=(ImageView)findViewById(R.id.ivImage2);
        foodPrice=(TextView)findViewById(R.id.txtPrice);

        Bundle mBundle =getIntent().getExtras();
        if(mBundle != null){

            foodName.setText(mBundle.getString("Name"));
            foodDescription.setText(mBundle.getString("Description"));
            key=mBundle.getString("keyValue");
            imageUrl=mBundle.getString("Image");
            foodPrice.setText(mBundle.getString("Price"));
            //foodImage.setImageResource(mBundle.getInt("Image"));

            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(foodImage);


        }

    }

    public void btnDeleteFood(View view) {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Recipe");
        FirebaseStorage storage=FirebaseStorage.getInstance();

        StorageReference storageReference =storage.getReferenceFromUrl(imageUrl);

        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                reference.child(key).removeValue();
                Toast.makeText(DetailActivity.this,"Food is Deleted",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }

    public void btnUpdateFood(View view) {

        startActivity(new Intent(getApplicationContext(),UpdateFoodActivity.class)
            .putExtra("recipeName",foodName.getText().toString())
                .putExtra("recipeDescription",foodDescription.getText().toString())
                .putExtra("recipePrice",foodPrice.getText().toString())
                .putExtra("oldimageurl",imageUrl)
                .putExtra("key",key)
        );
    }
}