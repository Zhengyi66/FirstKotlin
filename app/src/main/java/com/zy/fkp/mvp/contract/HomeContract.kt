package com.zy.fkp.mvp.contract

import com.zy.fkp.base.IBasePresent
import com.zy.fkp.base.IBaseView

/**
 * Created by zy
 * Date: 2018/11/6
 * desc:
 */
 
interface HomeContract{

    interface View : IBaseView{

        /**
         * 设置banner
         */
        fun setBanner(banners: List<String>)


    }

    interface Presenter : IBasePresent<View>{

        fun getBanner()

    }

}