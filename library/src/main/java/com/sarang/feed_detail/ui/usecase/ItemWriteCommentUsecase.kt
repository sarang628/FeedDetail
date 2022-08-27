package com.sarang.feed_detail.ui.usecase

import android.view.View
import kotlinx.coroutines.flow.MutableStateFlow

data class ItemWriteCommentUsecase(
    val profilePictureUrl : String,
    var comment : MutableStateFlow<String>,
    val clickPost : View.OnClickListener
)
