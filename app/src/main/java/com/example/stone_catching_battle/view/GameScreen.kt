package com.example.stone_catching_battle.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stone_catching_battle.compose.CurrentPlayerTextItem
import com.example.stone_catching_battle.ui.theme.firstPlayerBackColor
import com.example.stone_catching_battle.ui.theme.fourthPlayerBackColor
import com.example.stone_catching_battle.ui.theme.secondPlayerBackColor
import com.example.stone_catching_battle.ui.theme.thirdPlayerBackColor
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
    val countUp by gameViewModel.countUp.collectAsState()

    val playerColors = listOf(
        firstPlayerBackColor,
        secondPlayerBackColor,
        thirdPlayerBackColor,
        fourthPlayerBackColor
    )

    LaunchedEffect(gameCurrentNumber) {
        if (gameTargetNumber == gameCurrentNumber) {
            onLose()
            gameViewModel.resetGame()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(playerColors[currentPlayerIndex]),
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

        items(gamePlayerCount) {
            val isCurrentPlayer = it == currentPlayerIndex
            CurrentPlayerTextItem(
                text = if (isCurrentPlayer) "プレイヤー ${it + 1}(現在のプレイヤー)" else "プレイヤー ${it + 1}",
                fontSize = 20.sp,
                fontWeight = if (isCurrentPlayer) FontWeight.Bold else FontWeight.Normal,
                isCurrentPlayer = isCurrentPlayer
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
                enabled = countUp > 0
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "次のプレイヤーへ"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "次のプレイヤーへ")
            }
        }
    }
}