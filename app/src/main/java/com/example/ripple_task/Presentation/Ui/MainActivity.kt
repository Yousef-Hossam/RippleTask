package com.example.ripple_task.Presentation.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ripple_task.Datalayer.DataSource.GitHubDataSourceImp
import com.example.ripple_task.Datalayer.Network.RetrofitBuilder
import com.example.ripple_task.DominLayer.IntentUser.MainIntent
import com.example.ripple_task.DominLayer.Model.ResponseRepos
import com.example.ripple_task.DominLayer.ViewState.MainState
import com.example.ripple_task.Presentation.Ui.Adapter.RepoDataAdapter
import com.example.ripple_task.Presentation.ViewModel.GitHubSearchViewModel
import com.example.ripple_task.R
import com.example.ripple_task.Presentation.ViewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var repoDataAdapter = RepoDataAdapter((arrayListOf()))
    private lateinit var mainViewModel: GitHubSearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        search_button.setOnClickListener {
            repoDataAdapter.clearData()
            mainViewModel.searchWord = searchView.text.toString().toLowerCase().trim()
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUserRepo)
            }
        }


    } //SearchFun please Use KeyWord joe in mockmode to get data from json

    private fun setupUI() {
        recyview.layoutManager = LinearLayoutManager(this)
        recyview.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyview.context,
                    (recyview.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyview.adapter = repoDataAdapter
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                GitHubDataSourceImp(
                    RetrofitBuilder.apiService,
                    this
                )
            )
        ).get(GitHubSearchViewModel::class.java)
    }
    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when(it){
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        search_button.visibility = View.GONE
                        tv_error.visibility=View.GONE
                        image_error.visibility=View.GONE
                        progressBar.visibility = View.VISIBLE

                    }

                    is MainState.Repo -> {
                        progressBar.visibility = View.GONE
                        search_button.visibility = View.VISIBLE
                        tv_error.visibility=View.GONE
                        image_error.visibility=View.GONE
                        renderList(it.repo)
                    }
                    is MainState.Error -> {
                        repoDataAdapter.clearData()
                        repoDataAdapter.notifyDataSetChanged()
                        progressBar.visibility = View.GONE
                        search_button.visibility = View.VISIBLE
                        recyview.visibility = View.GONE
                        tv_error.visibility=View.VISIBLE
                        image_error.visibility=View.VISIBLE
                        tv_error.setText("No Data Found")
                    }
                }

            }
        }
    }




    private fun renderList(repos: ResponseRepos?=null) {
        if(repos!=null){
            recyview.visibility = View.VISIBLE
            repos.let { listOfUsers -> listOfUsers.let { repoDataAdapter.addData(it.items) } }
            repoDataAdapter.notifyDataSetChanged()
        }

    } //fill arraylist
}



