package com.viona.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.viona.moviecatalogue.data.source.DataSourceInterface
import com.viona.moviecatalogue.data.source.remote.response.movie.DetailMovieResponse
import com.viona.moviecatalogue.data.source.remote.response.movie.MoviesResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowDetailResponse
import com.viona.moviecatalogue.data.source.remote.response.tvShow.TVShowsResponse
import com.viona.moviecatalogue.utils.EspressoIdlingResource
import com.viona.moviecatalogue.data.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val networkConfig: NetworkConfig) :
    DataSourceInterface {

    companion object {
        private val TAG = RemoteDataSource::class.java.simpleName
    }

    private fun incrementIdlingResource() {
        EspressoIdlingResource.increment()
    }

    fun decrementIdlingResource() {
        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }

    override fun getMovies(): MutableLiveData<MoviesResponse?> {
        incrementIdlingResource()

        val movies = MutableLiveData<MoviesResponse?>()
        val client = networkConfig.getApiService().getMovies()

        client
            .enqueue(object :
                Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { movies.postValue(it) }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }

                    decrementIdlingResource()
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                    decrementIdlingResource()
                }
            })

        return movies
    }

    override fun getMovieDetail(id: Int): MutableLiveData<DetailMovieResponse?> {
        incrementIdlingResource()

        val movie = MutableLiveData<DetailMovieResponse?>()
        val client = networkConfig.getApiService().getMovieDetail(id)

        client
            .enqueue(object :
                Callback<DetailMovieResponse> {
                override fun onResponse(
                    call: Call<DetailMovieResponse>,
                    movieResponse: Response<DetailMovieResponse>
                ) {
                    if (movieResponse.isSuccessful) {
                        movieResponse.body()?.let { movie.postValue(it) }
                    } else {
                        Log.e(TAG, "onFailure: ${movieResponse.message()}")
                    }

                    decrementIdlingResource()
                }

                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                    decrementIdlingResource()
                }
            })

        return movie
    }

    override fun getTVShows(): MutableLiveData<TVShowsResponse?> {
        incrementIdlingResource()

        val tvShows = MutableLiveData<TVShowsResponse?>()
        val client = networkConfig.getApiService().getTVShows()

        client
            .enqueue(object :
                Callback<TVShowsResponse> {
                override fun onResponse(
                    call: Call<TVShowsResponse>,
                    response: Response<TVShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { tvShows.postValue(it) }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }

                    decrementIdlingResource()
                }

                override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                    decrementIdlingResource()
                }
            })

        return tvShows
    }

    override fun getTVShowDetail(id: Int): MutableLiveData<TVShowDetailResponse?> {
        incrementIdlingResource()

        val tvShow = MutableLiveData<TVShowDetailResponse?>()
        val client = networkConfig.getApiService().getTVShowDetail(id)

        client
            .enqueue(object :
                Callback<TVShowDetailResponse> {
                override fun onResponse(
                    call: Call<TVShowDetailResponse>,
                    response: Response<TVShowDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { tvShow.postValue(it) }
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }

                    decrementIdlingResource()
                }

                override fun onFailure(call: Call<TVShowDetailResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                    decrementIdlingResource()
                }
            })
        return tvShow
    }
}