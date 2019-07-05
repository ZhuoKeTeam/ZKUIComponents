package com.zkteam.ui.components.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zkteam.ui.components.TestActivity
import com.zkteam.ui.components.wedgit.ScreenInfoDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }

        bt1.setOnClickListener {
            ScreenInfoDialog().show(this)
        }

    }
}
