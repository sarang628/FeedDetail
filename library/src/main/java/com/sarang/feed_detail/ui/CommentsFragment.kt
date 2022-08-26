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
import com.example.torang_core.data.model.Comment
import com.example.torang_core.navigation.MenuBottomSheetNavigation
import com.example.torang_core.navigation.MyMenuBottomSheetNavigation
import com.example.torang_core.navigation.NotLoggedInMenuBottomSheetNavigation
import com.example.torang_core.navigation.ReportNavigation
import com.example.torang_core.util.Logger
import com.sarang.feed_detail.adapter.CommentsRvAdt
import com.sarang.feed_detail.databinding.FragmentCommentsBinding
import com.sarang.feed_detail.ui.usecase.CommentsLayoutUsecase
import com.sarang.feed_detail.viewmodel.TimeLineDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
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

    var layoutUsecase: MutableStateFlow<CommentsLayoutUsecase>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 바인딩 초기화
        val binding = FragmentCommentsBinding.inflate(layoutInflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            subScribeUI(initLayoutUseCase())
        }

        // 툴바 초기화
        setSupportActionbar(binding.toolbar2)

        //툴바 뒤로가기 버튼 클릭
        binding.toolbar2.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        // 아답터 초기화
        val adapter = CommentsRvAdt(viewModel)
        binding.rvTimeLineDetail.adapter = adapter

        // UI 구독
        subScribeUI(adapter)

        // 코멘트 불러오기
        if (requireActivity().intent.hasExtra("reviewId")) {
            viewModel.loadComments(requireActivity().intent.getIntExtra("reviewId", 0))
        }

        return binding.root
    }

    private fun initLayoutUseCase(): MutableStateFlow<CommentsLayoutUsecase> {
        layoutUsecase = MutableStateFlow<CommentsLayoutUsecase>(CommentsLayoutUsecase())
        return layoutUsecase!!
    }

    fun FragmentCommentsBinding.subScribeUI(feedUsecase: MutableStateFlow<CommentsLayoutUsecase>) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            feedUsecase.collect {
                this@subScribeUI.usecase = it
            }
        }
    }


    /**
     * UI 구독하기
     */
    private fun subScribeUI(adapter: CommentsRvAdt) {
        // 코멘트 리스트 관찰
        viewModel.comments1.observe(viewLifecycleOwner) {
            Logger.d("observe! ${it.size}")
            adapter.setComments(Comment.parse(it))
        }

        viewModel.feed.observe(viewLifecycleOwner) {
            Logger.d("observe feed ${it}")
            adapter.setFeed(it)
        }

        viewModel.isEmptyFeed.observe(viewLifecycleOwner) {
            if (it) {
                /*AlertDialog.Builder(requireContext())
                    .setMessage("글이 없습니다.")
                    .setPositiveButton("확인") { _, _ ->
                        requireActivity().finish()
                    }
                    .setCancelable(false)
                    .show()*/
            }
        }

        /*viewLifecycleOwner.lifecycle.addObserver(ReportProcessor(
            viewModel,
            menuBottomSheetNavigation,
            myMenuBottomSheetNavigation,
            notLoggedInMenuBottomSheetNavigation,
            reportNavigation,
            requireContext()
        ))*/

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            layoutUsecase?.collect {
                it.errorMsg?.let {
                    AlertDialog.Builder(requireContext())
                        .setMessage(it)
                        .show()
                }
            }
        }
    }
}

fun Fragment.setSupportActionbar(toolbar : Toolbar){
    (requireActivity() as AppCompatActivity).apply {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}