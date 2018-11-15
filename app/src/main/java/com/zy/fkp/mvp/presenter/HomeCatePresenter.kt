package com.zy.fkp.mvp.presenter

import android.util.Log
import com.zy.fkp.api.ApiManager
import com.zy.fkp.base.BasePresent
import com.zy.fkp.config.GlobalConfig
import com.zy.fkp.mvp.contract.HomeCateContract
import com.zy.fkp.utils.SchedulerUtils

/**
 * Created by zy
 * Date: 2018/11/13
 * desc:
 */
 
class HomeCatePresenter(cateName: String): BasePresent<HomeCateContract.View>(), HomeCateContract.Presenter{

    private val mCateName = cateName
    private var mPage: Int = 0

    override fun getData(isRefresh: Boolean) {
        if (isRefresh) {
            mPage = 1
        } else {
            mPage++
        }
        ApiManager.getGankApi()
                .getHomeData(mCateName, GlobalConfig.HOME_CATE_COUNT, mPage)
                .compose(SchedulerUtils.ioToMain())
                .subscribe({
                    if (it != null && !it.error){
                        if (isRefresh){
                            mRootView?.setCategoryItems(it.results)
                        }else{
                            mRootView?.addCategoryItems(it.results)
                        }

                        if (it.results.size < GlobalConfig.HOME_CATE_COUNT)
                            mRootView?.setNoMore()
                    }
                },{

                })

    }
}