package com.sryang.feed_detail

import androidx.test.platform.app.InstrumentationRegistry
import com.example.torang_core.data.dao.LoggedInUserDao
import com.example.torang_core.data.dao.UserDao
import com.example.torang_core.data.model.LoggedInUserData
import com.example.torangrepository.FeedRepositoryImpl
import com.example.torangrepository.LoginRepositoryImpl
import com.example.torangrepository.services.FeedServices
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
class ExampleInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.sryang.feed_detail", appContext.packageName)
    }

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Inject
    lateinit var feedService: FeedServices

    @Inject
    lateinit var userDao: UserDao

    @Inject
    lateinit var user: LoggedInUserDao

    @Test
    fun test() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val feedRepository = FeedRepositoryImpl(appContext, feedService, userDao, user)

        runBlocking {
            feedRepository.loadFeed()
        }
    }

    @Test
    fun login() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val loginrepository = LoginRepositoryImpl(appContext)

        runBlocking {
            val loggedInUserData = LoggedInUserData(userId = 4, userName = "베이글")
            loginrepository.setLoggedInUser(loggedInUserData)
        }
    }

}