package com.zkteam.ui.components.demo

import android.os.Bundle
import com.zkteam.ui.components.activity.ZKWelcomeActivity

class WelcomeActivity: ZKWelcomeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showGuide(false)
        setMainActivity(MainActivity::class.java)
    }
}