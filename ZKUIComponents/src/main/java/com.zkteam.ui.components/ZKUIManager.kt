package com.zkteam.ui.components

import com.zkteam.ui.components.activity.DefaultWelcomeActivity
import com.zkteam.ui.components.activity.EmptyActivity

class ZKUIManager {

    companion object {
        // 双重校验锁式（Double Check)
        val instance: ZKUIManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ZKUIManager()
        }
    }

    private var isShow: Boolean = false
    private var mainActivityClass: Class<*> = EmptyActivity::class.java
    private var welcomeActivityClass: Class<*> = DefaultWelcomeActivity::class.java

    fun setMainActivity(cls: Class<*>) {
        mainActivityClass = cls
    }

    fun getMainActivity(): Class<*> {
        return mainActivityClass
    }

    fun setWelcomeActivity(cls: Class<*>) {
        welcomeActivityClass = cls
    }

    fun getWelcomeActivity(): Class<*> {
        return welcomeActivityClass
    }

    fun setShowGuide(show: Boolean) {
        isShow = show
    }

    fun getShowGuide(): Boolean {
        return isShow
    }
}