package com.sarang.timelinedetail2

import com.example.torang_core.data.data.ReviewAndImage
import android.view.ViewGroup
import com.sarang.timelinedetail2.TimeLineDetailHeaderHolder
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.util.Logger
import com.sarang.timelinedetail2.databinding.ItemTimeLineDetailHeaderBinding

class TimeLineDetailHeaderHolder(var mBinding: ItemTimeLineDetailHeaderBinding) :
    RecyclerView.ViewHolder(
        mBinding.root
    ) {
    private var feed: ReviewAndImage? = null
    fun setFeed(feed: ReviewAndImage?) {
        Logger.d("setFeed $feed")
        this.feed = feed
        mBinding.feed = feed
    }

    companion object {
        fun create(parent: ViewGroup): TimeLineDetailHeaderHolder {
            return TimeLineDetailHeaderHolder(
                ItemTimeLineDetailHeaderBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }
}