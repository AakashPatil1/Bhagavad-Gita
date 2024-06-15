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
    val id: Int? = null,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    val name: String? = null,

    @ColumnInfo(name = "slug")
    @SerializedName("slug")
    @Expose
    val slug: String? = null,

    @ColumnInfo(name = "name_transliterated")
    @SerializedName("name_transliterated")
    @Expose
    val nameTransliterated: String? = null,

    @ColumnInfo(name = "name_translated")
    @SerializedName("name_translated")
    @Expose
    val nameTranslated: String? = null,

    @ColumnInfo(name = "verses_count")
    @SerializedName("verses_count")
    @Expose
    val versesCount: Int? = null,

    @ColumnInfo(name = "chapter_number")
    @SerializedName("chapter_number")
    @Expose
    val chapterNumber: Int? = null,

    @ColumnInfo(name = "name_meaning")
    @SerializedName("name_meaning")
    @Expose
    val nameMeaning: String? = null,

    @ColumnInfo(name = "chapter_summary")
    @SerializedName("chapter_summary")
    @Expose
    val chapterSummary: String? = null,

    @ColumnInfo(name = "chapter_summary_hindi")
    @SerializedName("chapter_summary_hindi")
    @Expose
    val chapterSummaryHindi: String? = null
) {
    constructor() : this(
        id = 0,
        name = null,
        slug = null,
        nameTransliterated = null,
        nameTranslated = null,
        versesCount = null,
        chapterNumber = null,
        nameMeaning = null,
        chapterSummary = null,
        chapterSummaryHindi = null
    )
}