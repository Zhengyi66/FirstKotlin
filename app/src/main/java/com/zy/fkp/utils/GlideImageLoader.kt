package com.zy.fkp.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader
import com.zy.fkp.MyApplication

/**
 * Created by zy
 * Date: 2018/11/7
 * desc:
 */
 
class GlideImageLoader: ImageLoader(){



    override fun displayImage(context: Context, path: Any, imageView: ImageView) {

        Glide.with(context)
                .load(path)
                .apply(MyApplication.options!!)
                .into(imageView)

        val uri = Uri.parse(path as String)
        imageView.setImageURI(uri)
    }
}