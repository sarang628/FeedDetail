package com.sarang.feed_detail.data.usecase

data class CommentsFragmentUseCase(
    val headerLayoutUseCase: ItemFeedDetailHeaderLayoutUseCase? = null,
    val comments: ArrayList<ItemFeedDetailCommentLayoutUseCase>? = null,
    val commentWrite: ItemWriteCommentUsecase? = null
)
