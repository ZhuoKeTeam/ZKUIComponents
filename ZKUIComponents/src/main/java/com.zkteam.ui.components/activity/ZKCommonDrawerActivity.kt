package com.zkteam.ui.components.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.R
import com.zkteam.ui.components.webview.ZKWebViewActivity
import com.zkteam.ui.components.webview.ZKWebViewActivity.Companion.FLAG_ZK_UI_WEBVIEW_URL

abstract class ZKCommonDrawerActivity : ZKBaseActivity() {

    companion object {
        const val urlGitHub = "https://github.com/ZhuoKeTeam"
        const val urlBlog = "https://www.gdky005.com"
    }

    lateinit var mBaseDrawerRootLayout: DrawerLayout
    lateinit var mBaseDrawerContainerView: FrameLayout

    private val mListener = object : NavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
            val id = item.itemId

            when(id) {
                R.id.baseDrawerActionGitHub -> {
                    startActivity(
                        Intent(mContext, ZKWebViewActivity::class.java)
                            .putExtra(FLAG_ZK_UI_WEBVIEW_URL, urlGitHub))
                }
                R.id.baseDrawerActionBlog -> {
                    startActivity(
                        Intent(mContext, ZKWebViewActivity::class.java)
                            .putExtra(FLAG_ZK_UI_WEBVIEW_URL, urlBlog))
                }
            }

            return onDrawerItemClickListener(id)
        }
    }

    /**
     *  if (id == R.id.baseDrawerActionGitHub) {
     *      ToastUtils.showShort("github")
     *      return true
     *  } else if (id == R.id.baseDrawerActionBlog) {
     *      ToastUtils.showShort("blog")
     *      return true
     *  }
     *  return false
     */
    abstract fun onDrawerItemClickListener(itemId: Int): Boolean

    /**
     * 布局请保留 R.layout.activity_zk_common_drawer 中的 id，否则可能无效，或者重新自行获取
     */
    abstract fun setDrawerLayout(): Int

    override fun isSwipeBack(): Boolean {
        return false
    }

    @SuppressLint("ResourceType")
    override fun setRootLayout(@LayoutRes layoutId: Int) {
        val drawerLayoutId = setDrawerLayout()
        if (drawerLayoutId == 0) {
            super.setRootLayout(R.layout.activity_zk_ui_common_drawer)
        } else {
            super.setRootLayout(drawerLayoutId)
        }
        mBaseDrawerRootLayout = findViewById(com.zkteam.ui.components.R.id.baseDrawerRootLayout)
        mBaseDrawerContainerView = findViewById(com.zkteam.ui.components.R.id.baseDrawerContainerView)
        if (layoutId > 0) {
            LayoutInflater.from(this).inflate(layoutId, mBaseDrawerContainerView)
        }
        val nav = findViewById<NavigationView>(com.zkteam.ui.components.R.id.baseDrawerNavView)
        nav.setNavigationItemSelectedListener(mListener)
    }



}