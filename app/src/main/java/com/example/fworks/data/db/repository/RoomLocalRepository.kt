package com.example.fworks.data.db.repository

import androidx.lifecycle.LiveData
import com.example.fworks.data.db.entity.FavouriteGif


class RoomLocalRepository constructor(private val gifDatabase: GifDatabase)
{
    suspend fun insertGif(item: FavouriteGif) = gifDatabase.getGifDao().insertGif(item)

    suspend fun deleteGifById(id: Int) = gifDatabase.getGifDao().deleteGifById(id)

    //suspend fun clearGif() = gifDatabase.getGifDao().clearGif()

    fun getAllGifs(): LiveData<MutableList<FavouriteGif>> = gifDatabase.getGifDao().getAllGifs()
}