package com.example.mir100control.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mir100control.ui.theme.GreenSuccess
import com.example.mir100control.ui.theme.RedError
import com.example.mir100control.ui.theme.YellowWarning

@Composable
fun StatusCard(status: String, battery: Float) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Stato: $status", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.BatteryFull,
                    contentDescription = "Batteria",
                    tint = when {
                        battery > 50 -> GreenSuccess
                        battery > 20 -> YellowWarning
                        else -> RedError
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Batteria: ${battery.toInt()}%", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
