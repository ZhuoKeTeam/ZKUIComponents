package com.zkteam.ui.components.activity

import android.os.Bundle
import com.zkteam.ui.components.ZKUIManager

class DefaultWelcomeActivity: ZKWelcomeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val zkUIManager = ZKUIManager.instance
        zkUIManager.setShowGuide(zkUIManager.getShowGuide())
        zkUIManager.setMainActivity(zkUIManager.getMainActivity())
        super.onCreate(savedInstanceState)
    }
}