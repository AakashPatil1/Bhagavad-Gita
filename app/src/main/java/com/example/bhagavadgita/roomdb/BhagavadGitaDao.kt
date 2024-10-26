package com.example.bhagavadgita.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.data.model.Verses

@Dao
interface BhagavadGitaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChapters(listChapters: List<Chapters>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVerses(listVerses: List<Verses>)

    @Query("SELECT * FROM chapters WHERE chapter_number = :chapter")
    fun getParticularChapter(chapter: Int): Chapters

    @Query("SELECT * FROM chapters")
    fun getChapters(): LiveData<List<Chapters>>

    @Query("SELECT * FROM verses")
    fun getVerses(): LiveData<List<Verses>>

    @Query("SELECT * FROM verses WHERE verse_number = :verseNumber AND chapter_number = :chapterNumber")
    fun getParticularVerses(verseNumber: Int, chapterNumber: Int): Verses

}