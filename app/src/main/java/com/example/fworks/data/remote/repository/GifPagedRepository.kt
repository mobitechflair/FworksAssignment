package com.example.fworks.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig



class GifPagedRepository constructor(private val apiService: GifApiService) {

    fun getGifResults(queryString: String)=
        Pager(
            config = PagingConfig (pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false),
                pagingSourceFactory = {GifPagingSource(apiService,queryString)}
        )
}