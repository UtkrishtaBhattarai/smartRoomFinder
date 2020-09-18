package com.example.smartroomfinder.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroomfinder.R;
import com.example.smartroomfinder.models.Products;
import com.example.smartroomfinder.url.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyHolder> {

    List<Products> products_list;
    Context contexts;

    public ProductsAdapter(List<Products> products_list, Context contexts) {
        this.products_list = products_list;
        this.contexts = contexts;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product1, parent, false);//yaha chai milauna parne xa
        MyHolder myholder = new MyHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final Products products = products_list.get(position);
        holder.txt_item_product_name.setText(products.getName());
        holder.txt_item_product_price.setText(products.getPrice());
        String imgPath = URL.imagePath + products.getImage();

//        Glide.with(context).load("url").into(imageView)


        Picasso.get()
                .load(imgPath)
                .placeholder(R.drawable.card_layout)
                .resize(220, 220)
                .centerCrop()
                .into(holder.item_product_image);
        holder.item_product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(contexts, ProductdetailActivity.class);
//                intent.putExtra("_id", products.get_id());
//                intent.putExtra("image", products.getImage());
//                intent.putExtra("name", products.getName());
//                intent.putExtra("description", products.getDescription());
//                intent.putExtra("price", products.getPrice());
//                intent.putExtra("specification", products.getSpecification());
//                contexts.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products_list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView txt_item_product_name, txt_item_product_price;
        ImageView item_product_image;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_product_name = itemView.findViewById(R.id.txtProductName);
            txt_item_product_price = itemView.findViewById(R.id.txtPrice);
            item_product_image = itemView.findViewById(R.id.imgProduct);
        }
    }
}
