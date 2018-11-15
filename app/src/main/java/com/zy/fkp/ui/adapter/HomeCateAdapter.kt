package com.zy.fkp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zy.fkp.MyApplication
import com.zy.fkp.R
import com.zy.fkp.base.adapter.CommonRecyclerAdapter
import com.zy.fkp.base.adapter.CommonRecyclerHolder
import com.zy.fkp.base.adapter.ListenerWithPosition
import com.zy.fkp.mvp.model.ResultsBean
import com.zy.fkp.ui.activity.WebViewActivity
import com.zy.fkp.utils.TimeUtils

/**
 * Created by zy
 * Date: 2018/11/13
 * desc:
 */

class HomeCateAdapter(context: Context?) : CommonRecyclerAdapter<ResultsBean>(context, null, R.layout.item_home_cate),
        ListenerWithPosition.OnClickWithPositionListener<CommonRecyclerHolder> {

    override fun convert(holder: CommonRecyclerHolder?, t: ResultsBean?) {
        if (t != null) {
            val imageView = holder?.getView<ImageView>(R.id.icon_iv)
                imageView?.visibility = View.VISIBLE
                if (t.images != null && t.images.isNotEmpty()) {
                    Glide.with(mContext)
                            .load(t.images[0])
                            .apply(MyApplication.options!!)
                            .into(imageView!!)

                } else {
                    Glide.with(mContext).load(R.mipmap.ic_launcher).into(imageView!!)
                }

            holder?.setTextViewText(R.id.title_tv, t.desc)
            holder?.setTextViewText(R.id.name_tv, t.who)
            holder?.setTextViewText(R.id.cate_tv, t.source)
            holder?.setTextViewText(R.id.time_tv, TimeUtils.dateFormat(t.publishedAt))
            holder.setOnClickListener(this,R.id.cate_item_layout)
        }
    }

    override fun onClick(v: View?, position: Int, holder: CommonRecyclerHolder?) {
        WebViewActivity.startActivity(mContext,mData[position].url)
    }


}