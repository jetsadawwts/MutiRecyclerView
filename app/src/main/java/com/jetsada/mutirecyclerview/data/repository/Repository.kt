package com.jetsada.mutirecyclerview.data.repository

import com.jetsada.mutirecyclerview.data.network.Api
import com.jetsada.mutirecyclerview.data.network.SafeApiCall
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api) : SafeApiCall {

    suspend fun getMovies() = safeApiCall { api.getMovies() }
    suspend fun getDirectors() = safeApiCall { api.getDirectors() }

}