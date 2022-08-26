package com.sarang.feed_detail.ui.usecase

import com.example.torang_core.data.model.Comment

data class CommentsLayoutUsecase(
    val comment: String? = null,
    val errorMsg : String? = null,
    val comments : ArrayList<Comment>? = null
)
