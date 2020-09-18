package com.example.smartroomfinder.api;

import com.example.smartroomfinder.models.Cart;
import com.example.smartroomfinder.models.Order;
import com.example.smartroomfinder.models.Products;
import com.example.smartroomfinder.serverresponse.ImageResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ProductsAPI {
    @POST("products/addproducts")
    Call<Void> products(@Body Products products);

    @POST("order/addorder")
    Call<Void> orderitem(@Body Order order);

    @GET("products/getproduct")
    Call<List<Products>> getallProduct();

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    //get all carts item
    @GET("cart/check/{userid}")
    Call<List<Cart>> getcart(@Path("userid") String userid);
}
