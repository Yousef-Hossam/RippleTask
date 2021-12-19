package com.example.ripple_task.Datalayer.DataSource

import com.example.ripple_task.GitHubDataSource

class MainRepository (private val gitHubSearch : GitHubDataSource) {
    suspend fun getAllReposData(searchKey:String) = gitHubSearch.getRepos(searchKey)
}