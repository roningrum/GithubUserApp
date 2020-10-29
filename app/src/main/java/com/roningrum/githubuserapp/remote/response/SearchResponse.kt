package com.roningrum.githubuserapp.remote.response

import com.google.gson.annotations.SerializedName
import com.roningrum.githubuserapp.remote.data.Items

data class SearchResponse(
    @field:SerializedName("total_count")
    val total_count: Int,
    @field:SerializedName("incomplete_results")
    val incomplete_results: Boolean,
    @field:SerializedName("items")
    val items: List<Items>
)