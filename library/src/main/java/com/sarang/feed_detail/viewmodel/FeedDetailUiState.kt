package com.sarang.feed_detail.viewmodel

import com.example.torang_core.data.data.ReviewAndImage
import com.example.torang_core.data.model.Comment
import com.example.torang_core.data.model.CommentData

data class FeedDetailUiState(
    val reviewId: Int,
    val comments: ArrayList<Comment>,
    val comments1: List<CommentData>,
    val comment: String,
    val isLogin: Boolean,
    val errorMsg: String,
    val feed: ReviewAndImage,
    val isEmptyFeed: Boolean
)
