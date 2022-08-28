package com.sryang.feed_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sarang.feed_detail.adapter.FeedDetailAdapter
import com.sarang.feed_detail.data.usecase.CommentsFragmentUsecase
import com.sryang.feed_detail.databinding.ActivityCommentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommentFragmentActivity : AppCompatActivity() {

    private val adapter = FeedDetailAdapter()
    private val _useCase = MutableStateFlow(
        CommentsFragmentUsecase(
            comment = "",
            headerLayoutUseCase = DUMMY.getItemHeader(),
            comments = DUMMY.getItemCommentLayoutUseCases()
        )
    )
    val useCase: StateFlow<CommentsFragmentUsecase> = _useCase

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityCommentDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.icFragmentComments.adapter = adapter
        subScribeViewModel(binding)
        subScribeUI(binding)
    }

    private fun subScribeUI(binding: ActivityCommentDetailBinding) {
        lifecycleScope.launch {
            while (true) {
                delay(1000)
                _useCase.update {
                    it.copy(comment = "abc")
                }
            }
        }
    }

    private fun subScribeViewModel(binding: ActivityCommentDetailBinding) {
        lifecycleScope.launchWhenResumed {
            useCase.collect {
                binding.icFragmentComments.usecase = it
                adapter.setHeader(it.headerLayoutUseCase)
                it.comments?.let {
                    adapter.setComments(it)
                }
            }
        }
    }
}