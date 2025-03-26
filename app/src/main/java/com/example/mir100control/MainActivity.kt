package com.example.mir100control

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mir100control.viewmodel.MiRViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val viewModel: MiRViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val status by viewModel.status.observeAsState("Stato: Pronto")
            val battery by viewModel.battery.observeAsState(0f)
            val missions by viewModel.missions.observeAsState(emptyList())
            val selectedMission by viewModel.selectedMission.observeAsState()

            var expanded by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Stato: $status", style = MaterialTheme.typography.headlineSmall)
                        Text(text = "Batteria: ${battery.toInt()}%", style = MaterialTheme.typography.bodyLarge)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box {
                    Button(onClick = { expanded = true }) {
                        Text(selectedMission ?: "Seleziona una missione")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        missions.forEach { mission ->
                            DropdownMenuItem(
                                text = { Text(mission.name) },
                                onClick = {
                                    viewModel.selectMission(mission.guid)
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                Button(onClick = { viewModel.getMissions() }) { Text("Ottieni Missioni") }
                Button(onClick = { viewModel.sendMission() }) { Text("Invia Missione") }
                Button(onClick = { viewModel.clearQueue() }) { Text("Cancella Coda") }
            }
        }
    }
}

