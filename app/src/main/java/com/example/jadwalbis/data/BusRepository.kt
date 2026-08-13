package com.example.jadwalbis.data

import kotlinx.coroutines.flow.Flow

class BusRepository(private val busScheduleDao: BusScheduleDao) {
    val allSchedules: Flow<List<BusSchedule>> = busScheduleDao.getAllSchedules()

    suspend fun insert(schedule: BusSchedule) {
        busScheduleDao.insertSchedule(schedule)
    }
}
