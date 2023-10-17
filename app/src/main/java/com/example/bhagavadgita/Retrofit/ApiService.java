package com.example.bhagavadgita.Retrofit;

import com.example.bhagavadgita.Models.Chapters;
import com.example.bhagavadgita.Models.Verses;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("chapters/?limit=18")
    Call<List<Chapters>> GetAllChapters();

    @GET("chapters/{chapterNumber}/")
    Call<Chapters> getParticularChapter(@Path("chapterNumber") int chapterNumber);

    @GET("chapters/{chapterNumber}/verses/")
    Call<List<Verses>> getAllVersesFromParticularChapter(@Path("chapterNumber") int chapterNumber);

    @GET("chapters/{chapterNumber}/verses/{versesNumber}/")
    Call<Verses> getParticularVerse(@Path("chapterNumber") int chapterNumber, @Path("versesNumber") int versesNumber);
}
