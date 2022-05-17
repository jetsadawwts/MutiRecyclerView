package com.jetsada.mutirecyclerview.adapter.item

sealed class MainRecyclerViewHolderItem {
    
   data class Title(
            val id: Int,
            val title: String
        ) : MainRecyclerViewHolderItem()

   data class Movie(
            val id: Int?,
            val title: String?,
            val thumbnail: String?,
            val release_date: String?
        ) : MainRecyclerViewHolderItem()

   data class Director(
            val id: Int,
            val name: String,
            val avatar: String,
            val movie_count: Int
        ) : MainRecyclerViewHolderItem()
}
