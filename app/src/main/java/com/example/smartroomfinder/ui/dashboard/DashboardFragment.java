package com.example.smartroomfinder.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroomfinder.R;
import com.example.smartroomfinder.adapter.ProductsAdapter;
import com.example.smartroomfinder.api.ProductsAPI;
import com.example.smartroomfinder.models.Products;
import com.example.smartroomfinder.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private RecyclerView recyclerView_, recyclerView1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView_ = root.findViewById(R.id.rvproducts);
        getProduct();
        return root;
    }
    public void getProduct() {
        ProductsAPI retrofitProductAPI = URL.getInstance().create(ProductsAPI.class);
        Call<List<Products>> ProductsCall = retrofitProductAPI.getallProduct();
        ProductsCall.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                // Toast.makeText(getActivity(), "RESPONSE" + response.body(), Toast.LENGTH_SHORT).show();
                ProductsAdapter recyclerviewAdapter = new ProductsAdapter(response.body(), getActivity());
                RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getActivity(), 3);
                recyclerView_.setLayoutManager(mlayoutManager);
                recyclerView_.setHasFixedSize(true);
                recyclerView_.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
        });

    }
}