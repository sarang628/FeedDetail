package com.sryang.feed_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sryang.feed_detail.databinding.ActivityItemFeedDetailHeaderBinding

class ItemFeedDetailHeaderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityItemFeedDetailHeaderBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}