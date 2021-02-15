package com.viona.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.DataMovie
import com.viona.moviecatalogue.data.DataTVShow
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test


class MainActivityTest {
    private val dataMovies = DataMovie.generateDummyMovie()
    private val dataTVShows = DataTVShow.generateDummyTVShow()

    private val dataMovie = dataMovies[0]
    private val dataTVShow = dataTVShows[0]


    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>
                (dataMovies.size)
        )
    }

    @Test
    fun loadDetailMovie() {

        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                dataMovies.indexOf(dataMovie),
                click()
            )
        )
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(withText(dataMovie.title)))
        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(withText(dataMovie.year.toString())))
        onView(withId(R.id.tv_detail_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_rate)).check(
            matches(
                withText(
                    CoreMatchers.containsString(
                        dataMovie.rating.toString()
                    )
                )
            )
        )
        onView(withId(R.id.tv_sum_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_sum_rate)).check(matches(withText(dataMovie.people_rate.toString())))

        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(withText(dataMovie.duration)))

        onView(withId(R.id.tv_desctiption)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desctiption)).check(matches(withText(dataMovie.description)))

        onView(withId(R.id.tv_directors)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_directors)).check(matches(withText(dataMovie.director)))

        onView(withId(R.id.tv_stars)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_stars)).check(matches(withText(dataMovie.stars)))

        onView(withId(R.id.tv_writers)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_writers)).check(matches(withText(dataMovie.writers)))

        onView(withId(R.id.button_buy)).perform(click()).check(matches(isClickable()))
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

    }

    @Test
    fun loadTVShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>
                (dataTVShows.size)
        )
    }

    @Test
    fun loadDetailTVShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                dataTVShows.indexOf(dataTVShow),
                click()
            )
        )

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_title)).check(matches(isDisplayed()))
        onView(
            allOf(
                withId(R.id.tv_show_title),
                withText(dataTVShow.title)
            )
        ).check(matches(withText(dataTVShow.title)))

        onView(withId(R.id.tv_year)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_year)).check(matches(withText(dataTVShow.year.toString())))

        onView(withId(R.id.tv_show_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_rate)).check(
            matches(
                withText(
                    CoreMatchers.containsString(
                        dataTVShow.rating.toString()
                    )
                )
            )
        )
        onView(withId(R.id.tv_episodes)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_episodes)).check(
            matches(
                withText(
                    CoreMatchers.containsString(
                        dataTVShow.episode.toString()
                    )
                )
            )
        )

        onView(withId(R.id.tv_type_show)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_type_show)).check(matches(withText(dataTVShow.type)))

        onView(withId(R.id.tv_actor)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_actor)).check(matches(withText(dataTVShow.star)))

        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(withText(dataTVShow.description)))

        onView(withId(R.id.button_buy)).perform(click()).check(matches(isClickable()))
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
    }
}