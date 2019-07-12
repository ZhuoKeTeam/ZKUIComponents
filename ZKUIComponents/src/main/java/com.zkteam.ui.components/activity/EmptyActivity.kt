package com.zkteam.ui.components.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.R
import kotlinx.android.synthetic.main.activity_empty.*

class EmptyActivity: ZKBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_empty
    }

    override fun initData(bundle: Bundle?) {
        //function
    }

    override fun initLifecycleObserve() {
        //function
    }

    override fun initListener() {
        btn_setting.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }
    }

    override fun initViews(contentView: View) {
        //function
    }

    override fun onDebouncingClick(view: View) {
        //function
    }
}