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
import com.sarang.feed_detail.data.usecase.CommentsFragmentUsecase
import com.sarang.feed_detail.data.usecase.FeedDetailHeaderLayoutUseCase
import com.sarang.feed_detail.databinding.FragmentCommentsBinding
import com.sarang.feed_detail.viewmodel.FeedDetailViewModel
import com.sarang.feed_detail.test.TestFeedDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.*

/**
 * 코멘트를 볼 수 있는 화면
 * [FeedDetailAdapter]
 * [TimeLineDetailHeaderHolder]
 * [FeedDetailViewModel]
 * [FragmentTimeLineDetailBinding]
 * [ItemCommentBinding]
 */
@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private val viewModel: TestFeedDetailViewModel by viewModels()
    private val adapter = FeedDetailAdapter()
    private val _layoutUsecase: MutableStateFlow<CommentsFragmentUsecase> = MutableStateFlow(
        CommentsFragmentUsecase()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCommentsBinding.inflate(layoutInflater, container, false)
        binding.init()
        binding.bindUseCase(_layoutUsecase)
        subScribeUiState(_layoutUsecase)
        loadComment()

        return binding.root
    }

    private fun FragmentCommentsBinding.init() {
        adapter = this@CommentsFragment.adapter
        lifecycleOwner = viewLifecycleOwner
        setSupportActionbar(toolbar2) // 툴바 초기화
        toolbar2.setNavigationOnClickListener { //툴바 뒤로가기 버튼 클릭
            requireActivity().onBackPressed()
        }
    }

    private fun subScribeUiState(layoutUseCase: MutableStateFlow<CommentsFragmentUsecase>) {
        lifecycleScope.launchWhenResumed {
            viewModel.uiState.collect {
                it?.let { uiState ->
                    layoutUseCase.update {
                        it.copy(
                            comment = uiState.comment,
                            errorMsg = uiState.errorMsg,
                            isEmpty = uiState.isEmptyFeed,
                            //comments = uiState.comments1
                            reviewAndIamge = uiState.feed,
                            headerLayoutUseCase = FeedDetailHeaderLayoutUseCase.parse(uiState.feed)
                        )
                    }
                }
            }
        }
    }

    private fun FragmentCommentsBinding.bindUseCase(
        useCase: StateFlow<CommentsFragmentUsecase>
    ) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            useCase.collect {
                this@bindUseCase.usecase = it
                it.errorMsg?.let { showErrorMsg(it) }
                it.isEmpty?.let { if (it) showEmptyPopup() }
                this@CommentsFragment.adapter.setHeader(it.headerLayoutUseCase)
                //it.reviewAndIamge?.let { adapter.setHeader(it) }
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