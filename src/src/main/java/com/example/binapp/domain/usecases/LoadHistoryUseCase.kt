package com.example.binapp.domain.usecases

import com.example.binapp.data.repository.ICardRepository
import com.example.binapp.domain.models.CardInfo
import javax.inject.Inject

class LoadHistoryUseCase @Inject constructor(private val repository: ICardRepository) {
    suspend operator fun invoke(): List<CardInfo> {
        return repository.getAllCardsFromHistory()
    }
}