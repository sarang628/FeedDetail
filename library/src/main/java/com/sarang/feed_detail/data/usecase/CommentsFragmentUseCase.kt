package com.sarang.feed_detail.data.usecase

import com.example.torang_core.data.data.ReviewAndImage

data class CommentsFragmentUseCase(
    val headerLayoutUseCase: FeedDetailHeaderLayoutUseCase? = null,
    val comments: ArrayList<ItemCommentLayoutUseCase>? = null
    //TODO::하단 댓글영역 유즈케이스 작성하기
)
