package com.example.ripple_task.Datalayer.Network

import com.example.ripple_task.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(Utils.baseUrL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}