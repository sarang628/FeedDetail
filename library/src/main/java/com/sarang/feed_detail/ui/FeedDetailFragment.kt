package com.sarang.feed_detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sarang.feed_detail.adapter.FeedDetailAdapter
import com.sarang.feed_detail.data.usecase.CommentsFragmentUseCase
import com.sarang.feed_detail.data.usecase.FeedDetailHeaderLayoutUseCase
import com.sarang.feed_detail.databinding.FragmentCommentsBinding
import com.sarang.feed_detail.test.TestFeedDetailViewModel
import com.sarang.feed_detail.viewmodel.FeedDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.*

/**
 * layout - [FragmentCommentsBinding]
 * [TimeLineDetailHeaderHolder]
 * [FeedDetailViewModel]
 * [FragmentTimeLineDetailBinding]
 * [ItemCommentBinding]
 */
@AndroidEntryPoint
class FeedDetailFragment : Fragment() {

    private val viewModel: TestFeedDetailViewModel by viewModels()
    private val adapter = FeedDetailAdapter()
    private val _layoutUsecase: MutableStateFlow<CommentsFragmentUseCase> = MutableStateFlow(
        CommentsFragmentUseCase()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCommentsBinding.inflate(layoutInflater, container, false)
        binding.init()
        binding.subscribeUseCase(_layoutUsecase)
        subScribeUiState(_layoutUsecase)
        loadComment()

        return binding.root
    }

    private fun FragmentCommentsBinding.init() {
        adapter = this@FeedDetailFragment.adapter
        lifecycleOwner = viewLifecycleOwner
        setSupportActionbar(toolbar) // 툴바 초기화
        toolbar.setNavigationOnClickListener { //툴바 뒤로가기 버튼 클릭
            requireActivity().onBackPressed()
        }
    }

    private fun subScribeUiState(layoutUseCase: MutableStateFlow<CommentsFragmentUseCase>) {
        lifecycleScope.launchWhenResumed {
            viewModel.uiState.collect {
                it?.let { uiState ->
                    layoutUseCase.update {
                        it.copy(
                            headerLayoutUseCase = FeedDetailHeaderLayoutUseCase.parse(uiState.feed)
                        )
                    }

                    if (uiState.errorMsg != null) {
                        showErrorMsg(uiState.errorMsg)
                    }

                    if (uiState.isEmptyFeed != null) {
                        if (uiState.isEmptyFeed) showEmptyPopup()
                    }
                }
            }
        }
    }

    private fun FragmentCommentsBinding.subscribeUseCase(
        useCase: StateFlow<CommentsFragmentUseCase>
    ) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            useCase.collect {
                this@subscribeUseCase.useCase = it
            }
        }
    }

    private fun showErrorMsg(msg: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(msg)
            .show()
    }

    private fun showEmptyPopup() {
        AlertDialog.Builder(requireContext())
            .setMessage("글이 없습니다.")
            .setPositiveButton("확인") { _, _ ->
                requireActivity().finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun loadComment() {
        // 코멘트 불러오기
        if (requireActivity().intent.hasExtra("reviewId")) {
            viewModel.loadComments(requireActivity().intent.getIntExtra("reviewId", 0))
        }
    }
}

fun Fragment.setSupportActionbar(toolbar: Toolbar) {
    (requireActivity() as AppCompatActivity).apply {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}