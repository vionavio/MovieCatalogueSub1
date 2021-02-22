package com.viona.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesItem(

	val name: String? = null,

	@field:SerializedName("iso_639_1")
	val iso6391: String? = null,

	@field:SerializedName("english_name")
	val englishName: String? = null
)
