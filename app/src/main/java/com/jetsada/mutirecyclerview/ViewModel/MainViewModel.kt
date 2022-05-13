package com.jetsada.mutirecyclerview.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jetsada.mutirecyclerview.adapter.item.MainRecyclerViewHolderItem
import com.jetsada.mutirecyclerview.data.network.Resource
import com.jetsada.mutirecyclerview.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _homeListItemsLiveData = MutableLiveData<Resource<List<MainRecyclerViewHolderItem>>>()
    val homeListItemsLiveData: LiveData<Resource<List<MainRecyclerViewHolderItem>>>
        get() = _homeListItemsLiveData

    init {
        getHomeListItems()
    }

    private fun getHomeListItems() {

    }
}