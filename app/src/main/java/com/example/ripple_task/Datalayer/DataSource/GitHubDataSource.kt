package com.example.ripple_task

import com.example.ripple_task.DominLayer.Model.ResponseRepos


interface GitHubDataSource {
    suspend fun getRepos (searchKey:String) : ResponseRepos?=null
}