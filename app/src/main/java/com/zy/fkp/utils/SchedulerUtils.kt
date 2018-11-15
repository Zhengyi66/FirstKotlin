package com.zy.fkp.utils

/**
 * Created by zy
 * Date: 2018/11/6
 * desc:
 */
 
object SchedulerUtils{
    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}