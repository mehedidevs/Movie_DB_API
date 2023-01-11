package com.mehedi.k_movie_db.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import com.example.k_movie_db.databinding.FragmentHomeBinding
import com.mehedi.k_movie_db.ui.UpcomingViewModel
import com.mehedi.k_movie_db.ui.adapter.UpcomingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var viewModel: UpcomingViewModel

    private lateinit var adapter: UpcomingAdapter

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UpcomingAdapter()

        binding.rv.adapter = adapter

        lifecycleScope.launch {

            (adapter as PagingDataAdapter<*, *>)
                .loadStateFlow
                .collectLatest { loadState ->

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
        viewModel.upcomingMovies.observe(requireActivity()) {

            adapter.submitData(lifecycle, it)
        }
    }

    private fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            val errMessage = loadState.error.localizedMessage
            Log.i("TAG", "$errMessage ")
        }
    }
}
