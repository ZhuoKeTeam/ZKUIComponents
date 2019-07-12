package com.zkteam.ui.components.test

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.R
import com.zkteam.ui.components.viewpager.ZKFragmentAdapter
import kotlinx.android.synthetic.main.activity_test_view_pager.*

class TestViewPagerActivity : ZKBaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_test_view_pager
    }

    override fun initData(bundle: Bundle?) {

//        val builder = ZKViewPagerTransformerAnimation.Builder()
//        builder.setTranslation(true)
//        builder.setTranslationX(100F)
//        builder.setTranslationY(100F)
//        builder.setRotation(true)
//        builder.setRotationCircle(90F)
//        builder.setScale(true)
//        zkViewPager.viewPager.setPageTransformer(ZKViewPagerTransformerAnimation(builder, zkViewPager.viewPager)) // builder 可以为空
//        zkViewPager.setZKAnimationBuilder(builder)
//        zkViewPager.showZKAnimation(false)
        zkViewPager.viewPager.adapter = object : ZKFragmentAdapter(this) {
            override fun getItemCount(): Int {
                return 10
            }

            override fun createFragment(position: Int): Fragment {
                return TestFragment.create(position)
            }
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