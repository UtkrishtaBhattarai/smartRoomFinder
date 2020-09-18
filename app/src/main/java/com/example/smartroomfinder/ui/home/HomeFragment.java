package com.example.smartroomfinder.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroomfinder.AddProductActivity;
import com.example.smartroomfinder.LikedPropertyActivity;
import com.example.smartroomfinder.ProfileActivity;
import com.example.smartroomfinder.R;
import com.example.smartroomfinder.adapter.ProductsAdapter;
import com.example.smartroomfinder.api.ProductsAPI;
import com.example.smartroomfinder.models.Products;
import com.example.smartroomfinder.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ImageView btncart, btnprofileview, btnaboutus,webview,btnpropadd;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btncart = root.findViewById(R.id.btncartview);
        btnaboutus = root.findViewById(R.id.btnaboutus);
        btnprofileview = root.findViewById(R.id.btnprofileview);
        btnpropadd=root.findViewById(R.id.addprop);
        webview=root.findViewById(R.id.webview);
        btnpropadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AddProductActivity.class);
                startActivity(intent);
            }
        });
        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LikedPropertyActivity.class);
                startActivity(intent);
            }
        });
        btnprofileview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }



}