package com.example.binapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binapp.domain.usecases.LoadHistoryUseCase
import com.example.binapp.domain.usecases.ClearHistoryUseCase
import com.example.binapp.presentation.states.HistoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val loadHistoryUseCase: LoadHistoryUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase
) : ViewModel() {

    // Внутренний поток для отслеживания истории
    private val _historyItems = MutableStateFlow<List<HistoryItem>>(emptyList())
    val historyItems: StateFlow<List<HistoryItem>> = _historyItems.asStateFlow()

    init {
        refreshHistory()
    }

    /**
     * Обновляем историю из репозитория
     */
    fun refreshHistory() {
        viewModelScope.launch {
            val cards = loadHistoryUseCase.invoke()
            _historyItems.emit(cards)
        }
    }

    /**
     * Очищаем историю запросов
     */
    fun clearHistory() {
        viewModelScope.launch {
            clearHistoryUseCase.invoke()
            refreshHistory()
        }
    }
}