package com.example.bhagavadgita.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.bhagavadgita.utils.CommentaryListConverter
import com.example.bhagavadgita.utils.TranslationListConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "verses")
data class Verses(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    var id: Int,

    @ColumnInfo(name = "verse_number")
    @SerializedName("verse_number")
    @Expose
    var verseNumber: Int,

    @ColumnInfo(name = "chapter_number")
    @SerializedName("chapter_number")
    @Expose
    var chapterNumber: Int,

    @ColumnInfo(name = "slug")
    @SerializedName("slug")
    @Expose
    var slug: String,

    @ColumnInfo(name = "text")
    @SerializedName("text")
    @Expose
    var text: String,

    @ColumnInfo(name = "transliteration")
    @SerializedName("transliteration")
    @Expose
    var transliteration: String,

    @ColumnInfo(name = "word_meanings")
    @SerializedName("word_meanings")
    @Expose
    var wordMeanings: String,

    @ColumnInfo(name = "translations")
    @SerializedName("translations")
    @Expose
    @TypeConverters(TranslationListConverter::class)
    var translations: ArrayList<Translation>,

    @ColumnInfo(name = "commentaries")
    @SerializedName("commentaries")
    @Expose
    @TypeConverters(CommentaryListConverter::class)
    var commentaries: ArrayList<Commentary>
){
    constructor() : this(
        id = 0,
        verseNumber = 0,
        chapterNumber = 0,
        slug = "",
        text = "",
        transliteration = "",
        wordMeanings = "",
        translations = arrayListOf(),
        commentaries = arrayListOf()
    )
}

