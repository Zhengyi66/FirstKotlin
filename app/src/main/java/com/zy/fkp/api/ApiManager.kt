package com.zy.fkp.api

import com.zy.fkp.utils.Preference
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by zy
 * Date: 2018/10/30
 * desc:
 */
 
object ApiManager{

    const val BASE_URL = "http://gank.io/api/"
    private var token:String by Preference("token","")


    private var gankApi: ApiService? = null
    private val okHttpClient = OkHttpClient()

    fun getGankApi(): ApiService {
        if (gankApi == null) {

            val retrofit = Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            gankApi = retrofit.create(ApiService::class.java)
        }
        return gankApi!!
    }


//    val service: ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
//        getRetrofit().create(ApiService::class.java)
//    }
//
//    private fun getRetrofit(): Retrofit{
//        return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(getOkHttpClient())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//    }
//
//
    private fun getOkHttpClient(): OkHttpClient{
        //添加一个log拦截器，打印所有log
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        //设置请求过滤的水平，body basic headers
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        //设置缓存大小和位置
//        val cacheFile = File(MyApplication.context.cacheDir,"cache")
//        val cache = Cache(cacheFile,1024 * 1024 *50)

        return OkHttpClient.Builder()
//                .addInterceptor(addQueryParameterIntercetor())
//                .addInterceptor(addHeaderInterceptor())
//                .addInterceptor(addCacheInterceptor())
                .addInterceptor(httpLoggingInterceptor)
//                .cache(cache)
                .connectTimeout(60L,TimeUnit.SECONDS)
                .readTimeout(60L,TimeUnit.SECONDS)
                .writeTimeout(60L,TimeUnit.SECONDS)
                .build()

    }
//
//    /**
//     * 设置公共参数
//     */
//    private fun addQueryParameterIntercetor() : Interceptor{
//        return Interceptor { chain ->
//            val originRequest = chain.request()
//            val request : Request
//            val modifiedUrl = originRequest.url().newBuilder()
//                    .addQueryParameter("udid", "d2807c895f0348a180148c9dfa6f2feeac0781b5")
//                    .addQueryParameter("deviceModel",AppUtils.getMobileModel())
//                    .build()
//            request = originRequest.newBuilder().url(modifiedUrl).build()
//            chain.proceed(request)
//        }
//    }
//
//    /**
//     * 设置头
//     */
//    private fun addHeaderInterceptor(): Interceptor{
//        return Interceptor { chain ->
//            val originalRequest = chain.request()
//            val requestBuilder = originalRequest.newBuilder()
//                    .header("token", token)
//                    .method(originalRequest.method(),originalRequest.body())
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }
//    }
//
//    /**
//     * 设置缓存
//     */
//    private fun addCacheInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            var request = chain.request()
//            if (!NetworkUtil.isNetworkAvailable(MyApplication.context)) {
//                request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE)
//                        .build()
//            }
//            val response = chain.proceed(request)
//            if (NetworkUtil.isNetworkAvailable(MyApplication.context)) {
//                val maxAge = 0
//                // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
//                response.newBuilder()
//                        .header("Cache-Control", "public, max-age=" + maxAge)
//                        .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
//                        .build()
//            } else {
//                // 无网络时，设置超时为4周  只对get有用,post没有缓冲
//                val maxStale = 60 * 60 * 24 * 28
//                response.newBuilder()
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                        .removeHeader("nyn")
//                        .build()
//            }
//            response
//        }
//    }



}