package com.viona.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProductionCountriesItem(

	@field:SerializedName("iso_3166_1")
	val iso31661: String? = null,

	val name: String? = null
)