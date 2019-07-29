package com.zkteam.ui.components.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.blankj.utilcode.util.AppUtils
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.R
import kotlinx.android.synthetic.main.activity_about.*
import java.util.*

class AboutActivity: ZKBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_about
    }

    override fun setToolbarContent(toolbar: Toolbar) {
        toolbar.title = resources.getString(R.string.zk_ui_about_us)
    }

    override fun initData(bundle: Bundle?) {
        //function
    }

    override fun initViews(contentView: View) {
        tv_app_name.text = AppUtils.getAppName()
        tv_version.text = String.format(Locale.CHINA,
            resources.getString(R.string.zk_ui_local_version), AppUtils.getAppVersionName())
    }
}