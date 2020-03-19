package com.pickletech.githubapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Author(
    @PrimaryKey val id: Long,
    @SerializedName("login") val name: String,
    @SerializedName("avatar_url") val imageUrl: String,
    @SerializedName("html_url") val pageUrl: String
)