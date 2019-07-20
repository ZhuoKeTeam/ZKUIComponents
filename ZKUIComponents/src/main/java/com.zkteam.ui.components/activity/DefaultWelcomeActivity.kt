package com.zkteam.ui.components.activity

import android.os.Bundle
import com.zkteam.ui.components.ZKUIManager

class DefaultWelcomeActivity: ZKWelcomeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val zkUIManager = ZKUIManager.instance
        showGuide(zkUIManager.getShowGuide())
        setMainActivity(zkUIManager.getMainActivity())
        super.onCreate(savedInstanceState)
    }
}