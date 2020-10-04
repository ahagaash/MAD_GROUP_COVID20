package com.example.all;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.UUID;

public class Upload_Food extends AppCompatActivity {

    ImageView recipeImage;
    Uri uri;
    EditText txt_name,txt_description,txt_price;
    String imageUrl;
    FirebaseStorage storage;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__food);
        recipeImage = (ImageView)findViewById(R.id.iv_foodImage);
        txt_name=(EditText)findViewById(R.id.txt_recipe_name);
        txt_description=(EditText)findViewById(R.id.txt_recipe_Description);
        txt_price=(EditText)findViewById(R.id.txt_recipe_price);
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




    public void uploadRecipe(){

         final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Food is uploading");
        progressDialog.show();

        FoodData foodData = new FoodData(
                txt_name.getText().toString(),
                txt_description.getText().toString(),
                txt_price.getText().toString(),
                imageUrl);


        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Recipe")
                .child(myCurrentDateTime).setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(Upload_Food.this, "Food uploaded", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_Food.this, "Failed"+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
           progressDialog.dismiss();
            }
        });

    }
    public void uploadImage(){

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("RecipeImage").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri>  uriTask= taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl=urlImage.toString();
                //Implement a toast

                uploadRecipe();




            }
        });
    }
    public void btnUploadfood(View view) {

   uploadImage();

    }

    public void uploadPicture(View view) {

    }


}