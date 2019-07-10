package com.zkteam.ui.components.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zkteam.ui.components.R;
import com.zkteam.ui.components.exception.ZKSharePreferencesException;
import com.zkteam.ui.components.sharedpreferences.ZKSharedPreferences;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final String FIRST_START = "first_start";
    private static final int DELAY_TIME = 3000;
    private static final int FLAG_ENTER_MAIN = 0;

    private String[] permissions = new String[]{Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @SuppressLint("HandlerLeak")
    private Handler splashHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == FLAG_ENTER_MAIN) {
                try {
                    boolean fistStart = (boolean) sharedPreferences.get(FIRST_START, true);
                    if (fistStart) {
                        sharedPreferences.put(FIRST_START, false);
                        Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, EmptyActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        finish();
                    }
                } catch (ZKSharePreferencesException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private ImageView img1, img2, img3;
    private LinearLayout linear;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initAnimation();
    }

    private void initPermission() {
        PermissionUtils.permission(permissions)
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        splashHandler.sendEmptyMessageDelayed(FLAG_ENTER_MAIN, DELAY_TIME);
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        ToastUtils.showShort(getString(R.string.question_permission_tip));
                        splashHandler.sendEmptyMessageDelayed(FLAG_ENTER_MAIN, DELAY_TIME);
                    }
                }).request();
    }

    private void initView() {
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        linear = findViewById(R.id.id_linear);
    }

    private void initAnimation() {

        WindowManager wm = (WindowManager)
                getSystemService(this.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        btnTranslateLeft(img1, 500);
        btnTranslateRight(img2, 1000);
        btnTranslateLeft(img3, 1500);
        btnAlpha(linear, 2000);
    }

    private void btnAlpha(View view, long duration) {
        //透明度动画 public AlphaAnimation(float fromAlpha, float toAlpha){}
        AlphaAnimation aa = new AlphaAnimation(0, 1);

        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                initPermission();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //持续时间
        aa.setDuration(duration);
        view.startAnimation(aa);
    }

    public void btnTranslateLeft(View view, long duration) {
        AnimationSet as = new AnimationSet(true);
        as.setDuration(duration);
        TranslateAnimation ta = new TranslateAnimation(width, 0, 0, 0);
        ta.setDuration(duration);
        as.addAnimation(ta);
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        //持续时间
        aa.setDuration(duration);
        as.addAnimation(aa);
        view.startAnimation(as);
    }

    public void btnTranslateRight(View view, long duration) {

        AnimationSet as = new AnimationSet(true);
        as.setDuration(duration);
        TranslateAnimation ta = new TranslateAnimation(-width, 0, 0, 0);
        ta.setDuration(duration);
        as.addAnimation(ta);
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        //持续时间
        aa.setDuration(duration);
        as.addAnimation(aa);
        view.startAnimation(as);
    }

    ZKSharedPreferences sharedPreferences = new ZKSharedPreferences(this) {

        @Override
        public String sharedPreferencesFileName() {
            return "init_setting";
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashHandler.removeMessages(FLAG_ENTER_MAIN);
    }
}
