package com.zy.fkp.ui.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zy.fkp.MyApplication
import com.zy.fkp.R
import com.zy.fkp.base.adapter.CommonRecyclerAdapter
import com.zy.fkp.base.adapter.CommonRecyclerHolder
import com.zy.fkp.base.adapter.ListenerWithPosition
import com.zy.fkp.mvp.model.GankBean
import com.zy.fkp.ui.activity.ImageActivity

/**
 * Created by zy
 * Date: 2018/11/14
 * desc:
 */
 
class GirlAdapter(context: Context?) : CommonRecyclerAdapter<GankBean>(context,null, R.layout.item_girl){

    override fun convert(holder: CommonRecyclerHolder?, t: GankBean?) {
        val imageView = holder?.getView<ImageView>(R.id.girl_iv)
        if (t != null){
            Glide.with(mContext)
                    .load(t.url)
                    .apply(MyApplication.options!!)
                    .into(imageView!!)

            imageView!!.setOnClickListener{
                ImageActivity.startActivity(mContext, imageView,t.url)
            }
        }
    }


}