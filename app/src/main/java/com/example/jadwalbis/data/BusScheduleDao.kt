package com.example.jadwalbis.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    @Query("SELECT * FROM bus_schedule ORDER BY departureTime ASC")
    fun getAllSchedules(): Flow<List<BusSchedule>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: BusSchedule)

    @Query("DELETE FROM bus_schedule")
    suspend fun deleteAll()
}
