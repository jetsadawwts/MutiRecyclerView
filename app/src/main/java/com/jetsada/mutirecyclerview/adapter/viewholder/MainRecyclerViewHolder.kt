package com.jetsada.mutirecyclerview.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.jetsada.mutirecyclerview.R
import com.jetsada.mutirecyclerview.adapter.item.MainRecyclerViewHolderItem
import com.jetsada.mutirecyclerview.databinding.ItemDirectorBinding
import com.jetsada.mutirecyclerview.databinding.ItemMovieBinding
import com.jetsada.mutirecyclerview.databinding.ItemTitleBinding
import com.jetsada.mutirecyclerview.loadImage

sealed class MainRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    var itemClickListener: ((view: View, item: MainRecyclerViewHolderItem, position: Int) -> Unit)? = null

    class TitleViewHolder(private val binding: ItemTitleBinding) : MainRecyclerViewHolder(binding){
        fun bind(title: MainRecyclerViewHolderItem.Title){
            binding.textViewTitle.text = title.title
            binding.textViewAll.setOnClickListener {
                itemClickListener?.invoke(it, title, adapterPosition)
            }
        }
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) : MainRecyclerViewHolder(binding){
        fun bind(movie: MainRecyclerViewHolderItem.Movie){
            if(movie.thumbnail.toString().isNotEmpty()) {
                movie.thumbnail?.let { binding.imageViewMovie.loadImage(it) }
            }
            binding.root.setOnClickListener {
                itemClickListener?.invoke(it, movie, adapterPosition)
            }
        }
    }

    class DirectorViewHolder(private val binding: ItemDirectorBinding) : MainRecyclerViewHolder(binding){
        fun bind(director: MainRecyclerViewHolderItem.Director){
            binding.imageViewDirector.loadImage(director.avatar)
            binding.textViewName.text = director.name
            binding.textViewMovies.text = binding.textViewMovies.context.getString(R.string.total_movies, director.movie_count)
            binding.root.setOnClickListener {
                itemClickListener?.invoke(it, director, adapterPosition)
            }
        }
    }
}
