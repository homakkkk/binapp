package com.example.binapp.data.repository

import android.content.Context
import androidx.room.withTransaction
import com.example.binapp.data.local.BinDatabase
import com.example.binapp.data.remote.BinListService
import com.example.binapp.data.remote.dto.CardInfoResponse
import com.example.binapp.domain.mappers.CardMapper
import com.example.binapp.domain.models.CardInfo
import retrofit2.Response
import javax.inject.Inject

class DefaultCardRepository @Inject constructor(
    private val context: Context,
    private val binListService: BinListService,
    private val database: BinDatabase
) : ICardRepository {

    override suspend fun fetchCardInfo(binNumber: String): Result<CardInfo> {
        val response: Response<CardInfoResponse> = binListService.lookupBin(binNumber)
        
        return when {
            response.isSuccessful && response.body() != null -> {
                val cardInfo = CardMapper.toCardInfo(response.body()!!)
                Result.success(cardInfo)
            }
            else -> {
                Result.failure(Exception("Error fetching card information"))
            }
        }
    }

    override suspend fun saveCardToHistory(cardInfo: CardInfo) {
        database.cardDao().insertCard(cardInfo)
    }

    override suspend fun getAllCardsFromHistory(): List<CardInfo> {
        return database.cardDao().getAllCards()
    }
}
/*
Результаты диагноза Помощника ИИ:
Added missing imports for Android, Room, Retrofit and Dagger
Replaced direct mapper call with CardMapper usage
Fixed CardInfoResponse import
*/
