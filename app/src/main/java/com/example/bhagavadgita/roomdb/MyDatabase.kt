package com.example.bhagavadgita.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.data.model.Verses
import com.example.bhagavadgita.utils.CommentaryListConverter
import com.example.bhagavadgita.utils.TranslationListConverter

@Database(entities = [Chapters::class, Verses::class], version = 1, exportSchema = false)
@TypeConverters(TranslationListConverter::class, CommentaryListConverter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getDao(): BhagavadGitaDao
}