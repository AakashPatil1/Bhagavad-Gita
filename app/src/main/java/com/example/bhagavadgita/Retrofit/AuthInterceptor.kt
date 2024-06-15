package com.example.bhagavadgita.Retrofit

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authenticatedRequest = originalRequest.newBuilder()
            .addHeader("X-RapidAPI-Key", "Enter your api key")
            .addHeader("X-RapidAPI-Host", "bhagavad-gita3.p.rapidapi.com")
            .build()
        return chain.proceed(authenticatedRequest)
    }
}
