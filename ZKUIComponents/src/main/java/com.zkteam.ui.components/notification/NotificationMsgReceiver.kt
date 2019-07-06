package com.zkteam.ui.components.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.zkteam.ui.components.notification.ZKNotificationBuilder.Companion.NOTIFICATION_CLICK
import java.lang.reflect.Method

class NotificationMsgReceiver(clickListener: NotificationItemClickListener) : BroadcastReceiver() {

    private var clickListener: NotificationItemClickListener? = null

    init {
        this.clickListener = clickListener
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (!intent.action!!.contentEquals(NOTIFICATION_CLICK)) {
            return
        }

        if (clickListener == null) return

        collapseStatusBar(context)
        clickListener!!.onItemClicked(intent.getSerializableExtra("data") as ItemDataBean)
    }

    /**
     * 收起通知栏
     *
     * @param context
     */
    fun collapseStatusBar(context: Context) {
        try {
            val statusBarManager = context.getSystemService("statusbar")
            val collapse: Method
            if (Build.VERSION.SDK_INT <= 16) {
                collapse = statusBarManager.javaClass.getMethod("collapse")
            } else {
                collapse = statusBarManager.javaClass.getMethod("collapsePanels")
            }
            collapse.invoke(statusBarManager)
        } catch (localException: Exception) {
            localException.printStackTrace()
        }

    }
}