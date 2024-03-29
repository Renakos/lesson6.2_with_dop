package com.example.lesson62.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson62.R
import com.example.lesson62.data.model.Data
import com.example.lesson62.data.remoteanime.paging.AnimePagingSource
import com.example.lesson62.databinding.FragmentAnimeBinding
import com.example.lesson62.ui.adapter.AdapterAnime
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AnimeFragment : Fragment(R.layout.fragment_anime) {

    private val binding by viewBinding(FragmentAnimeBinding::bind)
    private val adapter = AdapterAnime()
    private val viewModel: ViewModelAnime by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        collectWithLifecycle()
        observeViewModel()
        setupRefresh()
        setupSubscribe()

        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000)
            if (binding.progressBar.isVisible) {
                binding.progressBar.isVisible = false
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect {
                    binding.appendProgress.isVisible = it.source.prepend is LoadState.Loading
                    binding.prependProgress.isVisible = it.source.append is LoadState.Loading

                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadData().collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }

    private fun initializeRecyclerView() = with(binding) {
        recyclerView.adapter = adapter
    }

    private fun collectWithLifecycle() {
        val pagingSourceFactory = { AnimePagingSource() }
        val pager = Pager(
            PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = pagingSourceFactory
        )
        val flow: Flow<PagingData<Data>> = pager.flow.cachedIn(viewLifecycleOwner.lifecycleScope)
        viewLifecycleOwner.lifecycleScope.launch {
            flow.collectLatest { pagingData ->
                Log.e("data", pagingData.toString())

            }
        }
    }

    private fun observeViewModel() {

        viewModel.isLastPage.observe(viewLifecycleOwner) { isLastPage: Boolean ->
            if (isLastPage) {
                Snackbar.make(requireView(), "Это конечная страница.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            setupSubscribe()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }


    private fun setupSubscribe() {
        lifecycleScope.launch {
            viewModel.loadData().collect {
                adapter.submitData(it)
            }
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }


}