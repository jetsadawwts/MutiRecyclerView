package com.jetsada.mutirecyclerview.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetsada.mutirecyclerview.adapter.item.MainRecyclerViewHolderItem
import com.jetsada.mutirecyclerview.data.network.Resource
import com.jetsada.mutirecyclerview.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _homeListItemsLiveData = MutableLiveData<Resource<List<MainRecyclerViewHolderItem>>>()
    val homeListItemsLiveData: LiveData<Resource<List<MainRecyclerViewHolderItem>>>
        get() = _homeListItemsLiveData

    init {
        getHomeListItems()
    }

    private fun getHomeListItems() = viewModelScope.launch {
        _homeListItemsLiveData.postValue(Resource.Loading)
        val moviesDeferred = async { repository.getMovies() }
        val directorsDeferred = async { repository.getDirectors() }

        val movies = moviesDeferred.await()
        val director = directorsDeferred.await()

        val homeItemsList = mutableListOf<MainRecyclerViewHolderItem>()

        when {
            movies is Resource.Success && director is Resource.Success -> {
                homeItemsList.add(MainRecyclerViewHolderItem.Title(1, "Recommended Movies"))
                homeItemsList.addAll(movies.value)
                homeItemsList.add(MainRecyclerViewHolderItem.Title(2, "Top Director"))
                homeItemsList.addAll(director.value)
                _homeListItemsLiveData.postValue(Resource.Success(homeItemsList))
            }

            else -> {
                Resource.Failure(false, null, null)
            }
        }
    }
}