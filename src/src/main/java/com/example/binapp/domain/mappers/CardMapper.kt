package com.example.binapp.domain.mappers

import com.example.binapp.data.remote.dto.CardInfoResponse
import com.example.binapp.domain.models.CardInfo

object CardMapper {

    fun toCardInfo(dto: CardInfoResponse): CardInfo {
        return CardInfo(
            id = null,              // ID генерируется позже
            number = dto.number,
            brand = dto.brand ?: "",     // Если бренд отсутствует, устанавливаем пустую строку
            countryName = dto.country?.name ?: "",
            bankPhone = dto.bank?.phone,
            bankWebsite = dto.bank?.url
        )
    }

    // Можно добавить обратное преобразование, если оно потребуется
    /*fun fromCardInfo(domainModel: CardInfo): CardInfoResponse { }*/
}
