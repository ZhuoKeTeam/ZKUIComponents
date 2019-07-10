package com.zkteam.ui.components.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.zkteam.ui.components.R;

/**
 * Created by renxuelong on 2019-07-10
 */
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.btn_splash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this, SplashActivity.class));
            }
        });
    }
}
