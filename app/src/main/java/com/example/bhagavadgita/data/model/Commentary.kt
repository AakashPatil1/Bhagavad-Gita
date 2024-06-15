package com.example.bhagavadgita.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Commentary (
    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("author_name")
    @Expose
    var authorName: String? = null,

    @SerializedName("language")
    @Expose
    var language: String? = null
)
