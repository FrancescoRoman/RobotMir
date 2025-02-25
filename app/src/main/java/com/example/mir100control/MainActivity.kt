package com.example.mir100control

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mir100control.viewmodel.MiRViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MiRViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val status by viewModel.status.observeAsState("Stato: Pronto")

            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = status, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.getMissions() }) { Text("Ottieni Missioni") }
                Button(onClick = { viewModel.sendMission() }) { Text("Invia Missione") }
                Button(onClick = { viewModel.clearQueue() }) { Text("Cancella Coda") }
            }
        }
    }
}
