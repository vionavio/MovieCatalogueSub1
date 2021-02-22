package com.viona.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProductionCompaniesItem(

	@field:SerializedName("logo_path")
	val logoPath: Any? = null,
	val name: String? = null,
	val id: Int? = null,
	@field:SerializedName("origin_country")
	val originCountry: String? = null
)