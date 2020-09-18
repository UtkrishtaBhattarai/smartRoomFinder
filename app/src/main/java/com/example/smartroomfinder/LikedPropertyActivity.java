package com.example.smartroomfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartroomfinder.adapter.CartsAdapter;
import com.example.smartroomfinder.api.ProductsAPI;
import com.example.smartroomfinder.models.Cart;
import com.example.smartroomfinder.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LikedPropertyActivity extends AppCompatActivity {

    RecyclerView recyclerViewcart;
    public static String id = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_property);
        recyclerViewcart = findViewById(R.id.rvcart);
        getProduct();
    }
    public void getProduct() {
        String userid = id;
        ProductsAPI retrofitProductAPI = URL.getInstance().create(ProductsAPI.class);
        Call<List<Cart>> ProductsCall = retrofitProductAPI.getcart(userid);
        ProductsCall.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                // Toast.makeText(getActivity(), "RESPONSE" + response.body(), Toast.LENGTH_SHORT).show();
                CartsAdapter recyclerviewAdapter = new CartsAdapter(response.body(), getApplicationContext());
                RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                recyclerViewcart.setLayoutManager(mlayoutManager);
                recyclerViewcart.setHasFixedSize(true);
                recyclerViewcart.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
            }
        });


    }
}