package com.zkteam.ui.components.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.R
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : ZKBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun initData(bundle: Bundle?) {
        //function
    }

    override fun initListener() {
        btn_splash.setOnClickListener {
            startActivity(Intent(this, SplashActivity::class.java))
        }
    }

    override fun initViews(contentView: View) {
        //function
    }
}