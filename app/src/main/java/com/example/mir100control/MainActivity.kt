package com.example.mir100control


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.mir100control.model.MiRViewModel
import com.example.mir100control.presentation.home.HomeScreen
import com.example.mir100control.ui.theme.MiR100ControlTheme


class MainActivity : ComponentActivity() {
    private val viewModel: MiRViewModel<Any?> by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiR100ControlTheme {
                HomeScreen(viewModel)
            }
        }
    }
}
