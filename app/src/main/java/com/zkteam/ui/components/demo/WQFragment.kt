package com.zkteam.ui.components.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ColorUtils
import com.zkteam.sdk.base.ZKBaseFragment
import kotlinx.android.synthetic.main.fragment_wq.*

class WQFragment: ZKBaseFragment() {

    companion object {
        public const val FLAG_WQ_FRAGMENT_POSITION = "flag_wq_fragment_position"

        fun create(position: Int) :WQFragment {
            val fragment = WQFragment()
            val args = Bundle()
            args.putInt(FLAG_WQ_FRAGMENT_POSITION, position)
            fragment.arguments = args
            return fragment
        }

    }

    var position: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.fragment_wq
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
        tv.text = "内容：$position"
        rl_bg.setBackgroundColor(ColorUtils.getRandomColor())
    }

    override fun onDebouncingClick(view: View) {
        //function
    }
}