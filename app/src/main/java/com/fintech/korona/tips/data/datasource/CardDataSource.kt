package com.fintech.korona.tips.data.datasource

import com.fintech.korona.tips.data.model.objects.Card

/**
 * @author Ivan Prokopyev
 */
interface CardDataSource {

    fun putCard(data: Card)

    fun getCard(): Card
}