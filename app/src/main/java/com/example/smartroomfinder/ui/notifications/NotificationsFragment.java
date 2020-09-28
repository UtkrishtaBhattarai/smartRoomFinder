package com.example.smartroomfinder.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroomfinder.LandingActivity;
import com.example.smartroomfinder.R;
import com.example.smartroomfinder.adapter.ProductsAdapter;
import com.example.smartroomfinder.adapter.SaujiAdapter;
import com.example.smartroomfinder.api.ProductsAPI;
import com.example.smartroomfinder.models.Order;
import com.example.smartroomfinder.models.Products;
import com.example.smartroomfinder.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private RecyclerView recyclerViewsauji;
    public static String id = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        recyclerViewsauji=root.findViewById(R.id.rvsaujiji);
        String userid=id;
        Toast.makeText(getActivity(), "is"+userid, Toast.LENGTH_SHORT).show();
        getsauji();
        return root;
    }
    public void getsauji() {
        String userid=id;
        Toast.makeText(getActivity(), userid+"IS", Toast.LENGTH_SHORT).show();
        onPause();
        ProductsAPI retrofitProductAPI = URL.getInstance().create(ProductsAPI.class);
        Call<List<Order>> ProductsCall = retrofitProductAPI.getsauji(userid);
        ProductsCall.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                // Toast.makeText(getActivity(), "RESPONSE" + response.body(), Toast.LENGTH_SHORT).show();
                SaujiAdapter recyclerviewAdapter = new SaujiAdapter(response.body(), getActivity());
                RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.VERTICAL, false);
                recyclerViewsauji.setLayoutManager(mlayoutManager);
                recyclerViewsauji.setHasFixedSize(true);
                recyclerViewsauji.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

            }
        });


    }
}