package com.example.ripple_task.DominLayer.ViewState

import com.example.ripple_task.DominLayer.Model.ResponseRepos


sealed class MainState{
     object Idle : MainState()
     object Loading : MainState()
     data class Repo (val repo : ResponseRepos?=null) : MainState()
     data class Error (val errorMsg : String) :   MainState()
}
