package com.example.fworks.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fworks.data.db.repository.RoomLocalRepository

class FavouriteViewModelFactory(
    private val repository: RoomLocalRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(RoomLocalRepository::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {
            Log.e("VMFactory create", e.message.toString())
        }
        return super.create(modelClass)
    }
}