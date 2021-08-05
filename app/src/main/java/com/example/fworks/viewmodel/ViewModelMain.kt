package com.example.fworks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.cab.fworkstask.viewmodel.Event
import com.example.fworks.data.model.TredingGifImage
import com.example.fworks.data.remote.repository.GifPagedRepository

class ViewModelMain constructor(private val gifRepository: GifPagedRepository) : ViewModel() {
    companion object {
    private const val DEFAULT_KEYWORD = ""
}
    private val selectedItemEvent = MutableLiveData<Event<TredingGifImage>>()
    fun setSelectedITem(item: TredingGifImage) {
        selectedItemEvent.value = Event(item)
    }
    fun getSelectedITem(): MutableLiveData<Event<TredingGifImage>> {
        return selectedItemEvent
    }

    fun getSelectedItem() {

    }
    val currentQuery = MutableLiveData(DEFAULT_KEYWORD)
    fun searchGifs(query: String)
    {
        currentQuery.value = query
    }
    val gif = currentQuery.switchMap { queryString->
        gifRepository.getGifResults(queryString).liveData.cachedIn(viewModelScope)
    }
}
