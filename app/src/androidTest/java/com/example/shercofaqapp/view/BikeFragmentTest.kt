package com.example.shercofaqapp.view

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.SmallTest
import com.example.shercofaqapp.launchFragmentInHiltContainer
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@SmallTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class BikeFragmentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var fragmentRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_bike")
    lateinit var bike: Bike

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testLaunchFragmentInHiltContainer() {
        launchFragmentInHiltContainer<BikeFragment>{
            onView(withId(R.id.bikeNameTextView))
            onView(withId(R.id.bikeNameEditText))
            onView(withId(R.id.modelYearSpinner))
            onView(withId(R.id.typeSpinner))
            onView(withId(R.id.engineTypeSpinner))
            onView(withId(R.id.editionSpinner))
            onView(withId(R.id.engineVolumeSpinner))
        }
    }

}