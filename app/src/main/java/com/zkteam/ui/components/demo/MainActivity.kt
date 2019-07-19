package com.zkteam.ui.components.demo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.BarUtils
import com.zkteam.image.loader.ZKImageLoader
import com.zkteam.sdk.ZKManager
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.activity.TestActivity
import com.zkteam.ui.components.demo.viewpager.ViewPagerCardActivity
import com.zkteam.ui.components.test.TestViewPagerActivity
import com.zkteam.ui.components.webview.ZKWebViewActivity
import com.zkteam.ui.components.widget.ScreenInfoDialog
import kotlinx.android.synthetic.main.activity_main1.*

class MainActivity : ZKBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main1
    }

    override fun initData(bundle: Bundle?) {
        ZKManager.instance.init(this.application)

    }

    override fun initLifecycleObserve() {
        //function
    }

    override fun initListener() {
        bt.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }

        bt1.setOnClickListener {
            ScreenInfoDialog().show(this)
        }

        bt2.setOnClickListener {
            startWebViewActivity("")

        }

        bt3.setOnClickListener {
            startWebViewActivity("https://www.baidu.com")
        }

        bt4.setOnClickListener {
            startActivity(Intent(this, TestViewPagerActivity::class.java))
        }

        bt5.setOnClickListener {
            startActivity(Intent(this, ViewPagerCardActivity::class.java))
        }
    }

    override fun initViews(contentView: View) {
        launcherMainCtl.setExpandedTitleColor(Color.TRANSPARENT)
        setSupportActionBar(launcherMainToolbar)

        val url = "http://e.hiphotos.baidu.com/image/h%3D300/sign=8ba24e57aeec08fa390015a769ef3d4d/b17eca8065380cd7ed824805af44ad34588281be.jpg"
        ZKImageLoader.instance.load(this, iv_pic, url)




        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
//        BarUtils.addMarginTopEqualStatusBarHeight(launcherMainToolbar)
    }

    override fun onDebouncingClick(view: View) {
        //function
    }

    private fun startWebViewActivity(url: String) {
        startActivity(Intent(this, ZKWebViewActivity::class.java)
            .putExtra(ZKWebViewActivity.FLAG_ZK_UI_WEBVIEW_URL, url))
    }
}
