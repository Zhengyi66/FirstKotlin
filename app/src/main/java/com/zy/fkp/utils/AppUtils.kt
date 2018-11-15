package com.zy.fkp.utils

import android.os.Build

/**
 * Created by zy
 * Date: 2018/10/31
 * desc:
 */
 
class AppUtils private constructor(){

    init {
        throw Error("Do not need instantiate!")
    }

    companion object {


        fun getMobileModel(): String{
            var mode:String? = Build.MODEL
            mode = mode?.trim(){it <= ' '} ?: ""
            return mode
        }
    }

}