package com.example.mir100control.presentation.home

import com.example.mir100control.model.MiRViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MiRViewModel<Any?>) {
    val status by viewModel.status.observeAsState(": Pronto")
    val battery by viewModel.battery.observeAsState(0f)
    val missions by viewModel.missions.observeAsState(emptyList())
    val selectedMission by viewModel.selectedMission.observeAsState()

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MiR 100 Control") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StatusCard(status = status, battery = battery)
            Spacer(modifier = Modifier.height(24.dp))
            MissionDropdown(
                missions = missions,
                selectedMission = selectedMission,
                expanded = expanded,
                onExpandChange = { expanded = it },
                onSelect = { viewModel.selectMission(it) }
            )
            Spacer(modifier = Modifier.height(24.dp))
            MissionActions(
                onGetMissions = { viewModel.getMissions() },
                onSendMission = { viewModel.sendMission() },
                onClearQueue = { viewModel.clearQueue() }
            )
            val missionGroups by viewModel.missionGroups.observeAsState(emptyList())



        }
    }
}
