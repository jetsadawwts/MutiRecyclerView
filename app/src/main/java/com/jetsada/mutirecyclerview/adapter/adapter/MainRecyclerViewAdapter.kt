package com.jetsada.mutirecyclerview.adapter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jetsada.mutirecyclerview.R
import com.jetsada.mutirecyclerview.adapter.item.MainRecyclerViewHolderItem
import com.jetsada.mutirecyclerview.adapter.viewholder.MainRecyclerViewHolder
import com.jetsada.mutirecyclerview.databinding.ItemDirectorBinding
import com.jetsada.mutirecyclerview.databinding.ItemMovieBinding
import com.jetsada.mutirecyclerview.databinding.ItemTitleBinding

class MainRecyclerViewAdapter : RecyclerView.Adapter<MainRecyclerViewHolder>() {

    var items = listOf<MainRecyclerViewHolderItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    var itemClickListener: ((view: View, item: MainRecyclerViewHolderItem, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        return when(viewType){
            R.layout.item_title -> MainRecyclerViewHolder.TitleViewHolder(
                ItemTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_movie -> MainRecyclerViewHolder.MovieViewHolder(
                ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_director -> MainRecyclerViewHolder.DirectorViewHolder(
                ItemDirectorBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.itemClickListener = itemClickListener
        when(holder){
            is MainRecyclerViewHolder.DirectorViewHolder -> holder.bind(items[position] as MainRecyclerViewHolderItem.Director)
            is MainRecyclerViewHolder.MovieViewHolder -> holder.bind(items[position] as MainRecyclerViewHolderItem.Movie)
            is MainRecyclerViewHolder.TitleViewHolder -> holder.bind(items[position] as MainRecyclerViewHolderItem.Title)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is MainRecyclerViewHolderItem.Director -> R.layout.item_director
            is MainRecyclerViewHolderItem.Movie -> R.layout.item_movie
            is MainRecyclerViewHolderItem.Title -> R.layout.item_title
        }
    }
}