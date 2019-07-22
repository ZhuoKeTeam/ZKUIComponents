package com.zkteam.ui.components.activity

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.SizeUtils
import com.zkteam.ui.components.R
import com.zkteam.ui.components.widget.ZKSwipePanel

abstract class ZKCommonBackBaseActivity : AppCompatActivity() {

    abstract fun isSwipeBack(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<FrameLayout>(android.R.id.content).setBackgroundColor(resources.getColor(R.color.white_back))
        initSwipeBack()
    }

    private fun initSwipeBack() {
        if (isSwipeBack()) {
            val swipePanel  = ZKSwipePanel(this)
            swipePanel.setLeftDrawable(R.drawable.zk_base_back)
            swipePanel.setLeftEdgeSize(SizeUtils.dp2px(100F))
            swipePanel.wrapView(findViewById(android.R.id.content))
            swipePanel.setOnFullSwipeListener {
                swipePanel.close(it)
                finish()
            }
        }
    }

}