package com.example.shercofaqapp.view

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.filters.SmallTest
import com.example.shercofaqapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class BikeFragmentTest {

//    @get:Rule(order = 0)
//    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var fragmentRule = InstantTaskExecutorRule()

//    @Before
//    fun setup() {
//        hiltRule.inject()
//    }

    @Test
    fun testLaunchFragmentInHiltContainer() {
        launchFragmentInHiltContainer<BikeFragment>{

        }
    }

}