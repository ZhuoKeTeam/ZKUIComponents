package com.zkteam.ui.components.demo

import com.zkteam.sdk.base.ZKBaseApplication
import com.zkteam.ui.components.ZKUIManager

class ZKUIApplication : ZKBaseApplication() {

    override fun onCreate() {
        super.onCreate()
        val zkUiManager = ZKUIManager.instance
        zkUiManager.setShowGuide(false)
        zkUiManager.setMainActivity(MainActivity::class.java)
    }
}