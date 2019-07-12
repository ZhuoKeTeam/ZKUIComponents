package com.zkteam.ui.components.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.AppUtils
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.R
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.widget_toolbar.*

class AboutActivity: ZKBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_about
    }

    override fun initData(bundle: Bundle?) {
        //function
    }

    override fun initLifecycleObserve() {
        //function
    }

    override fun initListener() {
        //function
    }

    @SuppressLint("SetTextI18n")
    override fun initViews(contentView: View) {
        tv_title.text = "关于我们 "
        tv_app_name.text = AppUtils.getAppName()
        tv_version.text = "版本号: " + AppUtils.getAppVersionName()

    }

    override fun onDebouncingClick(view: View) {
        //function
    }
}