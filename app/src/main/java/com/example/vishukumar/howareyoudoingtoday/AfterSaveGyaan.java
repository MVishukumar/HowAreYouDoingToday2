package com.example.vishukumar.howareyoudoingtoday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AfterSaveGyaan extends AppCompatActivity {

    String moodConsolingMessage;
    TextView consoleMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_save_gyaan);

        Bundle bundle = getIntent().getExtras();
        moodConsolingMessage = bundle.getString("MOOD_CONSOLING_MESSAGE");
        consoleMessage = (TextView) findViewById(R.id.consoleMessageId);
        consoleMessage.setText(moodConsolingMessage);

        Log.d("tag", "After save gyaan page");
    }

    public void quitOrResultsButtonClicked(View v) {

        switch (v.getId()) {
            case R.id.quitAppButtonId:
                Log.d("tag", "Quit App Button Clicked");
                quitApp();
                break;
            case R.id.showResultButtonId:
                Log.d("tag", "Show Result Button Clicked");

                Intent i = new Intent(AfterSaveGyaan.this, BarCharResultPage.class);
                startActivity(i);

                break;
        }

        Log.d("tag", "Ok or Cancel Button Clicked");
    }

    public void quitApp() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);

    }
}
