package com.example.smartroomfinder.api;
import com.example.smartroomfinder.models.UserUpdateModel;
import com.example.smartroomfinder.models.Users;
import com.example.smartroomfinder.models.UsersCUD;
import com.example.smartroomfinder.serverresponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsersAPI {

    @POST("users/register")
    Call<Void> registerEmployee(@Body UsersCUD usersCUD);

    //for logging into the system
    @FormUrlEncoded
    @POST("users/login_user")
    Call<SignUpResponse> checkUser(@Field("email") String email, @Field("password") String password);

    //me
    @GET("users/me")
    Call<Users> getUserDetails(@Header("Authorization")String token);

    //forupdatinguser
    @PUT("users/me")
    Call<UserUpdateModel> edituser(@Header("Authorization")String token, @Body UserUpdateModel userUpdateModel);

    //
//    @GET("order/orderget/{id}")
//    Call<OrderResponse> getorderstatus(@Path("id") String id);
}
