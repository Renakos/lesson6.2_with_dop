package com.example.lesson62.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson62.R
import com.example.lesson62.databinding.ActivityMainBinding
import com.example.lesson62.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Manga"
                }

                1 -> {
                    tab.text = "Anime"
                }
            }
        }.attach()
    }
}