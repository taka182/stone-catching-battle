package com.example.stone_catching_battle.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onStartGame: ()-> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("30") }
    val numbers = (30..100).toList()

    var playerCount by remember { mutableStateOf(30) }
    var selectedNumber by remember { mutableStateOf(30) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "石取りゲーム",
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
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                label = { Text("数値を選択") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                numbers.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.toString()) },
                        onClick = {
                            selectedText = item.toString()
                            selectedNumber = item
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // プレイヤー数選択ボタン
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 1..4) {
                Button(
                    onClick = { playerCount = i },
                    enabled = playerCount != i,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "$i 人")
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // ゲームスタートボタン
        Button(
            onClick = { onStartGame() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "ゲームスタート")
        }
    }
}
