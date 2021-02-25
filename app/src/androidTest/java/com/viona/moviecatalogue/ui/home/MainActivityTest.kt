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
import com.viona.moviecatalogue.data.source.remote.RemoteDataSource
import com.viona.moviecatalogue.utils.EspressoIdlingResource
import com.viona.moviecatalogue.data.network.NetworkConfig
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.allOf
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
        val dataMovie = dataMovies.value?.results?.get(0)

        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie)).perform(
            dataMovies.value?.results?.indexOf(dataMovie)?.let {
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    it,
                    click()
                )
            }
        )
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(withText(dataMovie?.title)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(withText(dataMovie?.releaseDate.toString())))
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
        onView(withId(R.id.tv_sum_rate)).check(matches(withText(dataMovie?.voteCount.toString())))

        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_duration)).check(matches(withText(dataMovie.duration)))

        onView(withId(R.id.tv_desctiption)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desctiption)).check(matches(withText(dataMovie?.overview)))

        onView(withId(R.id.tv_directors)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_directors)).check(matches(withText(dataMovie.director)))

        onView(withId(R.id.tv_stars)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_stars)).check(matches(withText(dataMovie.stars)))

        onView(withId(R.id.tv_writers)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_writers)).check(matches(withText(dataMovie.writers)))

        onView(withId(R.id.button_buy)).perform(click()).check(matches(isClickable()))
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
        val dataTVShow = dataTVShows.value?.results?.get(0)

        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(
            dataTVShows.value?.results?.indexOf(dataTVShow)?.let {
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    it,
                    click()
                )
            }
        )

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_title)).check(matches(isDisplayed()))
        onView(
            allOf(
                withId(R.id.tv_show_title),
                withText(dataTVShow?.name)
            )
        ).check(matches(withText(dataTVShow?.name)))

        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(withText(dataTVShow?.firstAirDate.toString())))

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
        onView(withId(R.id.tv_episodes)).check(matches(isDisplayed()))
        /*onView(withId(R.id.tv_episodes)).check(
            matches(
                withText(
                    CoreMatchers.containsString(
                        dataTVShow.episode.toString()
                    )
                )
            )
        )*/

        onView(withId(R.id.tv_type_show)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_type_show)).check(matches(withText(dataTVShow.type)))

        onView(withId(R.id.tv_actor)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_actor)).check(matches(withText(dataTVShow.star)))

        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(withText(dataTVShow?.overview)))

        onView(withId(R.id.button_buy)).perform(click()).check(matches(isClickable()))
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
    }
}