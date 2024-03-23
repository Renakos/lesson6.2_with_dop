package com.example.lesson62.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lesson62.ui.fragment.AnimeFragment
import com.example.lesson62.ui.fragment.RecyclerViewFragment


class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) RecyclerViewFragment() else AnimeFragment()
    }
}
