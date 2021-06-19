package com.viona.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.viona.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object RemoteHelper {
    private val TAG = RemoteHelper::class.java.simpleName

    fun <T> call(call: Call<T>): LiveData<ApiResponse<T>> {
        EspressoIdlingResource.increment()
        val returnVal = MutableLiveData<ApiResponse<T>>()

        Executors.newFixedThreadPool(5).execute {
            val response = call.execute()

            if (response.isSuccessful) {
                response.body()?.let {
                    returnVal.postValue(ApiResponse.success(it))
                }
            } else {
                Log.e(TAG, "Error: " + response.errorBody()?.string())
                returnVal.postValue(null)
            }
            decrementalIdlingResource()
        }
        return returnVal
    }

    private fun decrementalIdlingResource() {
        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }
}