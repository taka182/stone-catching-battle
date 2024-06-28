package com.example.stone_catching_battle.compose

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.sp

@Composable
fun SelectPlayerButtonItem(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color.Green else Color.Gray,
            contentColor = Color.White
        ),
        modifier = Modifier
            .graphicsLayer {
                alpha = if (isSelected) 0.9f else 0.6f
            }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp)
        )
    }
}