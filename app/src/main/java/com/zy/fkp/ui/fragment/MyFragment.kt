package com.zy.fkp.ui.fragment

import com.zy.fkp.R
import com.zy.fkp.base.BaseFragment

/**
 * Created by zy
 * Date: 2018/11/6
 * desc:
 */
 
class MyFragment : BaseFragment(){

    companion object {
        fun getInstance() : MyFragment{
            return MyFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_my
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun lazyLoad() {
    }

}