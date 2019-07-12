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

class WelcomeActivity: ZKBaseActivity() {

    private val ids = IntArray(4)

    override fun getLayoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun initData(bundle: Bundle?) {
        ids[0] = R.drawable.w1
        ids[1] = R.drawable.w2
        ids[2] = R.drawable.w3
        ids[3] = R.drawable.w4

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
            val intent = Intent(mContext, EmptyActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            finish()
        }
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