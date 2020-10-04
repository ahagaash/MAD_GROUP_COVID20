package com.example.all;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<FoodData> myfoodList;
    FoodData mFoodData;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    EditText txt_Search;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        txt_Search=(EditText)findViewById(R.id.txt_searchtext) ;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("LOADING ITEMS ....");


         myfoodList =new ArrayList<>();



          myAdapter = new MyAdapter(MainActivity.this,myfoodList);
        mRecyclerView.setAdapter(myAdapter);

        databaseReference =FirebaseDatabase.getInstance().getReference("Recipe");
            progressDialog.show();
        eventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myfoodList.clear();
                for(DataSnapshot itemSnapshot:dataSnapshot.getChildren()){

                    FoodData foodData = itemSnapshot.getValue(FoodData.class);
                    foodData.setKey(itemSnapshot.getKey());
                    myfoodList.add(foodData);
                }
                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        txt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void filter(String text) {

        ArrayList<FoodData> filterList = new ArrayList<>();
        for(FoodData item:myfoodList){
            if(item.getItemName().toLowerCase().contains(text.toLowerCase())){

                filterList.add(item);
            }
        }
        myAdapter.filterdList(filterList);
    }

    public void btn_uploadActivity(View view) {

        startActivity(new Intent(this,Upload_Food.class));
    }


}