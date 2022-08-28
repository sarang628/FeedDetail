package com.sarang.feed_detail.data.usecase

import com.example.torang_core.data.data.ReviewAndImage

data class CommentsFragmentUseCase(
    val headerLayoutUseCase: FeedDetailHeaderLayoutUseCase? = null,
    val comments: ArrayList<ItemCommentLayoutUseCase>? = null
)
