package com.example.jadwalbis

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.jadwalbis.data.AppDatabase
import com.example.jadwalbis.data.BusSchedule
import com.example.jadwalbis.data.BusScheduleDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BusDatabaseTest {
    private lateinit var busDao: BusScheduleDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        busDao = db.busScheduleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadSchedule() = runBlocking {
        val schedule = BusSchedule(
            busName = "Test Bus",
            arrivalTime = "10:00",
            departureTime = "08:00",
            destination = "Jakarta"
        )
        busDao.insertSchedule(schedule)
        val allSchedules = busDao.getAllSchedules().first()
        assertEquals(allSchedules[0].busName, "Test Bus")
        assertEquals(allSchedules[0].destination, "Jakarta")
    }
}
