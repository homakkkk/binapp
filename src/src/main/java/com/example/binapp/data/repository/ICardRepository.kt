package com.example.binapp.data.repository

import com.example.binapp.domain.models.CardInfo
import java.util.*

interface ICardRepository {
    suspend fun fetchCardInfo(binNumber: String): Result<CardInfo>
    suspend fun saveCardToHistory(cardInfo: CardInfo)
    suspend fun getAllCardsFromHistory(): List<CardInfo>
}
/*
Результаты диагноза Помощника ИИ:
Added missing import for CardInfo model
*/
