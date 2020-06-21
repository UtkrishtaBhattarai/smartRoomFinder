package com.example.smartroomfinder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartroomfinder.api.UsersAPI;
import com.example.smartroomfinder.models.UsersCUD;
import com.example.smartroomfinder.url.URL;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class RegistrationActivity extends AppCompatActivity {

    Button btnsubbmit;
    TextView txtLogin;
    EditText etfname, etlname, etemail, etpassword, etaddress, etnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        etemail = findViewById(R.id.etremail);
        etfname = findViewById(R.id.etrfname);
        etlname = findViewById(R.id.etrlname);
        etpassword = findViewById(R.id.etrpwd);
        etaddress = findViewById(R.id.etaddress);
        etnumber = findViewById(R.id.etrnumber);
        btnsubbmit = findViewById(R.id.btnlogins);
        txtLogin = findViewById(R.id.txtLogin);
        btnsubbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etfname.getText())) {
                    etfname.setError("Enter First Name ");
                    ;
                    return;
                }
                if (TextUtils.isEmpty(etlname.getText())) {
                    etlname.setError("Enter Last Name ");
                    ;
                    return;
                }
                if (TextUtils.isEmpty(etemail.getText())) {
                    etemail.setError("Enter Email ");
                    ;
                    return;
                }

                if (TextUtils.isEmpty(etaddress.getText())) {
                    etaddress.setError("Enter Valid Address ");
                    return;
                } else {
                    String email = etemail.getText().toString();
                }

                if (isValidEmailId(etemail.getText().toString().trim())) {

                } else {
                    etemail.setError("Enter Valid Address ");
                    return;
                }

                if (TextUtils.isEmpty(etpassword.getText())) {
                    etpassword.setError("Enter Password ");
                    ;
                    return;
                }

                if (TextUtils.isEmpty(etnumber.getText())) {
                    etnumber.setError("Enter Number ");
                    ;
                    return;
                }
                Register();
                etemail.setText("");
                etfname.setText("");
                etlname.setText("");
                etpassword.setText("");
                etaddress.setText("");
                etnumber.setText("");
            }
        });


        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Register() {
        String fname = etfname.getText().toString();
        String lname = etlname.getText().toString();
        String email = etemail.getText().toString();
        String password = etpassword.getText().toString();
        String number = etnumber.getText().toString();
        String address = etaddress.getText().toString();

        UsersCUD employee = new UsersCUD(fname, lname, email, password, address, number);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersAPI employeeApi = retrofit.create(UsersAPI.class);
        Call<Void> voidCall = employeeApi.registerEmployee(employee);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RegistrationActivity.this, "You have been registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidEmailId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

}
