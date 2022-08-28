package com.sryang.feed_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sarang.feed_detail.adapter.FeedDetailAdapter
import com.sarang.feed_detail.data.usecase.CommentsFragmentUseCase
import com.sarang.feed_detail.databinding.FragmentCommentsBinding
import com.sryang.feed_detail.databinding.ActivityCommentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
/**
 * layout - [FragmentCommentsBinding]
 * fragment - [FeedDetailFragment]
 */
class FeedDetailFragmentActivity : AppCompatActivity() {

    private val adapter = FeedDetailAdapter()
    private val _useCase: MutableStateFlow<CommentsFragmentUseCase> =
        MutableStateFlow(DUMMY.getCommentsFragmentUseCase())
    val useCase: StateFlow<CommentsFragmentUseCase> = _useCase

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityCommentDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.icFragmentComments.adapter = adapter
        binding.subscribeLayoutUseCase(useCase)

        uiUpdateTest()
    }


    private fun ActivityCommentDetailBinding.subscribeLayoutUseCase(
        useCase: StateFlow<CommentsFragmentUseCase>
    ) {
        lifecycleScope.launchWhenResumed {
            useCase.collect {
                icFragmentComments.useCase = it
            }
        }
    }

    private fun uiUpdateTest() {
        lifecycleScope.launch {
            while (true) {
                delay(1000)
                val random = Random.nextInt(10)
                val useCase = if (random % 2 == 1) DUMMY.getItemHeader() else DUMMY.getItemHeader1()
                _useCase.update {
                    it.copy(
                        headerLayoutUseCase = useCase
                    )
                }
            }
        }
    }
}