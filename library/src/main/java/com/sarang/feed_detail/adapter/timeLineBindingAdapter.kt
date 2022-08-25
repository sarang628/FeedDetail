package com.sarang.feed_detail

import android.graphics.Rect
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.data.model.ReviewImage
import com.example.torang_core.util.Logger
import com.sarang.feed_detail.adapter.PictureAdapter

@BindingAdapter("select")
fun select(view: View, select: Boolean) {
    view.isSelected = select
}

@BindingAdapter("app:bindPicture")
fun bindPicture(recyclerView: RecyclerView, images: List<ReviewImage>?) {
    images?.let {
        Logger.d("bindPicture ${images.size}")
        (recyclerView.adapter as PictureAdapter).setPictures(it)
    }
}


@BindingAdapter("app:itemMarginTop")
fun itemMarginTop(recyclerView: RecyclerView, marginTop: Int?) {
    marginTop?.let {
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top = marginTop

                if (parent.getChildAdapterPosition(view) == 1) {
                    outRect.top = 30
                }
            }
        })
    }
}

@BindingAdapter("app:setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}