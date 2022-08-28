package com.sryang.feed_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.sarang.feed_detail.data.usecase.ItemFeedDetailHeaderLayoutUseCase
import com.sryang.feed_detail.databinding.ActivityItemFeedDetailHeaderBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemFeedDetailHeaderActivity : AppCompatActivity() {

    val _useCase = MutableStateFlow(
        DUMMY.getItemHeader()
    )

    val useCase: StateFlow<ItemFeedDetailHeaderLayoutUseCase> = _useCase

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityItemFeedDetailHeaderBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        subScribeViewModel()
        subScribeUI(binding)
    }

    private fun subScribeViewModel() {
        lifecycleScope.launch {
            while (true) {
                delay(1000)
                _useCase.update {
                    it.copy(
                        contents = it.contents + "!"
                    )
                }
            }
        }
    }

    fun subScribeUI(binding : ActivityItemFeedDetailHeaderBinding) {
        lifecycleScope.launchWhenResumed {
            useCase.collect {
                binding.icItemTimeLineDetailHeader.useCase = it
            }
        }
    }
}