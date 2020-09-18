package com.example.smartroomfinder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroomfinder.R;
import com.example.smartroomfinder.models.Cart;

import java.util.List;

public class CartsAdapter extends RecyclerView.Adapter<CartsAdapter.MyHolder> {

    public CartsAdapter(List<Cart> carts, Context contexts) {
        this.carts = carts;
        this.contexts = contexts;
    }
    List<Cart> carts;
    Context contexts;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_liked_property, parent, false);//yaha chai milauna parne xa
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Cart cart = carts.get(position);
        holder.cartprice.setText(cart.getPrice());
        holder.cartlocation.setText(cart.getLocation());
        holder.cartnumber.setText(cart.getNumber());
        holder.cartname.setText(cart.getName());
        holder.cartdescription.setText(cart.getDescription());
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView cartprice,cartdescription,cartlocation,cartname,cartnumber;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cartdescription=itemView.findViewById(R.id.cartdescription);
            cartname=itemView.findViewById(R.id.cartname);
            cartprice=itemView.findViewById(R.id.cartprice);
            cartlocation=itemView.findViewById(R.id.cartlocation);
            cartnumber=itemView.findViewById(R.id.cartnumber);

        }
    }

}