package com.zkteam.ui.components.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.R
import kotlinx.android.synthetic.main.activity_welcome.*

open class ZKWelcomeActivity: ZKBaseActivity() {

    // 是否显示引导页面
    private var isShow = true
    // 默认进入的 Activity
    private var mainActivity: Class<*> = EmptyActivity::class.java
    val ids = mutableListOf(R.drawable.w1, R.drawable.w2, R.drawable.w3, R.drawable.w4)

    override fun getLayoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun initData(bundle: Bundle?) {

        if (ids.size <= 0 || !isShow) {
            startMainActivity()
            return
        }

        zkViewPager.adapter = object : PagerAdapter() {

            override fun getCount(): Int {
                return ids.size
            }

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view === `object`
            }

            override fun instantiateItem(container: ViewGroup, position: Int): ImageView {

                val imageView = ImageView(mContext)
                imageView.setImageResource(ids[position])
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                val layoutParams =
                    ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                imageView.layoutParams = layoutParams
                container.addView(imageView)
                return imageView
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                val imageView = `object` as ImageView
                container.removeView(imageView)
            }
        }

        zkViewPagerIndicator.setUpWithViewPager(zkViewPager)
        zkViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                btnStart.visibility = if (position == 3) View.VISIBLE else View.GONE
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        btnStart.setOnClickListener {
            startMainActivity()
        }
    }

    fun showGuide(show: Boolean) {
        isShow = show
    }

    /**
     * 设置默认进入的 Activity
     */
    public fun setMainActivity(cls: Class<*>) {
        mainActivity = cls
    }

    private fun startMainActivity() {
        val intent = Intent(mContext, mainActivity)
        startActivity(intent)
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        finish()
    }

    override fun initLifecycleObserve() {
        //function
    }

    override fun initListener() {
        //function
    }

    override fun initViews(contentView: View) {
        //function
    }

    override fun onDebouncingClick(view: View) {
        //function
    }
}