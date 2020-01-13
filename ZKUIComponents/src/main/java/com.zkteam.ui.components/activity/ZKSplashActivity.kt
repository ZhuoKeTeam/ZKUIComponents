package com.zkteam.ui.components.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.blankj.utilcode.util.*
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.sdk.exception.ZKSPException
import com.zkteam.sdk.sp.ZKSharedPreferences
import com.zkteam.ui.components.R
import kotlinx.android.synthetic.main.zk_activity_splash.*

open class ZKSplashActivity : ZKBaseActivity(){

    companion object {
        //启动界面延迟进入时间
        var DELAY_TIME = 3000
        var DELAY_ANIMATION_TIME = DELAY_TIME
        private const val FIRST_START = "first_start"
        private const val FLAG_ENTER_MAIN = 0
    }

    private var width: Int = 0

    private val permissions = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    // 默认进入的 Activity
    private var mainActivity: Class<*> = EmptyActivity::class.java
    private var welcomeActivity: Class<*> = ZKWelcomeActivity::class.java

    private val splashHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == FLAG_ENTER_MAIN) {
                try {
                    val fistStart = sharedPreferences[FIRST_START, true] as Boolean
                    if (fistStart) {
                        sharedPreferences.put(FIRST_START, false)
                        val intent = Intent(applicationContext, welcomeActivity)
                        startActivity(intent)
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        finish()
                    } else {
                        val intent = Intent(applicationContext, mainActivity)
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

    fun setNewActivity(mainCls: Class<*>, welcomeCls: Class<*>) {
        setMainActivity(mainCls)
        setWelcomeActivity(welcomeCls)
    }

    /**
     * 设置默认进入的 Activity
     */
    fun setMainActivity(cls: Class<*>) {
        mainActivity = cls
    }

    /**
     * 设置默认进入的 引导页面的 Activity
     */
    fun setWelcomeActivity(cls: Class<*>) {
        welcomeActivity = cls
    }

    internal var sharedPreferences: ZKSharedPreferences = object : ZKSharedPreferences() {

        override fun sharedPreferencesFileName(): String {
            return "init_setting"
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.zk_activity_splash
    }

    @SuppressLint("SetTextI18n")
    override fun initData(bundle: Bundle?) {
        tv_app_version.text = "v ${AppUtils.getAppVersionName()}"
        tv_app_name.text = AppUtils.getAppName()

        if (AppUtils.isAppDebug()) {
            tv_app_debug_state.visibility = View.VISIBLE
        } else {
            tv_app_debug_state.visibility = View.GONE
        }

        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
//        BarUtils.addMarginTopEqualStatusBarHeight(rl_bg)
    }

    override fun initLifecycleObserve() {
        //function
    }

    override fun initListener() {
        //function
    }

    override fun initViews(contentView: View) {
        rl_bg.setBackgroundColor(ColorUtils.getRandomColor())
        initAnimation()
    }

    override fun onDebouncingClick(view: View) {
        //function
    }

    private fun initAnimation() {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        width = wm.defaultDisplay.width
        btnAlpha(ll_bottom, DELAY_ANIMATION_TIME.toLong())
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

    override fun onDestroy() {
        splashHandler.removeMessages(FLAG_ENTER_MAIN)
        super.onDestroy()
    }

}