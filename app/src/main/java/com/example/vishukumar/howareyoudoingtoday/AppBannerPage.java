package com.example.vishukumar.howareyoudoingtoday;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AppBannerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "onCreate");
        setContentView(R.layout.activity_app_banner_page);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(AppBannerPage.this,
                        MoodsPage.class);
                startActivity(i);
                finish();
            }
        }, 5000);
    }


}
