package com.depi.testapp.ui.features.stop_watch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun StopWatchScreen() {
    var timeInSeconds by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (isRunning) {
                delay(1000L)
                timeInSeconds++
            }
        }
    }

    val timeDisplay = String.format(
        "%02d:%02d:%02d",
        (timeInSeconds / 3600),
        ((timeInSeconds % 3600) / 60),
        (timeInSeconds % 60)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = timeDisplay,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Medium
        )


        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(13.dp)
        ) {
            Button(onClick = { isRunning = !isRunning }, modifier = Modifier.weight(1f)) {
                Text(if (isRunning) "Pause" else "Start")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                isRunning = false
                timeInSeconds = 0
            }, modifier = Modifier.weight(1f)) {
                Text("Reset")
            }

            //there is a datetimepicker like in alert dialog didn't made
        }
    }
}
