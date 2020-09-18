package com.example.smartroomfinder.api;

import com.example.smartroomfinder.models.Products;
import com.example.smartroomfinder.serverresponse.ImageResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ProductsAPI {
    @POST("products/addproducts")
    Call<Void> products(@Body Products products);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);
}
