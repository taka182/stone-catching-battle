package com.example.stone_catching_battle.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CurrentPlayerTextItem(
    text: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    isCurrentPlayer: Boolean) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        modifier = Modifier
            .padding(bottom = 12.dp)
            .background(
                color = if (isCurrentPlayer) Color(0xFFD3D3D3) else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
    )
}