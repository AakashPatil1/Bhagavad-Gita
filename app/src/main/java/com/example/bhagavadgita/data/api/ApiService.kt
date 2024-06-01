package com.example.bhagavadgita.data.api

import com.example.bhagavadgita.data.model.Chapters
import com.example.bhagavadgita.data.model.Verses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("chapters/?limit=18")
    suspend  fun getAllChapters(): Response<List<Chapters>>

    @GET("chapters/{chapterNumber}/")
    suspend fun getParticularChapter(@Path("chapterNumber") chapterNumber: Int): Response<Chapters>

    @GET("chapters/{chapterNumber}/verses/")
    suspend fun getAllVersesFromParticularChapter(@Path("chapterNumber") chapterNumber: Int): Response<List<Verses>>

    @GET("chapters/{chapterNumber}/verses/{versesNumber}/")
    suspend fun getParticularVerse(
        @Path("chapterNumber") chapterNumber: Int,
        @Path("versesNumber") versesNumber: Int
    ): Response<Verses>

}
