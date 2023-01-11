package com.mehedi.k_movie_db.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.k_movie_db.databinding.ItemMovieBinding
import com.mehedi.k_movie_db.data.dto.MovieResult
import com.mehedi.k_movie_db.utils.Util

class UpcomingAdapter : PagingDataAdapter<MovieResult, UpcomingAdapter.UpcomingViewHolder>(
    Comparator
) {

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        getItem(position).let {

            Glide.with(holder.binding.root.context)
                .load(Util.posterUrlMake(it?.posterPath))
                .into(holder.binding.image)

//            Glide.with(holder.binding.root.context)
//                .load("${Constant.IMAGE_PATH}${it!!.posterPath}")
//                .into(holder.binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UpcomingViewHolder(binding)
    }

    class UpcomingViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val Comparator =
            object : DiffUtil.ItemCallback<MovieResult>() {
                override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {

                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: MovieResult,
                    newItem: MovieResult
                ): Boolean {

                    return oldItem == newItem
                }
            }
    }
}
