package com.zy.fkp.ui.activity

import android.content.Context
import android.content.Intent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.zy.fkp.R
import com.zy.fkp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Created by zy
 * Date: 2018/11/14
 * desc:
 */

class WebViewActivity : BaseActivity() {

    lateinit var mWebView: WebView
    lateinit var mProgressBar: ProgressBar
    lateinit var mUrl: String

    companion object {
        const val WEB_URL = "web_url"
        fun startActivity(context: Context, url: String) {
            val intent = Intent(context,WebViewActivity::class.java)
            intent.putExtra(WebViewActivity.WEB_URL,url)
            context.startActivity(intent)
        }

    }

    override fun layoutId(): Int {
        return R.layout.activity_webview
    }

    override fun initData() {
        mUrl = intent.getStringExtra(WEB_URL)
    }

    override fun initView() {
        mWebView = webview
        mProgressBar = progress_bar
        initWebView()
    }

    private fun initWebView() {
        mWebView.webViewClient = MyWebClient()
        mWebView.webChromeClient = MyWebChrome()
        val setting = mWebView.settings
        setting.javaScriptCanOpenWindowsAutomatically = true
        setting.allowFileAccess = true
        setting.setSupportZoom(true)
        setting.javaScriptCanOpenWindowsAutomatically = true
        setting.cacheMode = WebSettings.LOAD_DEFAULT
        setting.setAppCacheEnabled(true)
        setting.javaScriptEnabled = true

        mWebView.loadUrl(mUrl)
    }

    override fun initListener() {
    }

    override fun loadDate() {
    }

    private inner class MyWebChrome : WebChromeClient(){

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            mProgressBar.progress = newProgress
        }

    }

    private inner class MyWebClient : WebViewClient(){

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            mProgressBar.visibility = View.GONE
        }

    }

}