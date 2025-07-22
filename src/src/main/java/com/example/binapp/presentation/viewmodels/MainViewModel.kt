package com.example.binapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binapp.domain.models.CardInfo
import com.example.binapp.domain.usecases.GetCardInfoUseCase
import com.example.binapp.domain.usecases.SaveCardToHistoryUseCase
import com.example.binapp.presentation.states.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCardInfoUseCase: GetCardInfoUseCase,
    private val saveCardToHistoryUseCase: SaveCardToHistoryUseCase
) : ViewModel() {

    // Текущее состояние экрана
    private val _screenState = MutableLiveData(ScreenState.Idle)
    val screenState: LiveData<ScreenState> = _screenState

    // Загрузка информации о карте
    fun loadCardInfo(binNumber: String) {
        _screenState.postValue(ScreenState.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            val result = getCardInfoUseCase.invoke(binNumber)

            if (result.isSuccess) {
                val cardInfo = result.getOrNull()
                saveCardToHistoryUseCase.invoke(cardInfo!!)
                _screenState.postValue(ScreenState.Success(cardInfo))
            } else {
                _screenState.postValue(ScreenState.Error(result.exceptionOrNull()))
            }
        }
    }
}