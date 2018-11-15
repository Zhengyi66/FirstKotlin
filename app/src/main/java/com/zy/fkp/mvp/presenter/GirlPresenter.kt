package com.zy.fkp.mvp.presenter

import com.zy.fkp.api.ApiManager
import com.zy.fkp.base.BasePresent
import com.zy.fkp.mvp.contract.GirlContract
import com.zy.fkp.utils.SchedulerUtils

/**
 * Created by zy
 * Date: 2018/11/14
 * desc:
 */

class GirlPresenter : BasePresent<GirlContract.View>(), GirlContract.Presenter {

    private var mPage: Int = 1

    override fun getGirlData() {
        mPage++
        ApiManager.getGankApi().getGirlData(mPage)
                .compose(SchedulerUtils.ioToMain())
                .subscribe({
                    if (it != null && !it.results.isEmpty()) {
                        mRootView?.apply {
                            addData(it.results)
                        }
                    }
                }, {
                    //异常处理
                })
    }


}