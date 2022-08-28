package com.sryang.feed_detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sryang.feed_detail.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).apply {
            btnCommentFragment.setOnClickListener {
                goCommentFragment()
            }
            btnItemComment.setOnClickListener {
                goItemComment()
            }
            btnItemWriteComment.setOnClickListener {
                goItemWriteComment()
            }
            btnItemFeedDetailHeader.setOnClickListener {
                goFeedDetailHeader()
            }
        }
        setContentView(binding.root)
    }

    private fun goCommentFragment() {
        startActivity(Intent(this, FeedDetailFragmentActivity::class.java))
    }

    private fun goItemComment() {
        startActivity(Intent(this, ItemCommentActivity::class.java))
    }

    private fun goItemWriteComment() {
        startActivity(Intent(this, ItemWriteCommentActivity::class.java))
    }

    private fun goFeedDetailHeader() {
        startActivity(Intent(this, ItemFeedDetailHeaderActivity::class.java))
    }
}