package com.intermedia.challenge.ui.events

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.intermedia.challenge.data.models.Event
import com.intermedia.challenge.data.models.NetResult
import com.intermedia.challenge.data.repositories.EventsRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class EventsViewModel(private val eventsRepository: EventsRepository) : ViewModel() {
    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> get() = _events
    var eventList = mutableListOf<Event>()

    init {
        loadEvents()
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadEvents() {
        viewModelScope.launch {
            when (val response = eventsRepository.getEvents()) {
                is NetResult.Success -> {
                    val today = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())

                    for(item in response.data.eventsList.events){
                        if(item.start != null && item.start >= today && eventList.size<25){
                            eventList.add(item)
                        }
                    }
                    _events.postValue(eventList.sortedBy { it.start })
                }
                is NetResult.Error -> {
                    Log.e(response.toString(), "Error")
                }
            }
        }
    }

}