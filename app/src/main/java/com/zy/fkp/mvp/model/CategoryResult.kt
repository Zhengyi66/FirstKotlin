package com.zy.fkp.mvp.model

/**
 * Created by zy
 * Date: 2018/11/6
 * desc:
 */
 
data class CategoryResult(
        var error: Boolean,
        var results: List<ResultsBean> = emptyList()
)

data class ResultsBean(
        var _id:String,
        var createdAt: String,
        var desc: String,
        var publishedAt: String,
        var source: String,
        var type: String,
        var url: String,
        var used: Boolean,
        var who: String,
        var images: List<String> = emptyList()
)
