package com.zy.fkp.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by zy on 2018/10/30
 * desc:
 */
open class BasePresent<T : IBaseView> : IBasePresent<T>{

    var mRootView: T? = null

    private var compositeDisposable = CompositeDisposable()

    override fun attachView(mRootView: T) {
        this.mRootView = mRootView
    }

    override fun detachView() {
        mRootView = null

        //activity结束时取消所有订阅
        if (!compositeDisposable.isDisposed)
            compositeDisposable.clear()
    }

    fun addSubscription(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    private val isViewAttached: Boolean
        get() = mRootView !=null

    fun checkViewAttached(){
        if (!isViewAttached)throw MvpViewNotAttachedExeption()

    }

    private class MvpViewNotAttachedExeption : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")

}