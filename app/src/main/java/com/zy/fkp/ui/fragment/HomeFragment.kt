package com.zy.fkp.ui.fragment

import android.app.ProgressDialog
import android.support.v4.app.Fragment
import android.util.Log
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import com.zy.fkp.R
import com.zy.fkp.base.BaseFragment
import com.zy.fkp.config.GlobalConfig
import com.zy.fkp.mvp.contract.HomeContract
import com.zy.fkp.mvp.presenter.HomePresenter
import com.zy.fkp.showToast
import com.zy.fkp.ui.adapter.BaseFragmentAdapter
import com.zy.fkp.utils.GlideImageLoader
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by zy
 * Date: 2018/11/6
 * desc:
 */
 
class HomeFragment : BaseFragment(), HomeContract.View, OnBannerListener {

    private val mPresenter by lazy { HomePresenter() }
    private val mFragment = ArrayList<Fragment>()
    val titles = listOf<String>(GlobalConfig.CATEGORY_NAME_APP, GlobalConfig.CATEGORY_NAME_ANDROID, GlobalConfig.CATEGORY_NAME_IOS, GlobalConfig.CATEGORY_NAME_FRONT_END, GlobalConfig.CATEGORY_NAME_RECOMMEND, GlobalConfig.CATEGORY_NAME_RESOURCE)


    companion object {
        fun getInstance() : HomeFragment{
            return HomeFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        mPresenter.attachView(this)

        for (i in titles.indices){
            mFragment.add(HomeCateFragment.getInstance(titles[i]))
        }

        //init banner
        banner_main.setOnBannerListener(this)
        banner_main.setIndicatorGravity(BannerConfig.CENTER)

        pager_main.adapter = BaseFragmentAdapter(childFragmentManager,mFragment,titles)
        tab_main.setupWithViewPager(pager_main)

    }

    override fun initListener() {

    }

    override fun lazyLoad() {
        mPresenter.getBanner()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }

    override fun setBanner(banners: List<String>) {
        banner_main.setImages(banners).setImageLoader(GlideImageLoader()).start()
    }

    override fun OnBannerClick(position: Int) {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}