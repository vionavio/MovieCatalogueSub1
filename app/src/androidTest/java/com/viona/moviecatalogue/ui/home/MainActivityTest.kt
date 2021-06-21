package com.viona.moviecatalogue.ui.home

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
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
    private val movieRemoteDataSource = MovieRemoteDataSource(networkConfig.getApiService())
    private val tvShowRemoteDataSource = TVShowRemoteDataSource(networkConfig.getApiService())

    private val sampleMovies = movieRemoteDataSource.getMovies()
    private val sampleTVShows = tvShowRemoteDataSource.getTVShows()

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
    fun loadMovieTab() {
        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.fragment_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTVShowTab() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.fragment_tv_show)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteTab() {
        onView(withText(R.string.favorite)).perform(click())
        onView(withId(R.id.fragment_favorite)).check(matches(isDisplayed()))
    }

    @Test
    fun upButton() {
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
    fun loadMovie() {
        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        val moviesSize = sampleMovies.value?.body?.results?.size!!
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                moviesSize
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        val res = sampleMovies.value?.body?.results!!
        val sampleMovie = res.first()
        val position = res.indexOf(sampleMovie)

        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click())
        )

        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(withText(sampleMovie?.title)))
        onView(withId(R.id.tv_original_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_original_title)).check(matches(withText(sampleMovie?.originalTitle)))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(sampleMovie?.overview)))
        onView(withId(R.id.tv_date_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_release)).check(matches(withText(sampleMovie?.releaseDate)))

    }

    @Test
    fun favoriteMovie() {
        onView(withText(R.string.favorite)).perform(click())
        val moviesCount = getText(onView(withId(R.id.tv_movies_count))).split(" ").first().toInt()

        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        val res = sampleMovies.value?.body?.results!!
        val sampleMovie = res.first()
        val position = res.indexOf(sampleMovie)

        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click())
        )

        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText(R.string.favorite)).perform(click())
        onView(withId(R.id.tv_movies_count)).check(matches(withText("${moviesCount + 1} items")))
        onView(withId(R.id.card_movies)).perform(click())
        onView(withText(sampleMovie?.title)).check(matches(isDisplayed()))
        onView(withText(sampleMovie?.title)).perform(click())

        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(withText(sampleMovie?.title)))
        /*onView(withId(R.id.tv_original_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_original_title)).check(matches(withText(sampleMovie?.title)))
        onView(withId(R.id.overview)).check(matches(isDisplayed()))
        onView(withId(R.id.overview)).check(matches(withText(sampleMovie?.overview)))
        onView(withId(R.id.tv_date_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date_release)).check(matches(withText(sampleMovie?.releaseDate)))

        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(withText(sampleMovie?.releaseDate)))*/

        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.tv_movies_count)).check(matches(withText("$moviesCount items")))
    }

    @Test
    fun loadTVShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))

        val size = sampleTVShows.value?.body?.results?.size!!
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                size
            )
        )
    }

    @Test
    fun loadDetailTVShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))

        val res = sampleTVShows.value?.body?.results!!
        val sampleTVShow = res.first()
        val position = res.indexOf(sampleTVShow)

        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click())
        )

        onView(withId(R.id.tv_show_title)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_show_title)).check(matches(withText(sampleTVShow?.name)))
        onView(withId(R.id.tv_original_name)).check(matches(isDisplayed()))
       // onView(withId(R.id.tv_original_name)).check(matches(withText(sampleTVShow?.originalName)))
        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(withText(sampleTVShow?.overview)))
        onView(withId(R.id.tv_air_date)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_air_date)).check(matches(withText(sampleTVShow?.firstAirDate)))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        //onView(withId(R.id.tv_language)).check(matches(withText(sampleTVShow?.originalLanguage)))
    }

    @Test
    fun favoriteTVShow() {
        onView(withText(R.string.favorite)).perform(click())
        val tvShowsCount = getText(onView(withId(R.id.tv_tv_show_count))).split(" ").first().toInt()

        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))

        val res = sampleTVShows.value?.body?.results!!
        val sampleTVShow = res.first()
        val position = res.indexOf(sampleTVShow)

        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click())
        )

        onView(withId(R.id.action_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText(R.string.favorite)).perform(click())
        onView(withId(R.id.tv_tv_show_count)).check(matches(withText("${tvShowsCount + 1} items")))
        onView(withId(R.id.card_tv_shows)).perform(click())
        onView(withText(sampleTVShow?.name)).check(matches(isDisplayed()))
        onView(withText(sampleTVShow?.name)).perform(click())

       /* onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_name)).check(matches(withText(sampleTVShow?.name)))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(sampleTVShow?.overview)))
        onView(withId(R.id.tv_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_date)).check(matches(withText(sampleTVShow?.firstAirDate)))
*/
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
                return "Text of the view"
            }

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView
                text = tv.text.toString()
            }
        })

        return text
    }
}