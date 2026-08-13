package com.example.jadwalbis.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bus_schedule")
data class BusSchedule(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val busName: String,
    val arrivalTime: String,
    val departureTime: String,
    val destination: String
)
