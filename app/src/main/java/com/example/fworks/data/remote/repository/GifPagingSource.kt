package com.example.fworks.data.remote.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.example.fworks.BuildConfig
import com.example.fworks.data.model.TredingGifImage
import retrofit2.HttpException


private const val STARTING_PAGE_INDEX = 1
private const val LIMIT = 5
class GifPagingSource (
    private val apiService: GifApiService,
    private val query: String
) : PagingSource<Int, TredingGifImage>() {
    override fun getRefreshKey(state: PagingState<Int, TredingGifImage>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TredingGifImage> {
        try {
           val currentLoadingPageKey = params.key ?: 1
           val offset: Int = (currentLoadingPageKey - 1) * LIMIT
           val response =
           if(query.isEmpty()) {
               apiService.gifListRequest(BuildConfig.GIPHY_API_KEY, params.loadSize, offset)
           }
            else{ apiService.gifSearchRequest(BuildConfig.GIPHY_API_KEY,params.loadSize,offset,query)
           }

            val responseData = mutableListOf<TredingGifImage>()
            val data = response.body()?.data ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(STARTING_PAGE_INDEX)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
        catch(e: HttpException)
        {
            return LoadResult.Error(e)
        }
    }
}
