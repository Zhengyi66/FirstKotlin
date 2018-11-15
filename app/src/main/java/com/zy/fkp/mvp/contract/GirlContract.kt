package com.zy.fkp.mvp.contract

import com.zy.fkp.base.IBasePresent
import com.zy.fkp.base.IBaseView
import com.zy.fkp.mvp.model.GankBean

/**
 * Created by zy
 * Date: 2018/11/14
 * desc:
 */

interface GirlContract {

    interface View : IBaseView{
        fun addData(data: List<GankBean>)
    }

    interface Presenter : IBasePresent<View>{
        fun getGirlData()
    }

}