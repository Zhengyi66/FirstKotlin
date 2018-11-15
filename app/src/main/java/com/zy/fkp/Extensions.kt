package com.zy.fkp

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by zy
 * Date: 2018/11/6
 * desc:
 */
fun Fragment.showToast(content: String): Toast{
    val toast = Toast.makeText(MyApplication.context,content,Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

fun Context.showToast(content: String): Toast{
    val toast = Toast.makeText(MyApplication.context,content,Toast.LENGTH_SHORT)
    toast.show()
    return toast
}