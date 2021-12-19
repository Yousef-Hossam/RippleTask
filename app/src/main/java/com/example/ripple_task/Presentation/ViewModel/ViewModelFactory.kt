package com.example.ripple_task.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ripple_task.Datalayer.DataSource.MainRepository
import com.example.ripple_task.GitHubDataSource

class ViewModelFactory(private val dataHelper: GitHubDataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitHubSearchViewModel::class.java)) {
            val search_Word  = ""
            return GitHubSearchViewModel(MainRepository(dataHelper),search_Word) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}