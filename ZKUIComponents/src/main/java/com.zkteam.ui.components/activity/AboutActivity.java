package com.zkteam.ui.components.activity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.blankj.utilcode.util.AppUtils;
import com.zkteam.ui.components.R;

public class AboutActivity extends AppCompatActivity {

    private TextView tvAppName, tvVersion, tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initViews();
    }

    protected void initViews() {
        tvTitle = findViewById(R.id.tv_title);
        tvAppName = findViewById(R.id.tv_app_name);
        tvVersion = findViewById(R.id.tv_version);

        tvTitle.setText("关于我们 ");
        tvAppName.setText(AppUtils.getAppName());
        tvVersion.setText("版本号: " + AppUtils.getAppVersionName());

    }
}
