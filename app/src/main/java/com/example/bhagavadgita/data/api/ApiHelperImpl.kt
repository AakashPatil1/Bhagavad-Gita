package com.example.bhagavadgita.data.api

import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.data.model.Verses
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAllChapters(): Response<List<Chapters>> =  apiService.getAllChapters()
    override suspend fun getParticularChapter(chapterNumber: Int): Response<Chapters> = apiService.getParticularChapter(chapterNumber)
    override suspend fun getAllVersesFromParticularChapter(chapterNumber: Int): Response<List<Verses>> = apiService.getAllVersesFromParticularChapter(chapterNumber)
    override suspend fun getParticularVerse(chapterNumber: Int, versesNumber: Int): Response<Verses> = apiService.getParticularVerse(chapterNumber,versesNumber)

}