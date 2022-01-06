package com.homebase.core.service

import com.homebase.core.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}