package com.example.bhagavadgita.data.api

import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.data.model.Verses
import retrofit2.Response

interface ApiHelper {
    suspend fun getAllChapters(): Response<List<Chapters>>
    suspend fun getParticularChapter(chapterNumber: Int): Response<Chapters>
    suspend fun getAllVersesFromParticularChapter(chapterNumber: Int): Response<List<Verses>>
    suspend fun getParticularVerse(chapterNumber: Int, versesNumber: Int): Response<Verses>
}