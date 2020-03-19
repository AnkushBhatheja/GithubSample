package com.pickletech.githubapplication.model

import com.google.gson.annotations.SerializedName


data class SearchResult(
    @SerializedName("total_count") val count: Int,
    @SerializedName("items") val items: List<Author>
)