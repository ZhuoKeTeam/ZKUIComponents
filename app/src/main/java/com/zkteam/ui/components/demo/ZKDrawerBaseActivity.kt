package com.zkteam.ui.components.demo

import android.os.Bundle
import android.view.View
import com.zkteam.sdk.base.ZKBaseActivity

class ZKDrawerBaseActivity: ZKBaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.abc_action_menu_item_layout
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
        //function
    }

    override fun onDebouncingClick(view: View) {
        //function
    }
}