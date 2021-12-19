package com.example.ripple_task.Datalayer.Network

import com.example.ripple_task.DominLayer.Model.ResponseRepos
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories?")
    suspend fun getAllRepo(@Query("q") query: String ) : ResponseRepos
}