package com.sarang.timelinedetail2

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.torang_core.data.model.ReviewImage
import java.util.ArrayList

class PictureAdapter() : RecyclerView.Adapter<PictureViewHolder>() {
    private var pictures = ArrayList<ReviewImage>()
    fun setPictures(pictures: List<ReviewImage>?) {
        if (pictures != null) this.pictures = ArrayList(pictures)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.mBinding.url = pictures[position].picture_url
    }

    override fun getItemCount(): Int {
        return pictures.size
    }
}