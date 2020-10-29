package com.roningrum.githubuserapp.remote

import com.roningrum.githubuserapp.util.Util.github_url
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubAPIConfig {
    val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { message ->
                println("Log-App : $message")
            }
        ).apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        })
        addNetworkInterceptor(HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { message ->
                println("Log-Net : $message")
            }
        ).apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }.build()
    val retrofitClient: Retrofit?
        get() = Retrofit.Builder()
            .baseUrl(github_url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
}