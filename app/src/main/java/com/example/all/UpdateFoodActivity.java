package com.example.all;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class UpdateFoodActivity extends AppCompatActivity {

    ImageView recipeImage;
    Uri uri;
    EditText txt_name,txt_description,txt_price;
    String imageUrl;
    String key,oldImageUrl;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String foodName,foodDescription,foodPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food);
        recipeImage = (ImageView)findViewById(R.id.iv_foodImage);
        txt_name=(EditText)findViewById(R.id.txt_recipe_name);
        txt_description=(EditText)findViewById(R.id.txt_recipe_Description);
        txt_price=(EditText)findViewById(R.id.txt_recipe_price);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            Glide.with(UpdateFoodActivity.this)
                    .load(bundle.getString("oldimageurl"))
                    .into(recipeImage);
            txt_name.setText(bundle.getString("recipeName"));
            txt_description.setText(bundle.getString("recipeDescription"));
            txt_price.setText(bundle.getString("recipePrice"));
            key=bundle.getString("key");
            oldImageUrl=bundle.getString("oldimageurl");
        }

        databaseReference= FirebaseDatabase.getInstance().getReference("Recipe").child(key);

    }



    public void btnSelectImage(View view) {
        Intent photoPicker = new Intent(Intent.ACTION_GET_CONTENT);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== RESULT_OK){

            uri = data.getData();

            recipeImage.setImageURI(uri);

        }
        else{
            Toast.makeText(this, "You have to pick image", Toast.LENGTH_SHORT).show();
        }

    }

    public void btnUpdatefood(View view) {

         foodName = txt_name.getText().toString().trim();
         foodDescription = txt_description.getText().toString().trim();
         foodPrice = txt_price.getText().toString();
        storageReference = FirebaseStorage.getInstance().getReference().child("RecipeImage").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl=urlImage.toString();
                //Implement a toast

                uploadRecipe();




            }
        });
    }
    public void uploadRecipe(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Food is uploading");
        progressDialog.show();

        FoodData foodData = new FoodData(
                foodName,
                foodDescription,
                foodPrice,
                imageUrl
                );


        databaseReference.setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(UpdateFoodActivity.this,"FOOD UPDATED",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
               /* startActivity(new Intent(getApplicationContext(),MainActivity.class));*/

            }
        });

    }
}