package com.ashish.syfapplication.network


import com.ashish.syfapplication.BuildConfig
import com.ashish.syfapplication.model.constant.Constants.THE_CAT_API_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    private val retrofitClient: Retrofit.Builder by lazy {

        val levelType: Level


        if (BuildConfig.DEBUG)
            levelType = Level.BODY else levelType = Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(
            levelType
        )

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)

        Retrofit.Builder()
            .baseUrl(THE_CAT_API_URL)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: CatService by lazy {
        retrofitClient
            .build()
            .create(CatService::class.java)
    }

}