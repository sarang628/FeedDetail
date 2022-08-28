package com.sarang.feed_detail.data.usecase

import com.example.torang_core.data.data.ReviewAndImage

data class CommentsFragmentUsecase(
    val comment: String? = null,
    val errorMsg: String? = null,
    val isEmpty: Boolean? = null,
    val comments: ArrayList<ItemCommentLayoutUseCase>? = null,
    val reviewAndIamge: ReviewAndImage? = null,
    val headerLayoutUseCase: FeedDetailHeaderLayoutUseCase? = null
)
