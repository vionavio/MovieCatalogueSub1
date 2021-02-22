package com.viona.moviecatalogue.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private val idlingResource = CountingIdlingResource(Constants.RESOURCE)

    fun getEspressoIdlingResource(): IdlingResource = idlingResource
    fun increment() = idlingResource.increment()
    fun decrement() = idlingResource.decrement()

}