package com.sarang.feed_detail.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.feed_detail.databinding.ItemTimelineDetailPictureBinding

class PictureViewHolder(val binding: ItemTimelineDetailPictureBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): PictureViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemTimelineDetailPictureBinding
                .inflate(layoutInflater, parent, false)
            return PictureViewHolder(binding)
        }
    }
}