package com.zy.fkp

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import kotlin.properties.Delegates

/**
 * Created by zy
 * Date: 2018/10/30
 * desc:
 */

class MyApplication : Application() {

    //companion object 修饰静态方法，可以使用类名.方法 形式调用
    companion object {
        private val TAG = "MyApplication"
        var context: MyApplication? = null

        val options : RequestOptions? =  RequestOptions()
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        initConfig()
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
        initOptions()
    }

    private fun initOptions() {
        options!!.placeholder(R.mipmap.ic_launcher)
        options!!.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        options!!.dontAnimate()
    }

    private fun initConfig() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)//隐藏线程信息  默认显示
                .methodCount(0) //打印行数 默认2
                .methodOffset(7)
                .tag("retrofit_log")
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity?.componentName?.className)
        }

        override fun onActivityStarted(activity: Activity?) {
            Log.d(TAG, "onStart: " + activity?.componentName?.className)
        }

        override fun onActivityResumed(activity: Activity?) {
        }

        override fun onActivityPaused(activity: Activity?) {
        }

        override fun onActivityStopped(activity: Activity?) {
        }

        override fun onActivityDestroyed(activity: Activity?) {
            Log.d(TAG, "onDestroy: " + activity?.componentName?.className)
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

        }

    }


}