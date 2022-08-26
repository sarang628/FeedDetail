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
import com.example.torang_core.navigation.MenuBottomSheetNavigation
import com.example.torang_core.navigation.MyMenuBottomSheetNavigation
import com.example.torang_core.navigation.NotLoggedInMenuBottomSheetNavigation
import com.example.torang_core.navigation.ReportNavigation
import com.sarang.feed_detail.adapter.CommentsRvAdt
import com.sarang.feed_detail.databinding.FragmentCommentsBinding
import com.sarang.feed_detail.ui.usecase.CommentsLayoutUsecase
import com.sarang.feed_detail.viewmodel.TimeLineDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * 코멘트를 볼 수 있는 화면
 * [CommentsRvAdt]
 * [TimeLineDetailHeaderHolder]
 * [TimeLineDetailViewModel]
 * [FragmentTimeLineDetailBinding]
 * [ItemCommentBinding]
 */
@AndroidEntryPoint
class CommentsFragment : Fragment() {
    private val viewModel: TimeLineDetailViewModel by viewModels()

    @Inject
    lateinit var menuBottomSheetNavigation: MenuBottomSheetNavigation

    @Inject
    lateinit var myMenuBottomSheetNavigation: MyMenuBottomSheetNavigation

    @Inject
    lateinit var notLoggedInMenuBottomSheetNavigation: NotLoggedInMenuBottomSheetNavigation

    @Inject
    lateinit var reportNavigation: ReportNavigation

    private var layoutUsecase = MutableStateFlow(CommentsLayoutUsecase())

    private val adapter = CommentsRvAdt()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCommentsBinding
            .inflate(layoutInflater, container, false).apply {
                init()
                subScribeUI(layoutUsecase)
            }
        subScribeViewModel() // ViewModel 구독
        loadComment()
        test()

        return binding.root
    }

    private fun FragmentCommentsBinding.init() {
        rvTimeLineDetail.adapter = adapter
        lifecycleOwner = viewLifecycleOwner
        setSupportActionbar(toolbar2) // 툴바 초기화
        toolbar2.setNavigationOnClickListener { //툴바 뒤로가기 버튼 클릭
            requireActivity().onBackPressed()
        }
    }

    private fun FragmentCommentsBinding.subScribeUI(
        useCase: MutableStateFlow<CommentsLayoutUsecase>
    ) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            useCase.collect {
                this@subScribeUI.usecase = it
            }
        }
    }

    /**
     * UI 구독하기
     */
    private fun subScribeViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            layoutUsecase.collect {
                it.errorMsg?.let { showErrorMsg(it) }
                it.isEmpty?.let { if (it) showEmptyPopup() }
                it.reviewAndIamge?.let { adapter.setFeed(it) }
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

    private fun test() {
        //test
        viewLifecycleOwner.lifecycleScope.launch {
            layoutUsecase.update {
                it.copy(errorMsg = "abc")
            }
        }
    }
}

fun Fragment.setSupportActionbar(toolbar: Toolbar) {
    (requireActivity() as AppCompatActivity).apply {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}