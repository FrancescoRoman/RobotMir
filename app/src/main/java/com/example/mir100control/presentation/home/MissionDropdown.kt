package com.example.mir100control.presentation.home

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun MissionDropdown(
    missions: List<com.example.mir100control.core.network.Mission>,
    selectedMission: String?,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onSelect: (String) -> Unit
) {
    Button(onClick = { onExpandChange(true) }) {
        Text(selectedMission ?: "Seleziona una missione")
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onExpandChange(false) }
    ) {
        missions.forEach { mission ->
            DropdownMenuItem(
                text = { Text(mission.name) },
                onClick = {
                    onSelect(mission.guid)
                    onExpandChange(false)
                }
            )
        }
    }
}
