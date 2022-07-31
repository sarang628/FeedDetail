package com.sarang.timelinedetail2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.torang_core.navigation.ProfileNavigation
import com.example.torang_core.navigation.TimeLineDetailNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@AndroidEntryPoint
class TimeLineDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line_detail)
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

@Module
@InstallIn(ActivityComponent::class)
abstract class TimeLineDetailNavigationInject {
    @Binds
    abstract fun bindTimeLineDetailNavigation(
        timeLineNavigationImpl: TimeLineDetailNavigationImpl
    ): TimeLineDetailNavigation
}