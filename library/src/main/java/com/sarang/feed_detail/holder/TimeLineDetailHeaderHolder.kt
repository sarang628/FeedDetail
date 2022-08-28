package com.sarang.feed_detail.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.util.Logger
import com.sarang.feed_detail.databinding.ItemTimeLineDetailHeaderBinding
import com.sarang.feed_detail.data.usecase.FeedDetailHeaderLayoutUseCase

class TimeLineDetailHeaderHolder(var mBinding: ItemTimeLineDetailHeaderBinding) :
    RecyclerView.ViewHolder(mBinding.root) {
    fun setFeed(useCase: FeedDetailHeaderLayoutUseCase?) {
        Logger.d("useCase $useCase")
        mBinding.useCase = useCase
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