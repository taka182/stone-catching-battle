package com.example.stone_catching_battle

import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stone_catching_battle.ui.theme.Stone_catching_battleTheme
import com.example.stone_catching_battle.view.GameScreen
import com.example.stone_catching_battle.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "HomeScreen") {
                composable(route = "HomeScreen") {
                    HomeScreen(onStartGame = { navController.navigate("GameScreen")})
                }

                composable(route = "GameScreen") {
                    GameScreen(onClickButton = { navController.navigate("HomeScreen")})
                }
            }
        }
    }
}