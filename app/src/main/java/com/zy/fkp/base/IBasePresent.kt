package com.zy.fkp.base

/**
 * Created by zy on 2018/10/30
 * desc:
 */
interface IBasePresent<in V: IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}