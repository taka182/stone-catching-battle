package com.example.stone_catching_battle.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stone_catching_battle.viewmodel.GameViewModel

@Composable
fun GameScreen(
    gameViewModel: GameViewModel,
    onLose: () -> Unit,
) {
    val gamePlayerCount by gameViewModel.gamePlayerCount.collectAsState()
    val gameTargetNumber by gameViewModel.gameTargetNumber.collectAsState()
    val gameCurrentNumber by gameViewModel.gameCurrentNumber.collectAsState()
    val currentPlayerIndex by gameViewModel.currentPlayerIndex.collectAsState()

    LaunchedEffect(gameCurrentNumber) {
        if (gameTargetNumber == gameCurrentNumber) {
            onLose()
            gameViewModel.resetGame()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFEFEFEF)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Text(
                text = "当てる数値: $gameTargetNumber",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        item {
            Text(
                text = "現在の数値: $gameCurrentNumber",
                fontSize = 36.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        items(gamePlayerCount) { i ->
            Text(
                text = if (i == currentPlayerIndex) "プレイヤー ${i + 1}(現在のプレイヤー)" else "プレイヤー ${i + 1}",
                fontSize = 18.sp,
                fontWeight = if (i == currentPlayerIndex) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .background(
                        color = if (i == currentPlayerIndex) Color(0xFFD3D3D3) else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    gameViewModel.incrementPlayerCountUp(1)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowUpward, contentDescription = "1UP")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "1UP")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    gameViewModel.nextPlayer()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                enabled = gameViewModel.countUp.value > 0
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "次のプレイヤーへ")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "次のプレイヤーへ")
            }
        }
    }
}
