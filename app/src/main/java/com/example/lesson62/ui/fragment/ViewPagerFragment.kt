package com.example.lesson62.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson62.R
import com.example.lesson62.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {
    private val binding by viewBinding(FragmentViewPagerBinding::bind)

    //    private val viewPagerAdapter = ViewPagerAdapter(this)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initialize()
//        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "Manga ${(position + 1)}"
            tab.text = "Anime ${(position + 1)}"
        }.attach()
    }

//    private fun initialize() {
//        binding.viewPager.adapter = viewPagerAdapter
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) {tab, position ->
//            tab.text = if (position == 0) "anime" else "manga"
//        }.attach()
//    }
}