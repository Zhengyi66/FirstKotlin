package com.zy.fkp.api

import com.zy.fkp.mvp.model.BaseBean
import com.zy.fkp.mvp.model.CategoryResult
import com.zy.fkp.mvp.model.GankBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by zy
 * Date: 2018/10/30
 * desc:
 */

interface ApiService {

    //首页
    @GET("data/{category}/{count}/{page}")
    fun getHomeData(@Path("category") category: String, @Path("count") count: Int,
                    @Path("page") page: Int): Observable<CategoryResult>


    //妹子
    @GET("data/福利/10/{page}")
    fun getGirlData(@Path("page") page: Int): Observable<BaseBean<List<GankBean>>>

}