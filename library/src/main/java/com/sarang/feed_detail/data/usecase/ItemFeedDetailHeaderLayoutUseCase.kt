package com.sarang.feed_detail.data.usecase

import com.example.torang_core.data.data.ReviewAndImage

data class ItemFeedDetailHeaderLayoutUseCase(
    val profilePictureUrl: String,
    val userName: String,
    val rating: Float,
    val createDate: String,
    val contents: String,
    val reviewImages: ArrayList<String>
) {
    companion object {
        fun parse(feed: ReviewAndImage): ItemFeedDetailHeaderLayoutUseCase {
            return ItemFeedDetailHeaderLayoutUseCase(
                profilePictureUrl = feed.user?.profile_pic_url ?: "",
                userName = feed.user?.userName ?: "",
                rating = feed.review?.rating ?: 5f,
                createDate = feed.review?.create_date ?: "",
                contents = feed.review?.contents ?: "",
                reviewImages = ArrayList()
            )
        }
    }
}