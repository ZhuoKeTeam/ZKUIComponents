package com.zkteam.ui.components.demo

import android.os.Bundle
import com.zkteam.ui.components.activity.ZKSplashActivity

class SplashActivity : ZKSplashActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNewActivity(MainActivity::class.java, WelcomeActivity::class.java)
    }

}