package com.fintech.korona.tips.interactors

import com.fintech.korona.tips.data.model.objects.Card
import com.fintech.korona.tips.data.repository.card.CardRepositoryImpl

/**
 * @author Ivan Prokopyev
 */
class CardInteractor {
    private val cardRepository = CardRepositoryImpl()

    fun saveCard(card: Card) =
            cardRepository.putCard(card)


    fun getCard(): Card =
            cardRepository.getCard()

}