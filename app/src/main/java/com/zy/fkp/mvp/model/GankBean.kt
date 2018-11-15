package com.zy.fkp.mvp.model

/**
 * Created by zy
 * Date: 2018/11/14
 * desc:
 */

data class GankBean(
        val _id: String,
        val createdAt: String,
        val desc: String,
        val images: Array<String>,
        val publishedAt: String,
        val source: String,
        val type: String,
        val url: String,
        val used: Boolean,
        val who: String

){
    fun hasImg():Boolean {
        return images != null
    }
    fun create() = createdAt.substring(0,10)

}