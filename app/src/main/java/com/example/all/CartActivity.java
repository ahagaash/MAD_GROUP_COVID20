package com.example.all;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button NextprocessBtn;

    private TextView txtTotalAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView =findViewById(R.id.cartList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        NextprocessBtn =(Button)findViewById(R.id.update);
        txtTotalAmount =(TextView)findViewById(R.id.total);

    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference cartList = FirebaseDatabase.getInstance().getReference().child("cart List");


        FirebaseRecyclerOptions<Cart> options=
                new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartList.child("Recipe"), Cart.class)
                .build();


        FirebaseRecyclerAdapter<Cart,CartViewHolder> adapter
                = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i, @NonNull final Cart cart) {

                cartViewHolder.txtProductQunatity.setText("Quantity ="+cart.getQuantity());
                cartViewHolder.txtProductName.setText(String.valueOf(cart.getProductName()));
                cartViewHolder.txtProductPrice.setText("Single price"+cart.getPrice());

                cartViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        CharSequence options[] = new CharSequence[]
                                {
                                        "Edit",
                                        "remove"
                                };
                        AlertDialog.Builder bulider = new AlertDialog.Builder(CartActivity.this);
                        bulider.setTitle("Cart Options");

                        bulider.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                 if(i==0);
                                {
                                    Intent intent = new Intent(CartActivity.this,Food_Description.class);
                                    intent.putExtra("keyValue",cart.getCartId());
                                    startActivity(intent);
                                }
                                if(i==1){

                                    cartList
                                            .child("Recipe")
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                   if(task.isSuccessful())
                                                   {
                                                       Toast.makeText(CartActivity.this,"Item removed succuessfully",Toast.LENGTH_SHORT).show();
                                                       Intent intent = new Intent(CartActivity.this,ProductPage.class);

                                                       startActivity(intent);
                                                   }
                                                }
                                            });

                                }
                            }
                        });
                    }
                });

            }


            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_single,parent,false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}