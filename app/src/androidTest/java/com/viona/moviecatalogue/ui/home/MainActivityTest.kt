package com.viona.moviecatalogue.ui.home

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.viona.moviecatalogue.R
import com.viona.moviecatalogue.data.network.NetworkConfig
import com.viona.moviecatalogue.data.source.remote.MovieRemoteDataSource
import com.viona.moviecatalogue.data.source.remote.TVShowRemoteDataSource
import com.viona.moviecatalogue.utils.EspressoIdlingResource
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test


class MainActivityTest {

    private val networkConfig = NetworkConfig()
    private val movieRemote = MovieRemoteDataSource(networkConfig.getApiService())
    private val tvShowRemote = TVShowRemoteDataSource(networkConfig.getApiService())

    private val dataMovies = movieRemote.getMovie()
    private val dataTVShows = tvShowRemote.getTVShow()

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = getInstrumentation().targetContext
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
        ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun testMovieTab() {
        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.fragment_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun testTVShowTab() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.fragment_tv_show)).check(matches(isDisplayed()))
    }

    @Test
    fun testFavoriteTab() {
        onView(withText(R.string.favorite)).perform(click())
        onView(withId(R.id.fragment_favorite)).check(matches(isDisplayed()))
    }

    @Test
    fun backButton() {
        onView(withText(R.string.favorite)).perform(click())
        onView(withId(R.id.fragment_favorite)).check(matches(isDisplayed()))
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText(R.string.tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_tv_show)).check(matches(isDisplayed()))
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText(R.string.movies)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun testMovie() {
        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        val size = dataMovies.value?.body?.results?.size!!
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                size
            )
        )
    }

    @Test
    fun testDetailMovie() {
        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        val dataMovie = dataMovies.value?.body?.results?.get(0)
        val position = dataMovies.value?.body?.results?.indexOf(dataMovie)

        onView(withId(R.id.rv_movie)).perform(
            position?.let {
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    it,
                    click()
                )
            }
        )

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop_path)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(withText(dataMovie?.title)))
        onView(withId(R.id.tv_original_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_original_title)).check(matches(withText(dataMovie?.originalTitle)))
        onView(withId(R.id.tv_detail_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(dataMovie?.overview)))
        onView(withId(R.id.tv_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_sum_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_release)).check(matches(withText(dataMovie?.releaseDate)))

        Espresso.openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
    }

    @Test
    fun testFavoriteMovie() {
        onView(withText(R.string.favorite)).perform(click())
        val moviesCount = getText(onView(withId(R.id.tv_movies_count))).split(" ").first().toInt()

        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        val dataMovie = dataMovies.value?.body?.results?.get(0)
        val position = dataMovies.value?.body?.results?.indexOf(dataMovie)

        onView(withId(R.id.rv_movie)).perform(
            position?.let { RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(it, click()) }
        )

        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText(R.string.favorite)).perform(click())
        onView(withId(R.id.tv_movies_count)).check(matches(withText("${moviesCount + 1} items")))
        onView(withId(R.id.cv_movies)).perform(click())
        onView(withText(dataMovie?.title)).check(matches(isDisplayed()))
        onView(withText(dataMovie?.title)).perform(click())

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.backdrop_path)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(withText(dataMovie?.title)))
        onView(withId(R.id.tv_original_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_original_title)).check(matches(withText(dataMovie?.originalTitle)))
        onView(withId(R.id.tv_detail_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(dataMovie?.overview)))
        onView(withId(R.id.tv_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_sum_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_release)).check(matches(withText(dataMovie?.releaseDate)))

        Espresso.openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.tv_movies_count)).check(matches(withText("$moviesCount items" )))
    }

    @Test
    fun testTVShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))

        val size = dataTVShows.value?.body?.results?.size
        onView(withId(R.id.rv_tv_show)).perform(
            size?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it
                )
            }
        )
    }

    @Test
    fun testDetailTVShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))

        val dataTVShow = dataTVShows.value?.body?.results?.get(0)
        val position = dataTVShows.value?.body?.results?.indexOf(dataTVShow)

        onView(withId(R.id.rv_tv_show)).perform(
            position?.let {
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    it,
                    click()
                )
            }
        )

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_backdrop_path)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_title)).check(matches(withText(dataTVShow?.name)))
        onView(withId(R.id.tv_original_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_original_name)).check(matches(withText(dataTVShow?.originalName)))
        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(withText(dataTVShow?.overview)))
        onView(withId(R.id.tv_air_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_air_date)).check(matches(withText(dataTVShow?.firstAirDate)))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(withText(dataTVShow?.originalLanguage)))

        Espresso.openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
    }

    @Test
    fun testfavoriteTVShow() {
        onView(withText(R.string.favorite)).perform(click())
        val tvShowsCount = getText(onView(withId(R.id.tv_tv_show_count))).split(" ").first().toInt()

        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))

        val res = dataTVShows.value?.body?.results!!
        val dataTVShow = res.first()
        val position = res.indexOf(dataTVShow)

        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click())
        )

        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText(R.string.favorite)).perform(click())
        onView(withId(R.id.tv_tv_show_count)).check(matches(withText("${tvShowsCount + 1} items")))
        onView(withId(R.id.cv_tv_shows)).perform(click())
        onView(withText(dataTVShow?.name)).check(matches(isDisplayed()))
        onView(withText(dataTVShow?.name)).perform(click())

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_backdrop_path)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_show_title)).check(matches(withText(dataTVShow?.name)))
        onView(withId(R.id.tv_original_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_original_name)).check(matches(withText(dataTVShow?.originalName)))
        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(withText(dataTVShow?.overview)))
        onView(withId(R.id.tv_air_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_air_date)).check(matches(withText(dataTVShow?.firstAirDate)))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(withText(dataTVShow?.originalLanguage)))

        Espresso.openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.tv_tv_show_count)).check(matches(withText("$tvShowsCount items")))
    }

    private fun getText(matcher: ViewInteraction): String {
        var text = String()
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return R.string.text_view.toString()
            }

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView
                text = tv.text.toString()
            }
        })

        return text
    }
}