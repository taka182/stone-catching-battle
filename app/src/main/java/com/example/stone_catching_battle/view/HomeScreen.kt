package com.example.stone_catching_battle.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stone_catching_battle.R
import com.example.stone_catching_battle.util.PlayerOptions
import com.example.stone_catching_battle.compose.SelectPlayerButtonItem
import com.example.stone_catching_battle.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    onStartGame: (Int, Int) -> Unit = { _, _ -> }
) {
    val playerCount by homeViewModel.playerCount.collectAsState()
    val targetNumber by homeViewModel.targetNumber.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    val numbers = (30..100).toList()

    LazyColumn(
        modifier = Modifier
            .padding(16.dp),
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = stringResource(R.string.rule_title_text),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.rule_text),
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.game_title_text),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // ゲームで使用する数値を設定
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = targetNumber.toString(),
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true,
                        label = { Text(stringResource(R.string.select_numerical_value_text)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled = true)
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        numbers.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item.toString()) },
                                onClick = {
                                    homeViewModel.setTargetNumber(item)
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))

                // プレイヤー数選択ボタン
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PlayerOptions.values().filter { it.ordinal >= PlayerOptions.TWO.ordinal }
                        .forEach { playerOptions ->
                            SelectPlayerButtonItem(
                                text = "${playerOptions.count} 人",
                                isSelected = playerCount == playerOptions.count,
                                onClick = { homeViewModel.setPlayerCount(playerOptions.count) }
                            )
                        }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        onStartGame(targetNumber, playerCount)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.start_game_button_text))
                }
            }
        }
    }
}