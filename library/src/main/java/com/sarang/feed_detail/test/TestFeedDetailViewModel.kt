package com.sarang.feed_detail.test

import androidx.lifecycle.*
import com.example.torang_core.data.data.ReviewAndImage
import com.example.torang_core.data.model.FeedData
import com.example.torang_core.data.model.UserData
import com.example.torang_core.repository.FeedRepository
import com.example.torang_core.repository.TimeLineDetailRepository
import com.sarang.feed_detail.data.uistate.FeedDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TestFeedDetailViewModel @Inject constructor(
    private val timeLineDetailRepository: TimeLineDetailRepository,
    private val feedRepository: FeedRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FeedDetailUiState?>(
        FeedDetailUiState(
            reviewId = 0,
            feed = ReviewAndImage(
                user = UserData(
                    userId = 0,
                    userName = "sryang",
                    profile_pic_url = "data:image/jpeg;base64,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"
                ),
                review = FeedData(
                    review_id = 0,
                    userId = 0,
                    contents = "이런 야심한 밤\n" +
                            "그런 걱정을 왜 해\n" +
                            "물어볼 뿐이야 오해는 왜 해 yeah\n" +
                            "TV 드라마로 끝내기엔 yeah\n" +
                            "저 달이 너무 밝아서\n" +
                            "So I just wanna talk about you\n" +
                            "And me, yeah\n" +
                            "밤하늘 위 잠긴 목소리로\n" +
                            "차가워 진 폰을 밤새 달궈 oh no\n" +
                            "별 말을 안 해도 훌쩍 지나있는\n" +
                            "시간 속에 올라타있어\n" +
                            "Am I crazy?\n" +
                            "Yeah, maybe\n" +
                            "뭘까 어딜까\n" +
                            "막 이쁘진 않은데\n" +
                            "자꾸만 눈이 가\n" +
                            "날 못 살게 구는 게\n" +
                            "나도 어지러워 도와줘\n" +
                            "밖은 어두워져\n" +
                            "Don't you want my love?\n" +
                            "시계 초 소리만 더 커져\n" +
                            "거기 멈춰 넘어, 넘어와\n" +
                            "그쯤에서 하고 넘어, 넘어와\n" +
                            "Oh baby 넘어, 넘어와 hey\n" +
                            "이 밤을 건너 넘어와\n" +
                            "Oh, baby\n" +
                            "달처럼 마음도 기우네\n" +
                            "할 말은 떨어지고\n" +
                            "그 사이를 비집고\n" +
                            "정적이 틈을 채우네 (yeah)\n" +
                            "밤 새 오늘을 기다렸는데\n" +
                            "네 앞에만 서면 난 왜 이리 작아지는지\n" +
                            "Oh 왜\n" +
                            "길거리 위를 끝없이 서성이며\n" +
                            "차가워 진 손을 밤새 달궈 oh no\n" +
                            "어제처럼 그제처럼\n" +
                            "집에 돌아가기엔\n" +
                            "I'm so crazy\n" +
                            "Yeah, maybe\n" +
                            "뭘까 어딜까\n" +
                            "막 이쁘진 않은데\n" +
                            "자꾸만 눈이 가\n" +
                            "날 못 살게 구는 게\n" +
                            "나도 어지러워 도와줘\n" +
                            "밖은 어두워져\n" +
                            "Don't you want my love?\n" +
                            "시계 초 소리만 더 커져 baby, oh yeah\n" +
                            "너와 나 사이\n" +
                            "결론은 없는데\n" +
                            "널 보면 이렇게\n" +
                            "전부 알 것 같은 게\n" +
                            "나도 힘들어 날 도와줘\n" +
                            "Oh, let me into your heart\n" +
                            "시계 초 소리만 더 커져\n" +
                            "바로 그 순간 넘어, 넘어와\n" +
                            "네가 내 쪽으로 넘어, 넘어와\n" +
                            "What should I do baby\n" +
                            "넘어, 넘어와\n" +
                            "내 맘 속으로\n" +
                            "넘어, 넘어와",
                    rating = 4.5f
                )
            ),
            comments = ArrayList(),
            comments1 = ArrayList(),
            comment = "",
            isLogin = false,
            isEmptyFeed = false
        )
    )
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