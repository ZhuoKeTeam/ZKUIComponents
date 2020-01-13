package com.zkteam.ui.components.demo

import com.zkteam.sdk.base.ZKBaseApplication
import com.zkteam.ui.components.ZKUIManager
import com.zkteam.ui.components.activity.ZKSplashActivity

class ZKUIApplication : ZKBaseApplication() {

    override fun onCreate() {
        super.onCreate()
        ZKSplashActivity.DELAY_TIME = 0
        ZKSplashActivity.DELAY_ANIMATION_TIME = 0
        val zkUiManager = ZKUIManager.instance
        zkUiManager.setShowGuide(false)
        zkUiManager.setMainActivity(MainActivity::class.java)
    }
}