package com.example.smartroomfinder.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartroomfinder.ProfileActivity;
import com.example.smartroomfinder.R;
import com.example.smartroomfinder.api.ProductsAPI;
import com.example.smartroomfinder.api.UsersAPI;
import com.example.smartroomfinder.models.DispatchModel;
import com.example.smartroomfinder.models.Order;
import com.example.smartroomfinder.models.Products;
import com.example.smartroomfinder.models.UserUpdateModel;
import com.example.smartroomfinder.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SaujiAdapter extends RecyclerView.Adapter<SaujiAdapter.MyHolder> {
    List<Order> products_list;
    Context contexts;
    public static String userid=null;

    public SaujiAdapter(List<Order> products_list, Context contexts) {
        this.products_list = products_list;
        this.contexts = contexts;
    }
    @NonNull
    @Override
    public SaujiAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sauji, parent, false);//yaha chai milauna parne xa
        SaujiAdapter.MyHolder myholder = new SaujiAdapter.MyHolder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(@NonNull final SaujiAdapter.MyHolder holder, int position) {
        final Order order = products_list.get(position);
        holder.orderlocation.setText(order.getOrderlocation());
        holder.ordernumber.setText(order.getOrdernumber());
        // holder.orderpeople.setText(order.getNumberofpeople());

        holder.acceptinvitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid=order.get_id();
                update();
            }

        });

        holder.rejectinvitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void update() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DispatchModel usersCUD = new DispatchModel("yes");
        ProductsAPI employeeApi = retrofit.create(ProductsAPI.class);
        Call<DispatchModel> updateModelCall = employeeApi.editconf(userid, usersCUD);

        updateModelCall.enqueue(new Callback<DispatchModel>() {
            @Override
            public void onResponse(Call<DispatchModel> call, Response<DispatchModel> response) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(contexts);
                builder1.setMessage("Updated Successfully");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

            @Override
            public void onFailure(Call<DispatchModel> call, Throwable t) {

            }
        });

    }

    public void update2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DispatchModel usersCUD = new DispatchModel("reject");
        ProductsAPI employeeApi = retrofit.create(ProductsAPI.class);
        Call<DispatchModel> updateModelCall = employeeApi.editconf(userid, usersCUD);

        updateModelCall.enqueue(new Callback<DispatchModel>() {
            @Override
            public void onResponse(Call<DispatchModel> call, Response<DispatchModel> response) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(contexts);
                builder1.setMessage("Rejected");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

            @Override
            public void onFailure(Call<DispatchModel> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return products_list.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        TextView orderlocation, ordernumber,orderpeople;
        Button rejectinvitation,acceptinvitation;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            orderlocation = itemView.findViewById(R.id.orderlocation);
            ordernumber = itemView.findViewById(R.id.ordernumber);
            orderpeople = itemView.findViewById(R.id.orderpeople1);
            rejectinvitation=itemView.findViewById(R.id.rejectinvitation);
            acceptinvitation=itemView.findViewById(R.id.acceptinvitation);
        }
    }
}
