package com.jetsada.mutirecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetsada.mutirecyclerview.ViewModel.MainViewModel
import com.jetsada.mutirecyclerview.adapter.adapter.MainRecyclerViewAdapter
import com.jetsada.mutirecyclerview.adapter.item.MainRecyclerViewHolderItem
import com.jetsada.mutirecyclerview.data.network.Resource
import com.jetsada.mutirecyclerview.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val mainRecyclerViewAdapter = MainRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainRecyclerViewAdapter
        }

        mainRecyclerViewAdapter.itemClickListener = { view, item, position ->
            val message = when(item){
                is MainRecyclerViewHolderItem.Director -> "Director ${item.name} Clicked"
                is MainRecyclerViewHolderItem.Movie -> "Movie ${item.title} Clicked"
                is MainRecyclerViewHolderItem.Title -> "Title ${item.title} Clicked"
            }
            snackbar(message)
        }

        viewModel.homeListItemsLiveData.observe(this){ result ->
            when(result){
                is Resource.Failure -> {
                    binding.progressBar.hide()
                    //handle failure case here
                }
                Resource.Loading -> binding.progressBar.show()
                is Resource.Success -> {
                    binding.progressBar.hide()
                    mainRecyclerViewAdapter.items = result.value
                    Log.d("result", result.value.toString())
                }
            }
        }
    }
}