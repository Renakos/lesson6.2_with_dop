package com.example.lesson62.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lesson62.R
import com.example.lesson62.data.model.Data

class AnimeAdapter : PagingDataAdapter<Data, AnimeAdapter.AnimeViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val animeItem = getItem(position)
        if (animeItem != null) {
            holder.bind(animeItem)
        }
    }

    inner class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.image)
        fun bind(data: Data) {
            Log.e("data", data.attributes.titles?.en ?: "")
            titleTextView.text = data.attributes.titles?.en ?: ""
            Glide.with(imageView.context)
                .load("https://media.kitsu.io/manga/poster_images/${data.id}/original.jpg")
                .into(imageView)
//            descriptionTextView.text = data.attributes.synopsis
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }
}
