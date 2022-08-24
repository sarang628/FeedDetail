package com.sarang.timelinedetail2

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.data.ReviewAndImage

@BindingAdapter("app:bindFeed")
fun select(listView: RecyclerView, feedData: ReviewAndImage?) {
    feedData?.let {
        (listView.adapter as CommentsRvAdt).setFeed(feedData)
    }
}