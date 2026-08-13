package com.example.jadwalbis.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jadwalbis.data.BusSchedule

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusScheduleScreen(viewModel: BusViewModel = viewModel()) {
    val schedules by viewModel.allSchedules.collectAsState()
    var busName by remember { mutableStateOf("") }
    var arrivalTime by remember { mutableStateOf("") }
    var departureTime by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Jadwal Bis") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Form to add new schedule
            OutlinedTextField(
                value = busName,
                onValueChange = { busName = it },
                label = { Text("Nama Bis") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = departureTime,
                    onValueChange = { departureTime = it },
                    label = { Text("Keberangkatan") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = arrivalTime,
                    onValueChange = { arrivalTime = it },
                    label = { Text("Kedatangan") },
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = destination,
                onValueChange = { destination = it },
                label = { Text("Tujuan") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (busName.isNotBlank()) {
                        viewModel.insert(busName, arrivalTime, departureTime, destination)
                        busName = ""
                        arrivalTime = ""
                        departureTime = ""
                        destination = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tambah Jadwal")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text("Daftar Jadwal:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(schedules) { schedule ->
                    BusScheduleItem(schedule)
                }
            }
        }
    }
}

@Composable
fun BusScheduleItem(schedule: BusSchedule) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = schedule.busName, style = MaterialTheme.typography.titleLarge)
            Text(text = "Tujuan: ${schedule.destination}")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Berangkat: ${schedule.departureTime}")
                Text(text = "Datang: ${schedule.arrivalTime}")
            }
        }
    }
}
