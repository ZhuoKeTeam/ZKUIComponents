package com.zkteam.ui.components.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.blankj.utilcode.util.ActivityUtils;
import com.zkteam.ui.components.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewById(R.id.cons_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(AboutActivity.class);
            }
        });

        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("设置");
    }
}
