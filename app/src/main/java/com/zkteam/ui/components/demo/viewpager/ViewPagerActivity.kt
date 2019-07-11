package com.zkteam.ui.components.demo.viewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.demo.R
import com.zkteam.ui.components.viewpager.ZKFragmentAdapter
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : ZKBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_view_pager
    }

    override fun initData(bundle: Bundle?) {
        vp_view.adapter = object : ZKFragmentAdapter(this) {
            override fun getItemCount(): Int {
                return 10
            }

            override fun createFragment(position: Int): Fragment {
                return WQFragment.create(position)
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