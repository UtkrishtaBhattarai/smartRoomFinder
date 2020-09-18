package com.example.smartroomfinder;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.smartroomfinder.api.UsersAPI;
import com.example.smartroomfinder.models.Users;
import com.example.smartroomfinder.url.URL;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity {

    public String userID = "";
    Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        loadCurrentUser();
    }

    private void loadCurrentUser() {
        UsersAPI usersAPI = URL.getInstance().create(UsersAPI.class);
        Call<Users> userCall = usersAPI.getUserDetails(URL.token);
        userCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LandingActivity.this, "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                users = response.body();
                userID = response.body().get_id();

                // set id and pass value in ProductActivity
                AddProductActivity.id = response.body().get_id();
                PropertyDetailActivity.id=response.body().get_id();
                LikedPropertyActivity.id=response.body().get_id();
                OrderActivity.id=response.body().get_id();
                Toast.makeText(LandingActivity.this, "ID IS"+response.body().get_id(), Toast.LENGTH_SHORT).show();
                // etfname.setText(response.body().getFname());
                //etlname.setText(response.body().getLname());
                //etemail.setText(response.body().getEmail());
                //etaddress.setText(response.body().getAddress());
                //etnumber.setText(response.body().getNumber());
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.d("My message", "onFaliure" + t.getLocalizedMessage());
                Toast.makeText(LandingActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}