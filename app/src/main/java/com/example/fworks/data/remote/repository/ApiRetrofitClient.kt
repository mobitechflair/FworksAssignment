package com.example.fworks.data.remote.repository

import com.example.fworks.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiRetrofitClient {

    companion object {
        var retrofitService: GifApiService? = null
        fun apiClient() : GifApiService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.NONE
            val oktHttpClient = OkHttpClient.Builder()
            oktHttpClient.addInterceptor(logging)
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.API_CALL_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(oktHttpClient.build())
                    .build()
                retrofitService = retrofit.create(GifApiService::class.java)
            }
            return retrofitService!!
        }
    }
}