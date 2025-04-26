package com.example.composesamples

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntOffset

import kotlin.math.roundToInt

@Composable
fun DraggablePanel() {
    val panelWidth = 300.dp
    val visiblePartWidth = 100.dp

    val maxOffsetPx = with(LocalDensity.current) { (visiblePartWidth - panelWidth).toPx() }
    val minOffsetPx = 0f

    var offsetX by remember { mutableFloatStateOf(maxOffsetPx) }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .width(panelWidth)
            .background(Color.Gray)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    offsetX = (offsetX + dragAmount).coerceIn(maxOffsetPx, minOffsetPx)
                }
            }
    ) {
        Text(modifier = Modifier.align(Alignment.Center),text = "Draggable Panel Content")
    }
}