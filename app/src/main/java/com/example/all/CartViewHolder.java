package com.example.all;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName,txtProductPrice,txtProductQunatity;
    private ItemClickListner itemClickListner;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txtProductName=itemView.findViewById(R.id.productName);
        txtProductPrice=itemView.findViewById(R.id.price);
        txtProductQunatity=itemView.findViewById(R.id.qty);

    }

    @Override
    public void onClick(View view) {
    itemClickListner.onClick(view,getAdapterPosition(),false);
    }

    public void setItemClickListner( ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
}
