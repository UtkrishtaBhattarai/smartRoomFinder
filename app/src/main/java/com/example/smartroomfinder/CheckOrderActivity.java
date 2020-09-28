package com.example.smartroomfinder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartroomfinder.api.ProductsAPI;
import com.example.smartroomfinder.serverresponse.OrderResponse;
import com.example.smartroomfinder.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckOrderActivity extends AppCompatActivity {

    Button btncheckorder;
    EditText etcheckproductid;
    String stat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_order);
        btncheckorder = findViewById(R.id.btncheckorder);
        etcheckproductid = findViewById(R.id.etcheckproductid);
        btncheckorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkorder();
                Toast.makeText(CheckOrderActivity.this, ""+checkorder(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public String checkorder() {
        String orderid = etcheckproductid.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductsAPI employeeApi = retrofit.create(ProductsAPI.class);
        Call<OrderResponse> voidCall = employeeApi.getorderstatus(orderid);

        voidCall.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                stat = response.body().getStatus();
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {

            }
        });
        return stat;

    }
}