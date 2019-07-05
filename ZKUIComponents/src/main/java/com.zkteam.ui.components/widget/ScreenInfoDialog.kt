package com.zkteam.ui.components.widget

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.util.DisplayMetrics
import android.widget.TextView
import com.blankj.utilcode.util.IntentUtils


/**
 * 需要再优化处理下，目前可以使用。
 */
class ScreenInfoDialog : DialogFragment() {

    private var dialog: AlertDialog? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return buildDialog(context!!)
    }

    override fun show(manager: FragmentManager?, tag: String?) {
        show(context!!)
        super.show(manager, tag)
    }

    fun show(context: Context) {
        if (dialog == null) {
            buildDialog(context)
        }
        dialog!!.show()
        val mMessageView = dialog!!.findViewById<TextView>(android.R.id.message)

        mMessageView!!.setTextColor(context.resources.getColor(com.zkteam.ui.components.R.color.notification_icon_bg_color))
    }

    private fun buildDialog(context: Context): AlertDialog {
        val builder = StringBuilder()
        val metrics = context.resources.displayMetrics
        val widthPx = metrics.widthPixels//宽度px
        val heightPx = metrics.heightPixels//高度px
        val dpi = metrics.densityDpi//密度dp
        val dpiText = getDpiText(dpi)
        val scaledDensity = String.format("%.2f", metrics.scaledDensity)//字体缩放因子

        //计算dp的方法
        val xdp = metrics.xdpi
        val ydp = metrics.ydpi

        //计算英寸的方法
        val inchX = widthPx / metrics.xdpi
        val inchY = heightPx / metrics.ydpi
        val inchScreen = Math.sqrt((inchX * inchX + inchY * inchY).toDouble())

        builder.append("屏幕分辨率:")
            .append(widthPx)
            .append(" × ")
            .append(heightPx)
            .append(" px\n")

        builder.append("密度:")
            .append(dpi)
            .append(" dp / ")
            .append(dpiText)
            .append("\n")

        builder.append("精确密度:")
            .append(xdp)
            .append(" × ").append(ydp)
            .append(" dp\n")

        builder.append("屏幕尺寸:")
            .append(String.format("%.1f", inchX)).append("\"")
            .append(" × ")
            .append(String.format("%.1f", inchY)).append("\"")
            .append(" / ").append(String.format("%.1f", inchScreen))
            .append("英寸")
            .append("\n")

        builder.append("字体缩放比例:")
            .append(scaledDensity)
            .append("\n")

        dialog = ZKBaseDialog(context).setTitle("屏幕").setMessage(builder).setCancelable(true).create()

        return dialog!!

    }


    private fun getDpiText(dpi: Int): String {
        return if (dpi <= DisplayMetrics.DENSITY_LOW) {
            "ldpi"
        } else if (dpi <= DisplayMetrics.DENSITY_MEDIUM) {
            "mdpi"
        } else if (dpi <= DisplayMetrics.DENSITY_HIGH) {
            "hdpi"
        } else if (dpi <= DisplayMetrics.DENSITY_XHIGH) {
            "xhdpi"
        } else if (dpi <= DisplayMetrics.DENSITY_XXHIGH) {
            "xxhdpi"
        } else {
            "xxxhdpi"
        }
    }
}

class ZKBaseDialog : AlertDialog.Builder {

    private var dialog: AlertDialog? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {}

    override fun create(): AlertDialog? {
        setPositiveButton("复制") { dialog, which -> copy() }
        setNegativeButton("分享") { dialog, which -> share() }
        dialog = super.create()
        return dialog
    }

    private fun copy() {
        val mTitleView = dialog!!.findViewById<TextView>(com.zkteam.ui.components.R.id.alertTitle)
        val mMessageView = dialog!!.findViewById<TextView>(android.R.id.message)
        copyText(mTitleView!!.text.toString(), mMessageView!!.text.toString(), context)
    }

    private fun share() {
        val mTitleView = dialog!!.findViewById<TextView>(com.zkteam.ui.components.R.id.alertTitle)
        val mMessageView = dialog!!.findViewById<TextView>(android.R.id.message)
        val title = mTitleView!!.text.toString()
        val text = mMessageView!!.text.toString()
        context.startActivity(Intent.createChooser(IntentUtils.getShareTextIntent(text, true), title))
    }


    /**
     * 复制文本
     *
     * @param title
     * @param content
     * @param context
     */
    private fun copyText(title: String, content: String, context: Context) {
        val cmb = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(title, content)
        cmb.primaryClip = clipData
    }
}