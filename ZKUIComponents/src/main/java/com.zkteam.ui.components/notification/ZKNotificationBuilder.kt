package com.zkteam.ui.components.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.zkteam.ui.components.R
import java.util.*

class ZKNotificationBuilder {

    companion object {
        const val NOTIFICATION_CLICK = "NOTIFICATION_CLICK"
    }

    private lateinit var context: Context
    private lateinit var notificationManager: NotificationManagerCompat
    private var msgReceiver: NotificationMsgReceiver? = null


    private lateinit var parentGroup: RemoteViews
    private lateinit var builder: NotificationCompat.Builder
    private var childCount: Int = 0

    private var list: ArrayList<ItemDataBean>? = null
    private var isCloseed = false

    fun NotificationBuilder(context: Context) {
        this.context = context
        notificationManager = NotificationManagerCompat.from(context)
        builder = NotificationCompat.Builder(context, "11")
        builder.setContentTitle("ZhuoKeSDK-Notification Util")//标题
        builder.setContentText("")//内容
        builder.setSmallIcon(R.mipmap.ic_launcher_round)//Icon 如果不设置Icon,Notification不会跳出来
        builder.setAutoCancel(false)//点击以后是否自动清除,true-点击后自动清除,false-点击以后不会自动清除
        builder.setOngoing(true)//设置是否为正在进行的通知
        builder.setOnlyAlertOnce(false)//setOnlyAlertOnce 是否提示一次. true - 如果Notification已经存在状态栏即使在调用notify函数也不会更新.
        //最外层布局Group
        parentGroup = RemoteViews(context.packageName, R.layout.notification_util_group)
        childCount = -1
    }

    fun addItem(itemDataBean: ItemDataBean) {
        if (list == null) {
            list = ArrayList<ItemDataBean>()
        }

        if (list!!.size >= 6) {
            Toast.makeText(context, "噢~工具栏放不下了，删除些再添加吧。", Toast.LENGTH_SHORT).show()
            return
        }

        list!!.add(itemDataBean)
        update()
    }

    fun updateItem(itemDataBean: ItemDataBean, index: Int) {
        if (list == null || list!!.size <= 0) {
            return
        }

        list!![index] = itemDataBean
        update()
    }

    fun updateItem(itemDataBean: ItemDataBean) {
        if (list == null) {
            return
        }

        for (i in list!!.indices) {
            val itemBean = list!![i]
            if (itemBean.itemId.contentEquals(itemDataBean.itemId)) {
                list!![i] = itemDataBean
            }
        }

        update()
    }

    fun removeItem(index: Int) {
        if (list == null) {
            return
        }

        list!!.removeAt(index)
        update()
    }

    fun removeItem(id: String) {
        if (list == null) {
            return
        }

        for (itemBean in list!!) {
            if (itemBean.itemId.contentEquals(id)) {
                if (list!!.remove(itemBean)) {
                    update()
                }
            }
        }
    }

    fun removeAllItem() {
        if (list == null) {
            return
        }

        list = ArrayList<ItemDataBean>()
        update()
    }

    private fun update() {
        childCount = -1
        parentGroup.removeAllViews(R.id.notifiy_item_parent_group)
        for (itemBean in list!!) {
            val itemView = RemoteViews(context.packageName, R.layout.notification_item)
            itemView.setTextViewText(R.id.notifiy_item_text, itemBean.text)
            itemView.setImageViewResource(R.id.notifiy_item_img, itemBean.imgId)
            val intent = Intent(NOTIFICATION_CLICK)
            intent.putExtra("data", itemBean)
            childCount++
            val pendingIntent = PendingIntent.getBroadcast(
                context, childCount, intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            itemView.setOnClickPendingIntent(R.id.notification_item_view, pendingIntent)
            parentGroup.addView(R.id.notifiy_item_parent_group, itemView)
        }

        show()
    }

    fun setOnItemClickListener(clickListener: NotificationItemClickListener?) {
        if (clickListener != null) {
            msgReceiver = NotificationMsgReceiver(clickListener)
            context.registerReceiver(msgReceiver, IntentFilter(NOTIFICATION_CLICK))
        }
    }

    private fun show() {
        builder.setContent(parentGroup)
        notificationManager.notify(1, builder.build())
        isCloseed = false
    }

    fun close() {
        if (isCloseed) {
            return
        }

        isCloseed = true
        notificationManager.cancel(1)
        parentGroup.removeAllViews(R.id.notifiy_item_parent_group)
        list = ArrayList<ItemDataBean>()
        if (msgReceiver != null) {
            context.unregisterReceiver(msgReceiver)
            msgReceiver = null
        }
    }
}