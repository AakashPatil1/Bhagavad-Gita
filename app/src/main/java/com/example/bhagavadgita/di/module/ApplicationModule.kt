package com.example.bhagavadgita.di.module

import com.example.bhagavadgita.Retrofit.AuthInterceptor
import com.example.bhagavadgita.data.api.ApiHelper
import com.example.bhagavadgita.data.api.ApiHelperImpl
import com.example.bhagavadgita.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

}

