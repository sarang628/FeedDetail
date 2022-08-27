package com.sarang.feed_detail.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.torang_core.navigation.TimeLineDetailNavigation
import com.sarang.feed_detail.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TimeLineDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_detail)
    }
}

object TimeLineDetailActivityLauncher {
    fun go(context: Context, reviewId: Int) {
        context.startActivity(Intent(context, TimeLineDetailActivity::class.java).apply {
            putExtra("reviewId", reviewId)
        })
    }
}

class TimeLineDetailNavigationImpl @Inject constructor() : TimeLineDetailNavigation {
    override fun go(context: Context, userId: Int) {
        TimeLineDetailActivityLauncher.go(context, userId)
    }
}