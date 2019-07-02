package com.zkteam.ui.components

import android.app.Activity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils

class TestActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rl = RelativeLayout(this)
        val tv = TextView(this)
        tv.text = "TestActivity"
        tv.textSize = 20F
        tv.setBackgroundColor(ColorUtils.getRandomColor())

        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)


        rl.setBackgroundColor(ColorUtils.getRandomColor())
        rl.addView(tv, params)

        setContentView(rl)
    }
}