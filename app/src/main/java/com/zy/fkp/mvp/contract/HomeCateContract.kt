package com.zy.fkp.mvp.contract

import com.zy.fkp.base.BasePresent
import com.zy.fkp.base.IBasePresent
import com.zy.fkp.base.IBaseView
import com.zy.fkp.mvp.model.ResultsBean

/**
 * Created by zy
 * Date: 2018/11/13
 * desc:
 */
 
interface HomeCateContract{

    interface View: IBaseView{

        fun setCategoryItems(data: List<ResultsBean>)

        fun addCategoryItems(data: List<ResultsBean>)

        fun setNoMore()

    }

    interface Presenter: IBasePresent<View>{
        fun getData(isRefresh: Boolean)
    }

}