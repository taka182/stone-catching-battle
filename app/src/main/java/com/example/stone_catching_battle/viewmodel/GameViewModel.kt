package com.example.stone_catching_battle.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val _gamePlayerCount = MutableStateFlow(2)
    val gamePlayerCount: StateFlow<Int> = _gamePlayerCount

    private val _gameTargetNumber = MutableStateFlow(30)
    val gameTargetNumber: StateFlow<Int> = _gameTargetNumber

    private val _gameCurrentNumber = MutableStateFlow(0)
    val gameCurrentNumber: StateFlow<Int> = _gameCurrentNumber

    private val _currentPlayerIndex = MutableStateFlow(0)
    val currentPlayerIndex: StateFlow<Int> = _currentPlayerIndex

    private val _countUp = MutableStateFlow(0)
    val countUp: StateFlow<Int> = _countUp

    init {
        viewModelScope.launch {
            _countUp.collect {
                if (it == 3) {
                    nextPlayer()
                }
            }
        }
    }

    fun setGamePlayerCount(count: Int) {
        _gamePlayerCount.value = count
    }

    fun setGameTargetNumber(number: Int) {
        _gameTargetNumber.value = number
    }

    fun incrementPlayerCountUp(countUp: Int) {
        _gameCurrentNumber.value += countUp
        _countUp.value += countUp
    }

    private fun resetCountUp() {
        _countUp.value = 0
    }

    fun resetGame() {
        _gameCurrentNumber.value = 0
        _currentPlayerIndex.value = 0
        resetCountUp()
    }

    fun nextPlayer() {
        if (_countUp.value > 0) {
            _currentPlayerIndex.value = (++_currentPlayerIndex.value) % _gamePlayerCount.value
        }
        resetCountUp()
    }
}