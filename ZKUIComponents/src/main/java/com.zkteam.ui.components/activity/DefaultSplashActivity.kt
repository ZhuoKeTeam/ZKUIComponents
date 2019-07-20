package com.zkteam.ui.components.activity

import android.os.Bundle
import com.zkteam.ui.components.ZKUIManager

class DefaultSplashActivity : ZKSplashActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val zkUiManager = ZKUIManager.instance
        setNewActivity(zkUiManager.getMainActivity(), zkUiManager.getWelcomeActivity())
    }

}