package com.zkteam.ui.components.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ColorUtils
import com.zkteam.sdk.base.ZKBaseFragment
import com.zkteam.ui.components.R
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment: ZKBaseFragment() {

    companion object {
        const val FLAG_WQ_FRAGMENT_POSITION = "flag_wq_fragment_position"

        fun create(position: Int) : TestFragment {
            val fragment = TestFragment()
            val args = Bundle()
            args.putInt(FLAG_WQ_FRAGMENT_POSITION, position)
            fragment.arguments = args
            return fragment
        }

    }

    private var position: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.fragment_test
    }

    override fun initData(bundle: Bundle?) {
        if (bundle != null) {
            position = bundle.getInt(FLAG_WQ_FRAGMENT_POSITION)
        }
    }

    override fun initLifecycleObserve() {
        //function
    }

    override fun initListener() {
        //function
    }

    @SuppressLint("SetTextI18n")
    override fun initViews(contentView: View) {
        tv.text = "当前页面：$position"
        rl_bg.setBackgroundColor(ColorUtils.getRandomColor())
    }

    override fun onDebouncingClick(view: View) {
        //function
    }
}