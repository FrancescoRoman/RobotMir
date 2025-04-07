package com.example.mir100control.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MissionActions(
    onGetMissions: () -> Unit,
    onSendMission: () -> Unit,
    onClearQueue: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onGetMissions) {
            Text("Ottieni Missioni")
        }

        Button(onClick = onSendMission) {
            Icon(Icons.Filled.Send, contentDescription = "Invia")
            Spacer(modifier = Modifier.width(4.dp))
            Text("Invia Missione")
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = onClearQueue,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
    ) {
        Text("Cancella Coda")
    }
}
