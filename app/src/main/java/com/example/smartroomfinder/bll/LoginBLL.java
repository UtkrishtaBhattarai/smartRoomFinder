package com.example.smartroomfinder.bll;

import com.example.smartroomfinder.api.UsersAPI;
import com.example.smartroomfinder.serverresponse.SignUpResponse;
import com.example.smartroomfinder.url.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Url;

public class LoginBLL {
    boolean isSuccess = false;

    public boolean checkuser(String email, String password) {

        UsersAPI usersAPI = URL.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> usersCall = usersAPI.checkUser(email, password);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {
                URL.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
