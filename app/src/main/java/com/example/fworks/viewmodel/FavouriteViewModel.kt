package com.example.fworks.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fworks.data.db.entity.FavouriteGif
import com.example.fworks.data.db.repository.RoomLocalRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/*
class FavouriteViewModel(private val repository: RoomLocalRepository): ViewModel() {

    suspend fun insertGif(note: FavouriteGif) = repository.insertGif(note)

    suspend fun deleteGifById(id: Int) = repository.deleteGifById(id)

    suspend fun clearGif() = repository.clearGif()

    fun getAllGifs() = repository.getAllGifs()
}*/

class FavouriteViewModel(private val repository: RoomLocalRepository): ViewModel()
{
    // In coroutines thread insert item in insert function.
    fun insert(item: FavouriteGif) = GlobalScope.launch {
        repository.insertGif(item)
    }

    // In coroutines thread delete item in delete function.
    fun delete(id: Int) = GlobalScope.launch {
        repository.deleteGifById(id)
    }

    //Here we initialized allGroceryItems function with repository
    fun getAllGif() = repository.getAllGifs()
}