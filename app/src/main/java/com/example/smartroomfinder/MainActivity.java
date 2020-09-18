package com.example.smartroomfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartroomfinder.api.UsersAPI;
import com.example.smartroomfinder.models.Users;
import com.example.smartroomfinder.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class MainActivity extends AppCompatActivity {
    public String userID = "";
    Users users;
    Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //btnadd=findViewById(R.id.btnopenaddactivity);
        loadCurrentUser();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadCurrentUser() {
        UsersAPI usersAPI = URL.getInstance().create(UsersAPI.class);
        Call<Users> userCall = usersAPI.getUserDetails(URL.token);
        userCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                users = response.body();
                userID = response.body().get_id();

                // set id and pass value in ProductActivity
                AddProductActivity.id = response.body().get_id();
                //CartActivity.id = response.body().get_id();
                //OrderActivity.id=response.body().get_id();

                Toast.makeText(MainActivity.this, "ID IS"+response.body().get_id(), Toast.LENGTH_SHORT).show();
               // etfname.setText(response.body().getFname());
                //etlname.setText(response.body().getLname());
                //etemail.setText(response.body().getEmail());
                //etaddress.setText(response.body().getAddress());
                //etnumber.setText(response.body().getNumber());
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.d("My message", "onFaliure" + t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}