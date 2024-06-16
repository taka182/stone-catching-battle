package com.example.stone_catching_battle.view

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun GameScreen(onClickButton: ()-> Unit = {}) {
    Text(text = "GameScreen")
    Button(onClick = onClickButton) {
        Text(text = "HomeScreenへ")
    }
}