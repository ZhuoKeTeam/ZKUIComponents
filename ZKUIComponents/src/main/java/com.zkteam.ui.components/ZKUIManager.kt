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

    private var mainActivityClass: Class<*>? = null
    private var welcomActivityClass: Class<*>? = null
    private var isShow: Boolean = false

    fun setMainActivity(cls: Class<*>) {
        mainActivityClass = cls
    }

    fun getMainActivity(): Class<*> {
        if (mainActivityClass == null) {
            mainActivityClass = EmptyActivity::class.java
        }

        return mainActivityClass!!
    }

    fun setWelcomeActivity(cls: Class<*>) {
        welcomActivityClass = cls
    }

    fun getWelcomeActivity(): Class<*> {
        if (welcomActivityClass == null) {
            welcomActivityClass = DefaultWelcomeActivity::class.java
        }

        return welcomActivityClass!!
    }

    fun setShowGuide(show: Boolean) {
        isShow = show
    }

    fun getShowGuide(): Boolean {
        return isShow
    }
}