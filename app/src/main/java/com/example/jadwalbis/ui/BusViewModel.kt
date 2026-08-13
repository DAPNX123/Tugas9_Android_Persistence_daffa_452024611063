package com.example.jadwalbis.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jadwalbis.data.AppDatabase
import com.example.jadwalbis.data.BusRepository
import com.example.jadwalbis.data.BusSchedule
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BusViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BusRepository
    val allSchedules: StateFlow<List<BusSchedule>>

    init {
        val busScheduleDao = AppDatabase.getDatabase(application).busScheduleDao()
        repository = BusRepository(busScheduleDao)
        allSchedules = repository.allSchedules.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun insert(busName: String, arrivalTime: String, departureTime: String, destination: String) {
        viewModelScope.launch {
            repository.insert(
                BusSchedule(
                    busName = busName,
                    arrivalTime = arrivalTime,
                    departureTime = departureTime,
                    destination = destination
                )
            )
        }
    }
}
