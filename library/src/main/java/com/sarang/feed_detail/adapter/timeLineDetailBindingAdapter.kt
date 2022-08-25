package com.sarang.feed_detail

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.data.ReviewAndImage
import com.sarang.feed_detail.adapter.CommentsRvAdt

@BindingAdapter("app:bindFeed")
fun select(listView: RecyclerView, feedData: ReviewAndImage?) {
    feedData?.let {
        (listView.adapter as CommentsRvAdt).setFeed(feedData)
    }
}