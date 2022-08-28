package com.sarang.feed_detail.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.torang_core.util.Logger
import com.sarang.feed_detail.data.usecase.ItemFeedDetailHeaderLayoutUseCase
import com.sarang.feed_detail.databinding.ItemFeedDetailHeaderBinding

class TimeLineDetailHeaderHolder(var mBinding: ItemFeedDetailHeaderBinding) :
    RecyclerView.ViewHolder(mBinding.root) {
    fun setFeed(useCase: ItemFeedDetailHeaderLayoutUseCase?) {
        Logger.d("useCase $useCase")
        mBinding.useCase = useCase
    }

    companion object {
        fun create(parent: ViewGroup): TimeLineDetailHeaderHolder {
            return TimeLineDetailHeaderHolder(
                ItemFeedDetailHeaderBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }
}