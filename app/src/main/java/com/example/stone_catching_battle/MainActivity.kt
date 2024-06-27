package com.example.stone_catching_battle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stone_catching_battle.view.GameScreen
import com.example.stone_catching_battle.view.HomeScreen
import com.example.stone_catching_battle.view.LoseScreen
import com.example.stone_catching_battle.viewmodel.GameViewModel
import com.example.stone_catching_battle.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val gameViewModel: GameViewModel = viewModel()

            NavHost(navController = navController, startDestination = "HomeScreen") {
                composable(route = "HomeScreen") {
                    HomeScreen { targetValue, playerCount ->
                        gameViewModel.setGamePlayerCount(playerCount)
                        gameViewModel.setGameTargetNumber(targetValue)
                        navController.navigate("GameScreen")
                    }
                }

                composable(route = "GameScreen") {
                    GameScreen(gameViewModel) {
                        navController.navigate("LoseScreen")
                    }
                }

                composable(route = "LoseScreen") {
                    LoseScreen {
                        navController.navigate("HomeScreen")
                    }
                }
            }
        }
    }
}