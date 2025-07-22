package com.example.binapp.domain.usecases

import com.example.binapp.data.repository.ICardRepository
import com.example.binapp.domain.models.CardInfo
import javax.inject.Inject

class SaveCardToHistoryUseCase @Inject constructor(private val repository: ICardRepository) {
    suspend operator fun invoke(cardInfo: CardInfo) {
        repository.saveCardToHistory(cardInfo)
    }
}