package com.fintech.korona.tips.data.repository.card

import com.fintech.korona.tips.data.datasource.CardDataSource
import com.fintech.korona.tips.data.datasource.InMemoryStorage
import com.fintech.korona.tips.data.model.objects.Card

/**
 * @author Ivan Prokopyev
 */
interface CardRepository {
    fun getCard(): Card

    fun putCard(data: Card)

}

class CardRepositoryImpl : CardRepository {

    private val cardDataSource: CardDataSource = InMemoryStorage()


    override fun getCard(): Card =
            cardDataSource.getCard()


    override fun putCard(data: Card) =
            cardDataSource.putCard(data)
}