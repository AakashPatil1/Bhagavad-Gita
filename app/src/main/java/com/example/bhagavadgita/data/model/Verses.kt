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
    val id: Int,

    @ColumnInfo(name = "verse_number")
    @SerializedName("verse_number")
    @Expose
    val verseNumber: Int,

    @ColumnInfo(name = "chapter_number")
    @SerializedName("chapter_number")
    @Expose
    val chapterNumber: Int,

    @ColumnInfo(name = "slug")
    @SerializedName("slug")
    @Expose
    val slug: String,

    @ColumnInfo(name = "text")
    @SerializedName("text")
    @Expose
    val text: String,

    @ColumnInfo(name = "transliteration")
    @SerializedName("transliteration")
    @Expose
    val transliteration: String,

    @ColumnInfo(name = "word_meanings")
    @SerializedName("word_meanings")
    @Expose
    val wordMeanings: String,

    @ColumnInfo(name = "translations")
    @SerializedName("translations")
    @Expose
    @TypeConverters(TranslationListConverter::class)
    val translations: ArrayList<Translation>,

    @ColumnInfo(name = "commentaries")
    @SerializedName("commentaries")
    @Expose
    @TypeConverters(CommentaryListConverter::class)
    val commentaries: ArrayList<Commentary>
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

