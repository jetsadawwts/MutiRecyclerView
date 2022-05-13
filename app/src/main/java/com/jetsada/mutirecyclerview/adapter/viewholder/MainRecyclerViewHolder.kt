package com.jetsada.mutirecyclerview.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.jetsada.mutirecyclerview.R
import com.jetsada.mutirecyclerview.Utils.loadImage
import com.jetsada.mutirecyclerview.adapter.item.MainRecyclerViewHolderItem
import com.jetsada.mutirecyclerview.databinding.ItemDirectorBinding
import com.jetsada.mutirecyclerview.databinding.ItemMovieBinding
import com.jetsada.mutirecyclerview.databinding.ItemTitleBinding

sealed class MainRecyclerViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    class TitleViewHolder(private val binding: ItemTitleBinding) : MainRecyclerViewHolder(binding) {
        fun bind(title: MainRecyclerViewHolderItem.Title) {
            binding.textViewTitle.text = title.title
        }
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) : MainRecyclerViewHolder(binding) {
        fun bind(movie: MainRecyclerViewHolderItem.Movie) {
            binding.imageViewMovie.loadImage(movie.thumbnail)
        }
    }

    class DirectorViewHolder(private val binding: ItemDirectorBinding) : MainRecyclerViewHolder(binding) {
        fun bind(director: MainRecyclerViewHolderItem.Director) {
            binding.imageViewDirector.loadImage(director.avatar)
            binding.textViewName.text = director.name
            binding.textViewMovies.text = binding.textViewMovies.context.getString(R.string.total_movies, director.movie_count)
        }
    }


}
