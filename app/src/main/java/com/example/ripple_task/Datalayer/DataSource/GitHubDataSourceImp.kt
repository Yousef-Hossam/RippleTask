package com.example.ripple_task.Datalayer.DataSource

import android.content.Context
import com.example.ripple_task.Datalayer.Network.ApiService
import com.example.ripple_task.DominLayer.Model.ResponseRepos
import com.example.ripple_task.GitHubDataSource
import com.example.ripple_task.R
import com.example.ripple_task.Utils
import com.google.gson.Gson

class GitHubDataSourceImp (private val apiService : ApiService,val c : Context) :
    GitHubDataSource {
    override suspend fun getRepos(searchKey:String): ResponseRepos {
        var testModel : ResponseRepos

        if(com.example.ripple_task.BuildConfig.BUILD_VARIANT.equals(Utils.BUILD_MODE_MOCK)){ //check BuildVariant Mood
            val s  = Utils.getDataFromJsonFile(c,c.resources.openRawResource(R.raw.repo_mock),searchKey)  //get Data From Json
            var gson = Gson()
             testModel = gson.fromJson(s, ResponseRepos::class.java)

        }else{
            testModel = apiService.getAllRepo(searchKey) // get Data From Api
        }
             return  testModel
    }
}