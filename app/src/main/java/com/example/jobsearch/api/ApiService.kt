package com.example.jobsearch.api

import com.example.jobsearch.data.JobSearchResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * @FileName:  ApiService
 * @Author:  ganlong
 * @CreateDate:  2022/7/21
 * @Description:
 */
interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("/")
    suspend fun searchJobs(@Body body: String): Response<String>

    companion object {
        private const val BASE_URL = "https://api.graphql.jobs/"
        fun create(): ApiService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

           return Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
               .client(client)
                .build().create(ApiService::class.java)
//            return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ApiService::class.java)
        }
    }
}