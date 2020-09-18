package com.example.smartroomfinder;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.CursorLoader;
import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.smartroomfinder.api.ProductsAPI;
import com.example.smartroomfinder.api.UsersAPI;
import com.example.smartroomfinder.models.Products;
import com.example.smartroomfinder.serverresponse.ImageResponse;
import com.example.smartroomfinder.strictmode.StrictModeClass;
import com.example.smartroomfinder.url.URL;
import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class AddProductActivity extends AppCompatActivity {
    private ImageButton imgproperty;
    private EditText etpname, etplocation, etpprice, etpdesc, etpnumber;
    private Button btnsubmitproperty;
    public static String id = null;
    String imagePath;
    private String imageName = "";

    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(29)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (shouldAskPermissions()) {
            askPermissions();
        }
        setContentView(R.layout.activity_add_product);
        btnsubmitproperty = findViewById(R.id.btnpsubmit);
        imgproperty = findViewById(R.id.btnsubmitproperty);
        etpname = findViewById(R.id.etpname);
        etplocation = findViewById(R.id.etplocation);
        etpprice = findViewById(R.id.etpprice);
        etpdesc = findViewById(R.id.etpdesc);
        etpnumber = findViewById(R.id.etpnumber);
        imgproperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });


        btnsubmitproperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageOnly();
                signUp();

            }
        });

    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgproperty.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);
        ProductsAPI productsAPI = URL.getInstance().create(ProductsAPI.class);
        Call<ImageResponse> responseBodyCall = productsAPI.uploadImage(body);
        StrictModeClass.StrictMode();
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void signUp() {
        String userid = id;
        String name = etpname.getText().toString();
        String price = etpprice.getText().toString();
        String location = etplocation.getText().toString();
        String description = etpdesc.getText().toString();
        String number = etpnumber.getText().toString();
        Products products = new Products(name, price, imageName, number, location, description,userid );
        ProductsAPI productsAPI = URL.getInstance().create(ProductsAPI.class);
        Call<Void> signUpCall = productsAPI.products(products);

        signUpCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddProductActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AddProductActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddProductActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}