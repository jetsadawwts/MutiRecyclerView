package com.jetsada.mutirecyclerview.data.network

import com.jetsada.mutirecyclerview.adapter.item.MainRecyclerViewHolderItem
import retrofit2.http.GET

interface Api {

        @GET("movies")
        suspend fun getMovies(): List<MainRecyclerViewHolderItem.Movie>

        @GET("directors")
        suspend fun getDirectors(): List<MainRecyclerViewHolderItem.Director>

}