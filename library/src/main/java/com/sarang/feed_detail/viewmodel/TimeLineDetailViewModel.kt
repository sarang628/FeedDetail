package com.sarang.feed_detail.viewmodel

import androidx.lifecycle.*
import com.example.torang_core.data.data.ReviewAndImage
import com.example.torang_core.data.model.Comment
import com.example.torang_core.data.model.CommentData
import com.example.torang_core.repository.FeedRepository
import com.example.torang_core.repository.TimeLineDetailRepository
import com.example.torang_core.util.Event
import com.example.torang_core.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeLineDetailViewModel @Inject constructor(
    private val timeLineDetailRepository: TimeLineDetailRepository,
    private val feedRepository: FeedRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FeedDetailUiState?>(null)
    val uiState: StateFlow<FeedDetailUiState?> = _uiState

    fun loadComments(reviewId: Int) {
    }

    fun addComment() {
        /*viewModelScope.launch {
            try {
                if (reviewId.value == null) {
                    _errorMsg.postValue(Event("리뷰id 가 없습니다."))
                } else if (comment.value == null || comment.value.equals("")) {
                    _errorMsg.postValue(Event("comment 가 없습니다."))
                } else {
                    val result =
                        timeLineDetailRepository.addComment(reviewId.value!!, comment.value!!)
                    val list = _comments.value
                    list?.let {
                        it.add(result)
                        _comments.postValue(it)
                    }
                    comment.postValue("")
                }

            } catch (e: Exception) {
                _errorMsg.postValue(Event(e.toString()))
            }
        }*/
    }
}