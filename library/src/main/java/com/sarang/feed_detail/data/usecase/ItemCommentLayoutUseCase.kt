package com.sarang.feed_detail.data.usecase

import android.view.View

data class ItemCommentLayoutUseCase(
    val likeCount: Int,
    val profilePictureUrl: String,
    val userName: String,
    val comment: String,
    val createDate: String,
    val clickProfilePic: View.OnClickListener,
    val clickProfileName: View.OnClickListener,
    val clickLikeCount: View.OnClickListener,
    val clickReply: View.OnClickListener
)
