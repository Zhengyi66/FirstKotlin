package com.zy.fkp.mvp.presenter

import com.zy.fkp.api.ApiManager
import com.zy.fkp.base.BasePresent
import com.zy.fkp.mvp.contract.HomeContract
import com.zy.fkp.mvp.model.CategoryResult
import com.zy.fkp.mvp.model.PictureBean
import com.zy.fkp.mvp.model.ResultsBean
import com.zy.fkp.utils.SchedulerUtils
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription

/**
 * Created by zy
 * Date: 2018/11/6
 * desc:
 */
class HomePresenter : BasePresent<HomeContract.View>(), HomeContract.Presenter {

    override fun getBanner() {
        val disposable = ApiManager.getGankApi()
                .getHomeData("福利", 5, 1)
                .compose(SchedulerUtils.ioToMain())
                .subscribe({ t: CategoryResult ->
                    if (t != null && !t.results.isEmpty()) {
                        mRootView?.apply {
                            val imgUrls = ArrayList<String>()
                            val pictures: MutableList<PictureBean> = ArrayList()
                            for (result: ResultsBean in t.results) {
                                if (!result.url.isEmpty()) {
                                    imgUrls.add(result.url)
                                }
                                pictures.add(PictureBean(result.desc?.let { result.desc }
                                        ?: "unKnow", result.url))
                            }
                            setBanner(imgUrls)
                        }
                    }
                }, { t: Throwable? ->
                    //处理异常
                })
        addSubscription(disposable)
    }




}