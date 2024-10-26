package com.example.bhagavadgita.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "chapters")
data class Chapters(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: Int? = 0,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    var name: String? = "",

    @ColumnInfo(name = "slug")
    @SerializedName("slug")
    @Expose
    var slug: String? = "",

    @ColumnInfo(name = "name_transliterated")
    @SerializedName("name_transliterated")
    @Expose
    var nameTransliterated: String? = "",

    @ColumnInfo(name = "name_translated")
    @SerializedName("name_translated")
    @Expose
    var nameTranslated: String? = "",

    @ColumnInfo(name = "verses_count")
    @SerializedName("verses_count")
    @Expose
    var versesCount: Int? = -1,

    @ColumnInfo(name = "chapter_number")
    @SerializedName("chapter_number")
    @Expose
    var chapterNumber: Int? = -1,

    @ColumnInfo(name = "name_meaning")
    @SerializedName("name_meaning")
    @Expose
    var nameMeaning: String? = "null",

    @ColumnInfo(name = "chapter_summary")
    @SerializedName("chapter_summary")
    @Expose
    var chapterSummary: String? = null,

    @ColumnInfo(name = "chapter_summary_hindi")
    @SerializedName("chapter_summary_hindi")
    @Expose
    var chapterSummaryHindi: String? = ""
) {
    constructor() : this(
        id = 0,
        name = null,
        slug = null,
        nameTransliterated = null,
        nameTranslated = null,
        versesCount =null,
        chapterNumber =null,
        nameMeaning = null,
        chapterSummary = null,
        chapterSummaryHindi = null
    )
}