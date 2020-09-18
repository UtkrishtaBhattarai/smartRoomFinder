package com.example.smartroomfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartroomfinder.api.UsersAPI;
import com.example.smartroomfinder.bll.CartBll;
import com.example.smartroomfinder.models.Cart;
import com.example.smartroomfinder.url.URL;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PropertyDetailActivity extends AppCompatActivity {
    ImageView dimgview;
    TextView tvdname, tvdprice, tvddesc, tvlelocation, tvnumber,tvprprice;
    Button buyyy;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    Context mcontext;
    public static String id = null;
    String product_id = "";
    private FloatingActionButton fab;
    Button buttonCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);
        dimgview = findViewById(R.id.imgdis);
        tvddesc = findViewById(R.id.tvddesc);
        tvdprice=findViewById(R.id.tvdprice);
        tvnumber = findViewById(R.id.tvprprice);
        tvdname = findViewById(R.id.tvdname);
        tvlelocation = findViewById(R.id.tvlelocation);
        tvprprice=findViewById(R.id.tvprprice);
        buyyy = findViewById(R.id.buyyy);
        buttonCart = findViewById(R.id.cartnow);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String imgPath = URL.imagePath + (bundle.getString("image"));
            Picasso.get()
                    .load(imgPath)
                    .placeholder(R.drawable.smarttoom)
                    .resize(220, 220)
                    .centerCrop()
                    .into(dimgview);
            product_id = bundle.getString("_id");
            tvdname.setText(bundle.getString("name"));
            tvdprice.setText("Rs :"+bundle.getString("price"));
            tvprprice.setText(bundle.getString("number"));
            tvddesc.setText(bundle.getString("description"));
            tvlelocation.setText(bundle.getString("location"));
            Toast.makeText(PropertyDetailActivity.this, "k ho aayo ki kyaa hoo"+bundle.getString("userid"), Toast.LENGTH_SHORT).show();
            buyyy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mcontext = getApplicationContext();
                    Bundle bundle = getIntent().getExtras();
                    Toast.makeText(PropertyDetailActivity.this, "" +id, Toast.LENGTH_SHORT).show();
                    Toast.makeText(PropertyDetailActivity.this, "" +id, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PropertyDetailActivity.this, OrderActivity.class);
                    intent.putExtra("_id", bundle.getString("_id"));
                    intent.putExtra("number", bundle.getString("number"));
                    intent.putExtra("userid", bundle.getString("userid"));
                    intent.putExtra("name", bundle.getString("name"));
                    PropertyDetailActivity.this.startActivity(intent);
                }
            });
        }
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _userid = id;
                String _productid = product_id;
                CartBll cartBll = new CartBll();
                if (cartBll.checkcart(_userid, _productid)) {
                    Register();
                    Snackbar.make(view, "Added to Cart", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    scheduleNotification(getNotification("Buy now the product added in the liked items box here ..."), 5000);
                } else {
                    fab.setEnabled(false);
                    Snackbar.make(view, "Already Added! Check Liked items", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    scheduleNotification(getNotification("You have already added  ..."), 5000);
                }
            }
        });
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _userid = id;
                String _productid = product_id;
                CartBll cartBll = new CartBll();
                if (cartBll.checkcart(_userid, _productid)) {
                    Register();
                    Snackbar.make(view, "Added to Liked Property", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    scheduleNotification(getNotification("Buy now the liked items ..."), 5000);
                } else {
                    fab.setEnabled(false);
                    Snackbar.make(view, "Already Added! Check Liked Items", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    scheduleNotification(getNotification("You have already added  ..."), 5000);
                }
            }
        });
        }

    private void scheduleNotification(Notification notification, int delay) {
        Intent notificationIntent = new Intent(this, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
    private Notification getNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, default_notification_channel_id);
        builder.setContentTitle("Check out Cart");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.smarttoom);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }
    private void Register() {

        String _userid = id;
        String _productid = product_id;
        String name = tvdname.getText().toString();
        String price = tvlelocation.getText().toString();
        String location = tvlelocation.getText().toString();
        String number=tvnumber.getText().toString();
        String description = tvddesc.getText().toString();
        Cart cart = new Cart(_userid, _productid, name, price, description, location,number);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersAPI employeeApi = retrofit.create(UsersAPI.class);
        Call<Void> voidCall = employeeApi.addcart(cart);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "You have added to cart registered", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}