package com.viona.moviecatalogue.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.network.NetworkConfig
import com.viona.moviecatalogue.data.source.remote.RemoteDataSource
import com.viona.moviecatalogue.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Test


class MainActivityTest {

    private val networkConfig = NetworkConfig()
    private val dataSource = RemoteDataSource(networkConfig)

    private val dataMovies = dataSource.getMovies()
    private val dataTVShows = dataSource.getTVShows()

    private lateinit var instrumentalContext: Context

    @Before
    fun setUp() {
        instrumentalContext = getInstrumentation().targetContext
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())

        ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            dataMovies.value?.results?.size?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it
                )
            }
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        val dataMovie = dataMovies.value?.results?.get(0)
        val position = dataMovies.value?.results?.indexOf(dataMovie)

        onView(withId(R.id.rv_movie)).perform(
            position?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it
                )
            }
        )
        onView(withId(R.id.rv_movie)).perform(
            position?.let {
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    it,
                    click()
                )
            }
        )

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(withText(dataMovie?.title)))
        onView(withId(R.id.tv_detail_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_rate)).check(
            matches(
                withText(
                    CoreMatchers.containsString(
                        dataMovie?.voteAverage.toString()
                    )
                )
            )
        )
        onView(withId(R.id.tv_sum_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_release)).check(matches(withText(dataMovie?.releaseDate)))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(dataMovie?.overview)))
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
    }

    @Test
    fun loadTVShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            dataTVShows.value?.results?.size?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it
                )
            }
        )
    }

    @Test
    fun loadDetailTVShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        val dataTVShow = dataTVShows.value?.results?.get(0)
        val position = dataTVShows.value?.results?.indexOf(dataTVShow)

        onView(withId(R.id.rv_tv_show)).perform(
            position?.let {
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    it,
                    click()
                )
            }
        )

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_title)).check(matches(withText(dataTVShow?.name)))

        onView(withId(R.id.tv_show_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_rate)).check(
            matches(
                withText(
                    CoreMatchers.containsString(
                        dataTVShow?.voteAverage.toString()
                    )
                )
            )
        )

        onView(withId(R.id.tv_original_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_original_name)).check(matches(withText(dataTVShow?.originalName)))
        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(withText(dataTVShow?.overview)))
        onView(withId(R.id.tv_air_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_air_date)).check(matches(withText(dataTVShow?.firstAirDate)))
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
    }
}