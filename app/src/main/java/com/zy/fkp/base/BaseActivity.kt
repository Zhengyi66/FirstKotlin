package com.zy.fkp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.classic.common.MultipleStatusView

/**
 * Created by zy on 2018/10/30
 * desc:
 */
abstract class BaseActivity : AppCompatActivity(){

    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        initView()
        initListener()
        loadDate()

        mLayoutStatusView?.setOnClickListener(mRetryClickListener)

    }

    val mRetryClickListener: View.OnClickListener = View.OnClickListener { loadDate() }

    /**
     * 加载布局ID
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化布局
     */
    abstract fun initView()

    /**
     * 初始化监听
     */
    abstract fun initListener()

    /**
     * 加载数据
     */
    abstract fun loadDate()

}