package com.example.composesamples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun Timer(onTimeout: () -> Unit) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)

    LaunchedEffect(Unit) {
        delay(5000)
        currentOnTimeout()
    }
}

@Composable
fun Timer2(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(5000)
        onTimeout()
    }
}

/**
 * The button click is to simulate recomposition
 * But in real scenario recomposition could be caused due to configuration change
 * Time2 shows the onTimeOut changes on recomposition
 * Timer shows the solution of how to correct this
 */
@Composable
fun TimerScreen() {
    var text by remember { mutableStateOf("Waiting...") }
    var count by remember { mutableStateOf(0) }  // Track recompositions
    var lastUpdated by remember { mutableStateOf(System.currentTimeMillis()) } // Track latest update time

    Timer {
        text = "Time out! Count: $count at ${lastUpdated % 10000}"  // Unique identifier
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text)
        Button(onClick = { count++; lastUpdated = System.currentTimeMillis() }) {
            Text("Recompose")
        }
        Text("Recomposition count: $count")
    }
}