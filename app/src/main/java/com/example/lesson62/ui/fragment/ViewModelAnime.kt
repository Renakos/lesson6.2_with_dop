package com.example.lesson62.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson62.data.remoteanime.RetrofitClientAnime
import com.example.lesson62.data.repoanime.RepositoryAnime

class ViewModelAnime() : ViewModel() {

    private val repository = RepositoryAnime(RetrofitClientAnime.apiService)

    private val _isLastPage = MutableLiveData<Boolean>()
    val isLastPage: LiveData<Boolean> = _isLastPage

    init {
        loadData()
    }

    fun loadData() = repository.getAnime()
}
