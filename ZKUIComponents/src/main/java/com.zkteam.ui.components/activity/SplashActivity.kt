package com.zkteam.ui.components.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.sdk.exception.ZKSPException
import com.zkteam.sdk.sp.ZKSharedPreferences
import com.zkteam.ui.components.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity: ZKBaseActivity() {

    private val permissions = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )


    private var width: Int = 0

    private val splashHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == FLAG_ENTER_MAIN) {
                try {
                    val fistStart = sharedPreferences[Companion.FIRST_START, true] as Boolean
                    if (fistStart) {
                        sharedPreferences.put(Companion.FIRST_START, false)
                        val intent = Intent(this@SplashActivity, WelcomeActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        finish()
                    } else {
                        val intent = Intent(baseContext, EmptyActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        finish()
                    }
                } catch (e: ZKSPException) {
                    e.printStackTrace()
                }

            }
        }
    }

    internal var sharedPreferences: ZKSharedPreferences = object : ZKSharedPreferences() {

        override fun sharedPreferencesFileName(): String {
            return "init_setting"
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData(bundle: Bundle?) {
        //function
    }

    override fun initLifecycleObserve() {
        //function
    }

    override fun initListener() {
        //function
    }

    override fun initViews(contentView: View) {
        initAnimation()
    }

    override fun onDebouncingClick(view: View) {
        //function
    }


    private fun initPermission() {
        PermissionUtils.permission(*permissions)
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(permissionsGranted: List<String>) {
                    splashHandler.sendEmptyMessageDelayed(FLAG_ENTER_MAIN, DELAY_TIME.toLong())
                }

                override fun onDenied(permissionsDeniedForever: List<String>, permissionsDenied: List<String>) {
                    ToastUtils.showShort(getString(R.string.question_permission_tip))
                    splashHandler.sendEmptyMessageDelayed(FLAG_ENTER_MAIN, DELAY_TIME.toLong())
                }
            }).request()
    }

    private fun initAnimation() {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        width = wm.defaultDisplay.width
        btnTranslateLeft(img1, 500)
        btnTranslateRight(img2, 1000)
        btnTranslateLeft(img3, 1500)
        btnAlpha(id_linear, 2000)
    }


    private fun btnAlpha(view: View, duration: Long) {
        //透明度动画 public AlphaAnimation(float fromAlpha, float toAlpha){}
        val aa = AlphaAnimation(0f, 1f)

        aa.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                initPermission()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })

        //持续时间
        aa.duration = duration
        view.startAnimation(aa)
    }


    fun btnTranslateLeft(view: View, duration: Long) {
        val `as` = AnimationSet(true)
        `as`.duration = duration
        val ta = TranslateAnimation(width.toFloat(), 0f, 0f, 0f)
        ta.duration = duration
        `as`.addAnimation(ta)
        val aa = AlphaAnimation(0f, 1f)
        //持续时间
        aa.duration = duration
        `as`.addAnimation(aa)
        view.startAnimation(`as`)
    }

    fun btnTranslateRight(view: View, duration: Long) {

        val `as` = AnimationSet(true)
        `as`.duration = duration
        val ta = TranslateAnimation((-width).toFloat(), 0f, 0f, 0f)
        ta.duration = duration
        `as`.addAnimation(ta)
        val aa = AlphaAnimation(0f, 1f)
        //持续时间
        aa.duration = duration
        `as`.addAnimation(aa)
        view.startAnimation(`as`)
    }

    override fun onDestroy() {
        splashHandler.removeMessages(FLAG_ENTER_MAIN)
        super.onDestroy()
    }

    companion object {
        private const val FIRST_START = "first_start"
        private const val DELAY_TIME = 3000
        private const val FLAG_ENTER_MAIN = 0
    }
}