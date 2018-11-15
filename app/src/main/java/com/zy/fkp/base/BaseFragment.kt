package com.zy.fkp.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.widget.ContentLoadingProgressBar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.classic.common.MultipleStatusView

/**
 * Created by zy
 * Date: 2018/10/30
 * desc:
 */
 
abstract class BaseFragment: Fragment(){

    /**
     * activity是否加载完毕
     */
    private var isViewPreparer = false

    /**
     * 数据是否加载完成
     */
    private var hasLoadDate = false

    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(),null)
    }

    /**
     * 手动设置fragment属性
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPreparer = true
        initView()
        initListener()
        lazyLoadDataIfPrepared()
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    private fun lazyLoadDataIfPrepared(){
        if (userVisibleHint && isViewPreparer && !hasLoadDate){
            lazyLoad()
            hasLoadDate = true
        }
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener { lazyLoad() }

    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 初始化布局
     */
    abstract fun initView()

    /**
     * 初始化监听
     */
    abstract fun initListener()

    /**
     * 懒加载
     */
    abstract fun lazyLoad()

}