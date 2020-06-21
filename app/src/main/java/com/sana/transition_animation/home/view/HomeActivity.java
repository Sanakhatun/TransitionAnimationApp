package com.sana.transition_animation.home.view;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.sana.transition_animation.R;


public class HomeActivity extends AppCompatActivity {

    private TextView tv_title;

    private String username = "";

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init() {

        tv_title = findViewById(R.id.tv_title);

        try {

            /**
             * Getting data from Intent
             */

            Intent intent = getIntent();

            if (intent != null) {

                if (intent.hasExtra("username")) {

                    username = intent.getStringExtra("username");

                }

            }

            tv_title.setText("Welcome ".concat(username));

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }
}
