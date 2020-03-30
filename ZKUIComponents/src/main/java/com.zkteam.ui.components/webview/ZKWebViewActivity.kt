package com.zkteam.ui.components.webview

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.blankj.utilcode.util.ToastUtils
import com.zkteam.sdk.ZKBase
import com.zkteam.sdk.base.ZKBaseActivity
import com.zkteam.ui.components.R
import kotlinx.android.synthetic.main.zk_common_toolbar_layout.*
import kotlinx.android.synthetic.main.zk_ui_webview.*

open class ZKWebViewActivity : ZKBaseActivity() {
    var webView: WebView? = null
    lateinit var mWebSettings: WebSettings

    var url: String? = "http://www.gdky005.com"

    companion object {
        const val FLAG_ZK_UI_WEBVIEW_URL = "flag_zk_ui_webview_url"
    }

    override fun getLayoutId(): Int {
        return R.layout.zk_ui_webview
    }

    override fun getToolbar(): Toolbar? {
        return toolbar
    }

    override fun initData(bundle: Bundle?) {
        var newUrl = intent.getStringExtra(FLAG_ZK_UI_WEBVIEW_URL)
        initWebSettings(webView!!)

        if (newUrl.isNotEmpty()) {
            url = newUrl
        } else {
            ToastUtils.showShort("传入的 url 为空，默认展示 gdky005 网站")
        }

        webView!!.loadUrl(url)
    }

    private fun initWebSettings(webView: WebView) {
        mWebSettings = webView.settings

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        webView.webViewClient = object : ZKWebViewClient() {}
        webView.webChromeClient = object : ZKWebChromeClient() {}

        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            WebView.setWebContentsDebuggingEnabled(true)
        }

        //设置自适应屏幕，两者合用
        mWebSettings.useWideViewPort = true //将图片调整到适合webview的大小
        mWebSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

        //缩放操作
        mWebSettings.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。
        mWebSettings.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
        mWebSettings.displayZoomControls = false //隐藏原生的缩放控件
        //设置字体大小
        val res = resources
        val fontSize = res.getDimension(R.dimen.sp_14).toInt()
        mWebSettings.defaultFontSize = fontSize
        //其他细节操作
        mWebSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //关闭webview中缓存
        mWebSettings.allowFileAccess = true //设置可以访问文件
        mWebSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        mWebSettings.loadsImagesAutomatically = true //支持自动加载图片
        mWebSettings.defaultTextEncodingName = "utf-8"//设置编码格式

        mWebSettings.javaScriptEnabled = true
        mWebSettings.domStorageEnabled = true
        mWebSettings.setAppCacheMaxSize((1024 * 1024 * 8).toLong())
        val appCachePath = webView.context.cacheDir.absolutePath
        mWebSettings.setAppCachePath(appCachePath)
        mWebSettings.allowFileAccess = true
        mWebSettings.setAppCacheEnabled(true)
    }

    override fun initLifecycleObserve() {
        //function
    }

    override fun initListener() {
        //function
    }

    override fun initViews(contentView: View) {
        webView = zk_ui_webview
    }

    override fun onDebouncingClick(view: View) {
        //function
    }


    private open inner class ZKWebViewClient : WebViewClient() {

        private var mIsError: Boolean = false

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return filterUri(ZKBase.context(), request.url)
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            val uri = Uri.parse(url)
            return filterUri(ZKBase.context(), uri)
        }

        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler?, error: SslError) {
//            handler.cancel() //super中默认的处理方式，WebView变成空白页
            handler?.proceed()
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            mIsError = true
        }

        override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
            mIsError = true
        }
    }

    fun filterUri(context: Context, uri: Uri?): Boolean {
        if (uri == null) return false
        val url = uri.toString()
        this.url = url
        // 打电话
        if (url.contains("tel:")) {
            checkPermission()
            return true
        }
        return false
    }


    private fun checkPermission() {
        val permissions = arrayOf(Manifest.permission.CALL_PHONE)
//        todo 添加相关权限库
//        if (AndPermission.hasPermissions(this, permissions)) {
//            callPhone()
//        } else {
//            AndPermission.with(this)
//                .runtime()
//                .permission(permissions)
//                .onGranted {
//                    // Storage permission are allowed.
//                    callPhone()
//                }
//                .onDenied {
//                    ToastUtils.showShort("获取权限失败")
//                }
//                .start()
//
//        }
    }

    private fun callPhone() {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse(url))
        startActivity(intent)
    }

    private open inner class ZKWebChromeClient : WebChromeClient() {

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
        }

        override fun onShowCustomView(view: View, callback: WebChromeClient.CustomViewCallback) {
            super.onShowCustomView(view, callback)
        }
    }

    override fun onBackPressed() {
        if (webView != null) {
            if (webView!!.canGoBack()) {
                webView!!.goBack()
            } else {
                super.onBackPressed()
            }
        }
    }

}