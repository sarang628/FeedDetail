package com.sarang.feed_detail.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarang.feed_detail.databinding.ItemWriteCommentBinding
import com.sarang.feed_detail.viewmodel.TimeLineDetailViewModel

class AddCommentHolder(val binding: ItemWriteCommentBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): AddCommentHolder {
            return AddCommentHolder(
                ItemWriteCommentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}