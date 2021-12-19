package com.example.ripple_task.Presentation.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ripple_task.Datalayer.DataSource.MainRepository
import com.example.ripple_task.DominLayer.IntentUser.MainIntent
import com.example.ripple_task.DominLayer.ViewState.MainState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class GitHubSearchViewModel  (private val repository: MainRepository, var searchWord : String) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    @ExperimentalCoroutinesApi
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    @ExperimentalCoroutinesApi
    val state: StateFlow<MainState>
        get() = _state
    init {
        handleIntent()
    }

    private fun handleIntent(){

        viewModelScope.launch() {
            userIntent.consumeAsFlow().collect {
                when(it){
                    is MainIntent.FetchUserRepo -> fetchRepos(searchWord)
                }
            }
        }
    }
    @ExperimentalCoroutinesApi
    private  fun fetchRepos(searchKey:String){
        viewModelScope.launch() {
            _state.value = MainState.Loading
            _state.value = try {
               MainState.Repo(repository.getAllReposData(searchKey))
            }catch (e:Exception){
                MainState.Error(e.localizedMessage)

            }
        }
    }


}