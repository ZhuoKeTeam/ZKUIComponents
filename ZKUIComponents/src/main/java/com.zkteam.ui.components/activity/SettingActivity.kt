package com.zkteam.ui.components.activity

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ActivityUtils
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.R
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.widget_toolbar.*

class SettingActivity: ZKBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData(bundle: Bundle?) {
        //function
    }

    override fun initLifecycleObserve() {
        //function
    }

    override fun initListener() {
        cons_about.setOnClickListener {
            ActivityUtils.startActivity(AboutActivity::class.java)
        }
    }

    override fun initViews(contentView: View) {
        tv_title.text = "设置"
    }

    override fun onDebouncingClick(view: View) {
        //function
    }
}