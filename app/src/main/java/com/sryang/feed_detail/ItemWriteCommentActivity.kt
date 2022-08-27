package com.sryang.feed_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.sarang.feed_detail.ui.usecase.ItemWriteCommentUsecase
import com.sryang.feed_detail.databinding.ActivityItemWriteCommentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemWriteCommentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityItemWriteCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val _useCase = MutableStateFlow(
            ItemWriteCommentUsecase(
                profilePictureUrl = "data:image/jpeg;base64,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",
                comment = MutableStateFlow<String>("abc"),
                clickPost = {
                    Toast.makeText(this, "clickPost", Toast.LENGTH_SHORT).show()
                }
            )
        )

        val useCase: StateFlow<ItemWriteCommentUsecase> = _useCase

        lifecycleScope.launch {
            while (true) {
                delay(1000)
                /*_useCase.update {
                    it.copy(comment = it.comment + "!")
                }*/
            }
        }

        lifecycleScope.launchWhenResumed {
            useCase.collect {
                binding.icItemWriteComment.useCase = it
            }
        }
    }
}