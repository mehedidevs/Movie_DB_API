package com.mehedi.k_movie_db.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.k_movie_db.R
import com.mehedi.k_movie_db.ui.adapter.UpcomingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UpcomingViewModel


    lateinit var adapter: UpcomingAdapter

    lateinit var rv: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById<RecyclerView>(R.id.rv)

        adapter = UpcomingAdapter()

        rv.adapter = adapter

        lifecycleScope.launch {
            (adapter as PagingDataAdapter<*, *>).loadStateFlow.collectLatest { loadState ->

                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        Log.i("TAG", "Loading.... ")
                    }
                    is LoadState.Error -> {

                        bind(loadState.refresh)

                    }
                    is LoadState.NotLoading -> {
                        Log.i("TAG", "Not Loading.... ")
                    }
                }
            }
        }




        viewModel = ViewModelProvider(this)[UpcomingViewModel::class.java]
        viewModel.upcomingMovies.observe(this) {

            adapter.submitData(lifecycle, it)


        }


    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            var errMessage = loadState.error.localizedMessage
            Log.i("TAG", "$errMessage ")

        }

    }}