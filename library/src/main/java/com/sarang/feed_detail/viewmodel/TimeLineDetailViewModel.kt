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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeLineDetailViewModel @Inject constructor(
    private val timeLineDetailRepository: TimeLineDetailRepository,
    private val feedRepository: FeedRepository
) : ViewModel() {
    private val reviewId = MutableLiveData<Int>()
    private var _comments = MutableLiveData<ArrayList<Comment>>()
    var comments: LiveData<ArrayList<Comment>> = _comments

    var comments1: LiveData<List<CommentData>> = reviewId.switchMap {
        timeLineDetailRepository.getComments(it)
    }

    val comment = MutableLiveData<String>()
    val isLogin = timeLineDetailRepository.isLogin

    private val _errorMsg: MutableLiveData<Event<String>> = MutableLiveData()
    val errorMsg: LiveData<Event<String>> = _errorMsg

    val feed: LiveData<ReviewAndImage?> = reviewId.switchMap {
        timeLineDetailRepository.getFeed(it)
    }

    val isEmptyFeed: LiveData<Boolean> = feed.switchMap {
        Logger.d("isEmptyFeed :  $it")
        if (it == null) {
            MutableLiveData<Boolean>(true)
        } else {
            MutableLiveData<Boolean>(false)
        }
    }

    fun loadComments(reviewId: Int) {
        this.reviewId.postValue(reviewId)
    }

    fun addComment() {
        viewModelScope.launch {
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
        }
    }
}