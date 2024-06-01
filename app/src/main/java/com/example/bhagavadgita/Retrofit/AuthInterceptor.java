package com.example.bhagavadgita.Retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request authenticatedRequest = originalRequest.newBuilder()
//                .addHeader("X-RapidAPI-Key", "Enter your api key")
                .addHeader("X-RapidAPI-Key", "7f7d78c955msh8521a80d9ba3a74p16847djsn7461a83a4d4c")
                .addHeader("X-RapidAPI-Host", "bhagavad-gita3.p.rapidapi.com")
                .build();

        return chain.proceed(authenticatedRequest);
    }
}
