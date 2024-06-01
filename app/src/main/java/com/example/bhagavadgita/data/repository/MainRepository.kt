package com.example.bhagavadgita.data.repository

import com.example.bhagavadgita.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getAllChapters() = apiHelper.getAllChapters()

    suspend fun getParticularChapter(chapterNumber: Int) = apiHelper.getParticularChapter(chapterNumber)

    suspend fun getAllVersesFromParticularChapter(chapterNumber: Int) = apiHelper.getAllVersesFromParticularChapter(chapterNumber)
    suspend fun getParticularVerse(chapterNumber: Int, versesNumber: Int) = apiHelper.getParticularVerse(chapterNumber, versesNumber)
}