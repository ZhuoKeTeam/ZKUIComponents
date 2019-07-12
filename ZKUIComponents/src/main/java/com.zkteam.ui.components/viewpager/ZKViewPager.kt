package com.zkteam.ui.components.viewpager

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.viewpager2.widget.ViewPager2
import com.zkteam.ui.components.viewpager.animation.ZKViewPagerTransformerAnimation

class ZKViewPager: RelativeLayout {

    companion object {
        const val HORIZONTAL = ViewPager2.ORIENTATION_HORIZONTAL
        const val VERTICAL = ViewPager2.ORIENTATION_VERTICAL
    }

    lateinit var viewPager: ViewPager2
    private var builder: ZKViewPagerTransformerAnimation.Builder? = null
    private var isShow: Boolean = true

    constructor(context: Context?) : super(context) {init()}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {init()}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {init()}

    private fun init() {
        val viewPager = ViewPager2(context)

        initViewPager(viewPager)
        addView(viewPager, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }

    private fun initViewPager(viewPager: ViewPager2) {
        if (isShow) {
            if (builder == null) {
                builder = ZKViewPagerTransformerAnimation.Builder()
            }

            viewPager.setPageTransformer(ZKViewPagerTransformerAnimation(builder, viewPager))
        } else {
            viewPager.setPageTransformer(null)
        }

        this.viewPager = viewPager
    }

    fun setZKAnimationBuilder(builder: ZKViewPagerTransformerAnimation.Builder?) {
        this.builder = builder
        initViewPager(viewPager)
    }

    fun showZKAnimation(isShow: Boolean) {
        this.isShow = isShow
        initViewPager(viewPager)
    }

    fun setOrientation(orientation: Int) {
        viewPager.orientation = orientation
    }

}