package com.zy.fkp.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zy.fkp.MyApplication
import com.zy.fkp.R
import com.zy.fkp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_image.*

/**
 * Created by zy
 * Date: 2018/11/15
 * desc:
 */
 
class ImageActivity : BaseActivity(){

    private lateinit var mImageView: ImageView

    companion object {
        val IMG = "img"
        fun startActivity(context: Context, imageView: ImageView, url: String){
            val intent = Intent(context,ImageActivity::class.java)
            intent.putExtra(IMG,url)
            if (Build.VERSION.SDK_INT > 21){
                context.startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(context as Activity,imageView,"img").toBundle())
            }else{
                context.startActivity(intent)
            }
        }
    }

    override fun layoutId(): Int {
        return R.layout.activity_image
    }

    override fun initData() {
    }

    override fun initView() {
        mImageView = image_iv
        Glide.with(this)
                .load(intent.getStringExtra(IMG))
                .apply(MyApplication.options!!)
                .into(mImageView)
    }

    override fun initListener() {
        mImageView.setOnClickListener{
            supportFinishAfterTransition()
        }
    }

    override fun loadDate() {
    }
}