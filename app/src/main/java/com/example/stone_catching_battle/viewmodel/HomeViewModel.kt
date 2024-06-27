package com.example.stone_catching_battle.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel(){

    private val _playerCount = MutableStateFlow(2)
    val playerCount: StateFlow<Int> = _playerCount

    private val _targetNumber = MutableStateFlow(30)
    val targetNumber: StateFlow<Int> = _targetNumber

    fun setPlayerCount(count: Int) {
        _playerCount.value = count
    }

    fun setTargetNumber(number: Int) {
        _targetNumber.value = number
    }
}