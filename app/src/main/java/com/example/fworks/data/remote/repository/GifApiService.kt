package com.example.fworks.data.remote.repository

import com.example.fworks.data.model.GifResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GifApiService {
    @GET("trending")
    suspend fun gifListRequest(@Query("api_key") apiKey: String?,
                               @Query("limit") limit: Int?,
                               @Query("offset") offset: Int?
    ): Response<GifResponse>

    @GET("search")
    suspend fun gifSearchRequest(@Query("api_key") apiKey: String?,
                                 @Query("limit") limit: Int?,
                                 @Query("offset") offset: Int?,
                                 @Query("q") keyword: String? ): Response<GifResponse>

}