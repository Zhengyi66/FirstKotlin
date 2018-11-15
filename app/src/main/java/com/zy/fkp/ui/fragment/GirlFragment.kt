package com.zy.fkp.ui.fragment

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.zy.fkp.R
import com.zy.fkp.base.BaseFragment
import com.zy.fkp.mvp.contract.GirlContract
import com.zy.fkp.mvp.model.GankBean
import com.zy.fkp.mvp.presenter.GirlPresenter
import com.zy.fkp.ui.adapter.GirlAdapter
import kotlinx.android.synthetic.main.fragment_girl.*

/**
 * Created by zy
 * Date: 2018/11/6
 * desc:
 */
 
class GirlFragment : BaseFragment(), GirlContract.View, OnLoadmoreListener{

    lateinit var mRefreshLayout: SmartRefreshLayout
    lateinit var mRecyclerView:  RecyclerView

    private val mPresenter by lazy { GirlPresenter() }
    private val mAdapter by lazy {  GirlAdapter(context) }

    companion object {
        fun getInstance(): GirlFragment{
            return GirlFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_girl
    }

    override fun initView() {
        mPresenter.attachView(this)

        mRefreshLayout = girl_refresh_layout
        mRecyclerView = girl_recycler

        mRefreshLayout.setEnableRefresh(false)
        mRecyclerView.layoutManager = GridLayoutManager(context,2)
        mRecyclerView.adapter = mAdapter
    }

    override fun initListener() {
        mRefreshLayout.setOnLoadmoreListener(this)
    }

    override fun lazyLoad() {
        mPresenter.getGirlData()
    }

    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        mPresenter.getGirlData()
    }

    override fun addData(data: List<GankBean>) {
        mRefreshLayout.finishLoadmore()
        mAdapter.addData(data)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}