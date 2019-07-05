package com.zkteam.ui.components.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zkteam.sdk.ZKManager
import com.zkteam.ui.components.TestActivity
import com.zkteam.ui.components.webview.ZKWebViewActivity
import com.zkteam.ui.components.widget.ScreenInfoDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ZKManager.instance.init(this.application)

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

    }

    private fun startWebViewActivity(url: String) {
        startActivity(Intent(this, ZKWebViewActivity::class.java)
            .putExtra(ZKWebViewActivity.FLAG_ZK_UI_WEBVIEW_URL, url))
    }
}
