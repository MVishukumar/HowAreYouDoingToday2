package com.example.vishukumar.howareyoudoingtoday;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AppBannerPage extends AppCompatActivity {

    TextView appName, appSubtitle;
    Typeface groBold;
    Typeface helvatica;
    Animation subtitleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag", "onCreate");
        setContentView(R.layout.activity_app_banner_page);

        appName = (TextView) findViewById(R.id.appNameTextViewId);
        appSubtitle = (TextView) findViewById(R.id.appSubtitleTextViewId);
        groBold = Typeface.createFromAsset(getAssets(), "GROBOLD.ttf");
        helvatica = Typeface.createFromAsset(getAssets(), "HELR45W.ttf");
        appName.setTypeface(groBold);
        appSubtitle.setTypeface(helvatica);

        subtitleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.subtitle);
        appSubtitle.setAnimation(subtitleAnimation);

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
