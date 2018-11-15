package com.zy.fkp.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.zy.fkp.R
import com.zy.fkp.base.BaseFragment
import com.zy.fkp.config.IntentKey
import com.zy.fkp.mvp.contract.HomeCateContract
import com.zy.fkp.mvp.model.ResultsBean
import com.zy.fkp.mvp.presenter.HomeCatePresenter
import com.zy.fkp.ui.adapter.HomeCateAdapter
import com.zy.fkp.utils.CommItemDecoration
import kotlinx.android.synthetic.main.fragment_home_cate.*

/**
 * Created by zy
 * Date: 2018/11/13
 * desc:
 */
 
class HomeCateFragment: BaseFragment(), HomeCateContract.View, OnRefreshListener, OnLoadmoreListener{

    companion object {
        fun getInstance(cateName: String): HomeCateFragment{
            val cateFragment = HomeCateFragment()
            val bundle = Bundle()
            bundle.putString(IntentKey.CATEGORY_NAME, cateName)
            cateFragment.arguments = bundle
            return cateFragment
        }
    }

    private val cateName: String by lazy {
        arguments?.getString(IntentKey.CATEGORY_NAME)?:""
    }
    private val mPresenter by lazy { HomeCatePresenter(cateName) }
    private val mAdapter: HomeCateAdapter by lazy {
        HomeCateAdapter(context)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home_cate
    }

    override fun initView() {
        mPresenter.attachView(this)

        mLayoutStatusView = multipleStatusView

        cate_rv.layoutManager = LinearLayoutManager(context)
        cate_rv.addItemDecoration(CommItemDecoration(context!!,LinearLayoutManager.VERTICAL))
        cate_rv.adapter = mAdapter
        cate_srl.setOnRefreshListener(this)
        cate_srl.setOnLoadmoreListener(this)
    }

    override fun initListener() {
    }

    override fun lazyLoad() {
        showLoading()
        mPresenter.getData(false)
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
        mPresenter.getData(true)
    }

    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        mPresenter.getData(false)
    }


    override fun setCategoryItems(data: List<ResultsBean>) {
        cate_srl.finishRefresh()
        mAdapter.setData(data)
    }

    override fun addCategoryItems(data: List<ResultsBean>) {
        hideLoading()
        cate_srl.finishLoadmore()
        mAdapter.addData(data)
    }

    override fun setNoMore() {
        cate_srl.setEnableLoadmore(true)
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun hideLoading() {
        multipleStatusView.showContent()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}