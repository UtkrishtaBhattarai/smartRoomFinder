package com.example.smartroomfinder.bll;

import com.example.smartroomfinder.api.UsersAPI;
import com.example.smartroomfinder.serverresponse.CartResponse;
import com.example.smartroomfinder.url.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class CartBll {

    boolean isSuccess = false;

    public boolean checkcart(String userid, String productid) {
        UsersAPI usersAPI = URL.getInstance().create(UsersAPI.class);
        Call<CartResponse> cartcall = usersAPI.checkcart(productid, userid);
        try {
            Response<CartResponse> cartresponse = cartcall.execute();
            if (cartresponse.isSuccessful() &&
                    cartresponse.body().getStatus().equals("addhere")) {
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
